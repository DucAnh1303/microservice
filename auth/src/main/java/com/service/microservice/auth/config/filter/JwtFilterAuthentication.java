package com.service.microservice.auth.config.filter;

import com.service.microservice.auth.response.AuthResponse;
import io.jsonwebtoken.Claims;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Collections;

@Component
@Slf4j
@RequiredArgsConstructor
public class JwtFilterAuthentication extends OncePerRequestFilter {

    private final JwtFilter jwtUtil;
    private final UserDetailsService userDetailsService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws ServletException, IOException {

        final String header = request.getHeader("Authorization");
        final String token;
        final String userName;

        if (header == null || !header.startsWith("Bearer ")) {
            chain.doFilter(request, response);
            return;
        }

        token = header.substring(7);

        if (ObjectUtils.isEmpty(token)) {
            throw new BadCredentialsException("Token is not exits !");
        }


        if (jwtUtil.isTokenExpired(token)) {
            throw new BadCredentialsException("Token has expired !");
        }

        Claims claims = jwtUtil.getClaimsFromToken(token);
        userName = claims.getSubject();


        try {
            if (userName != null && SecurityContextHolder.getContext().getAuthentication() == null) {
                AuthResponse userDetails = jwtUtil.getAuthResponseFromToken(token);
                UsernamePasswordAuthenticationToken authentication =
                        new UsernamePasswordAuthenticationToken(userDetails, null, Collections.emptyList());
                SecurityContext securityContext = SecurityContextHolder.createEmptyContext();
                securityContext.setAuthentication(authentication);
                SecurityContextHolder.setContext(securityContext);
            }
        } catch (AuthenticationException e) {
            throw new ServletException(e.getMessage());
        }
        chain.doFilter(request, response);
    }
}
