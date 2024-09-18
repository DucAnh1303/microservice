package com.service.elasticsearch.config.filter;

import com.service.elasticsearch.common.ExceptionResponse;
import com.service.elasticsearch.response.AuthResponse;
import io.jsonwebtoken.Claims;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.security.sasl.AuthenticationException;
import java.io.IOException;
import java.nio.file.AccessDeniedException;
import java.util.Collections;

@Component
@Slf4j
public class JwtFilterAuthentication extends OncePerRequestFilter {

    private final JwtUtil jwtUtil;

    @Autowired
    public JwtFilterAuthentication(JwtUtil jwtUtil) {
        this.jwtUtil = jwtUtil;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws ServletException, IOException {

        final String header = request.getHeader("Authorization");
        final String token;
        final String userName;

        if (header == null || !header.startsWith("Bearer ")) {
            throw new ExceptionResponse(HttpStatus.UNAUTHORIZED.value(), "Authorization header is incorrect");
        }

        token = header.substring(7);

        if (jwtUtil.isTokenExpired(token)) {
            throw new AccessDeniedException("Token expired");
        }

        Claims claims = jwtUtil.getAllClaimsFromToken(token);
        userName = claims.getSubject();
        AuthResponse userDetails = jwtUtil.getAuthResponseFromToken(token);
        if (userName != null && SecurityContextHolder.getContext().getAuthentication() == null) {

            UsernamePasswordAuthenticationToken authentication =
                    new UsernamePasswordAuthenticationToken(userDetails, null, Collections.emptyList());
            SecurityContext securityContext = SecurityContextHolder.createEmptyContext();
            securityContext.setAuthentication(authentication);
            SecurityContextHolder.setContext(securityContext);
            chain.doFilter(request, response);
        }
    }
}
