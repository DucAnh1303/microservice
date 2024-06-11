package com.service.vendor.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.service.vendor.response.AuthResponse;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.impl.DefaultJwtParserBuilder;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;

@Component
public class JwtUtil {

    @Value("${jwt.secret}")
    private String secret;

    private Key key;

    @PostConstruct
    public void init() {
        this.key = Keys.hmacShaKeyFor(secret.getBytes());
    }

    public Claims getAllClaimsFromToken(String token) {
        DefaultJwtParserBuilder defaultJwtParserBuilder = new DefaultJwtParserBuilder();
        return defaultJwtParserBuilder.setSigningKey(this.key).build().parseClaimsJws(token).getBody();
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

    private boolean isTokenExpired(String token) {
        return this.getAllClaimsFromToken(token).getExpiration().before(new Date());
    }

    public boolean isInvalid(String token) {
        return this.isTokenExpired(token);
    }

}
