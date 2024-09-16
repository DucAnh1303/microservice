package com.service.employee.config.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.service.employee.response.AuthResponse;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;

@Component
public class JwtUtil {

    @Value("${jwt.secret}")
    private String secret;

    public Key getKey() {
        byte[] keyBytes = Decoders.BASE64.decode(secret);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    public boolean isTokenExpired(String token) {
        return getAllClaimsFromToken(token).getExpiration().before(new Date());
    }

    public AuthResponse getAuthResponseFromToken(String token) {
        return getClaimFromToken(token, "auth", AuthResponse.class);
    }

    public <T> T getClaimFromToken(String token, String claimKey, Class<T> clazz) {
        Claims claims = getAllClaimsFromToken(token);
        Object claim = claims.get(claimKey);
        ObjectMapper mapper = new ObjectMapper();
        return mapper.convertValue(claim, clazz);
    }


    public boolean validateToken(String token) {
        return isTokenExpired(token);
    }

    public Claims getAllClaimsFromToken(String token) {
        return Jwts.parserBuilder().setSigningKey(getKey()).build().parseClaimsJws(token).getBody();
    }
}
