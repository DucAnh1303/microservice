package com.service.microservice.auth.domain.usecase;

import com.service.microservice.auth.response.AuthResponse;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
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

    // get username
    public String getToken(String token) {
        return Jwts.parserBuilder().setSigningKey(this.key).build().parseClaimsJws(token).getBody().getSubject();
    }

    // get token
    public String generate(AuthResponse authResponse, String type) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("auth", authResponse);
        claims.put("type", type);
        return doGenerateToken(claims, authResponse.getName(), type);
    }

    private String doGenerateToken(Map<String, Object> claims, String username, String type) {

        long expirationTimeLong = getExpirationTime(type);
        final Date createdDate = new Date();
        final Date expirationDate = new Date(createdDate.getTime() + expirationTimeLong);

        return Jwts.builder()
                .setClaims(claims)
                .setSubject(username)
                .setIssuer("microservice")
                .setIssuedAt(createdDate)
                .setExpiration(expirationDate)
                .signWith(this.key,SignatureAlgorithm.HS256)
                .compact();
    }

    private long getExpirationTime(String type) {
        if ("REFRESH" .equals(type)) {
            return expirationTimeRefresh;
        }
        return expirationTime; // Default to access token expiration
    }

    // check time token expire
    public boolean isTokenExpired(String token) {
        final Date expiration = getExpirationDateFromToken(token);
        return expiration.before(new Date());
    }

    public Date getExpirationDateFromToken(String token) {
        return getClaimsFromToken(token).getExpiration();
    }


    public Claims getClaimsFromToken(String token) {
        return Jwts.parserBuilder().setSigningKey(this.key).build().parseClaimsJws(token).getBody();
    }
}
