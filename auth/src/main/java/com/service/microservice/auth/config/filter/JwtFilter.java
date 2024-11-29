package com.service.microservice.auth.config.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.service.microservice.auth.common.until.TypeJwt;
import com.service.microservice.auth.entities.AuthControlEntity;
import com.service.microservice.auth.entities.AuthEntity;
import com.service.microservice.auth.response.AuthResponse;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SignatureException;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
@Slf4j
public class JwtFilter {

    @Value("${jwt.secret}")
    private String secret;

    @Value("${jwt.expiration}")
    private long expirationTime;
    @Value("${jwt.expiration-refresh}")
    private long expirationTimeRefresh;

    private Key key;

    @PostConstruct
    public void init() {
        this.key = Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));
    }

    // get token
    public String generate(AuthResponse authResponse, String type) {
        Map<String, Object> claims = new HashMap<>();
        if (TypeJwt.ACCESS.name().equals(type)) {
            claims.put("type", type);
            claims.put("auth", authResponse);
            return doGenerateToken(claims, authResponse.getId(), type);
        }
        claims.put("type", type);
        return doGenerateToken(claims, authResponse.getId(), type);
    }

    private String doGenerateToken(Map<String, Object> claims, Long id, String type) {

        long expirationTimeLong = getExpirationTime(type);
        final Date createdDate = new Date();
        final Date expirationDate = new Date(createdDate.getTime() + expirationTimeLong);

        return Jwts.builder()
                .setClaims(claims)
                .setSubject(String.valueOf(id))
                .setIssuedAt(createdDate)
                .setExpiration(expirationDate)
                .signWith(this.key)
                .compact();
    }

    private long getExpirationTime(String type) {
        if (TypeJwt.REFRESH.name().equals(type)) {
            return expirationTimeRefresh;
        }
        return expirationTime;
    }

    // check time token expire
    public boolean isTokenExpired(String token) {
        try {
            final Date expiration = getClaimsFromToken(token).getExpiration();
            return (expiration.before(new Date()));
        } catch (SignatureException | MalformedJwtException e) {
            return true;
        } catch (ExpiredJwtException e) {
            log.error("JWT token is expired: {} ", e.getMessage());
            return true;
        } catch (UnsupportedJwtException e) {
            log.error("Unsupported JWT token: {} ", e.getMessage());
            return true;
        } catch (IllegalArgumentException e) {
            log.error("JWT claims string is empty: {} ", e.getMessage());
            return true;
        }
    }

    public Claims getClaimsFromToken(String token) {
        return Jwts.parserBuilder().setSigningKey(this.key).build().parseClaimsJws(token).getBody();
    }

    public AuthResponse getAuthResponseFromToken(String token) {
        return getClaimFromToken(token, "auth", AuthResponse.class);
    }

    public Long getId(String token) {
        return getClaimFromToken(token, "id", Long.class);
    }

    public <T> T getClaimFromToken(String token, String claimKey, Class<T> clazz) {
        Claims claims = getClaimsFromToken(token);
        Object claim = claims.get(claimKey);
        ObjectMapper mapper = new ObjectMapper();
        return mapper.convertValue(claim, clazz);
    }
}
