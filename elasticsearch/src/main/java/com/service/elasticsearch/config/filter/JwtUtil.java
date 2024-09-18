package com.service.elasticsearch.config.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.service.elasticsearch.common.ExceptionResponse;
import com.service.elasticsearch.response.AuthResponse;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.security.Key;

@Component
public class JwtUtil {

    @Value("${jwt.secret}")
    private String secret;

    private Key key;

    @PostConstruct
    public void init() {
        this.key = Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));
    }

    public Claims getAllClaimsFromToken(String token) {
        return Jwts.parserBuilder().setSigningKey(this.key).build().parseClaimsJws(token).getBody();
    }

    public <T> T getClaimFromToken(String token, String claimKey, Class<T> clazz) {
        Claims claims = getAllClaimsFromToken(token);
        Object claim = claims.get(claimKey);
        ObjectMapper mapper = new ObjectMapper();
        return mapper.convertValue(claim, clazz);
    }

    public AuthResponse getAuthResponseFromToken(String token) {
        return getClaimFromToken(token, "auth", AuthResponse.class);
    }

    public boolean isTokenExpired(String token) {
        try {
            Jwts.parserBuilder().setSigningKey(this.key).build().parseClaimsJws(token);
            return true;
        } catch (ExpiredJwtException | UnsupportedJwtException | MalformedJwtException | IllegalArgumentException ex) {
            throw new ExceptionResponse(401, ex.getMessage());
        }
    }

}
