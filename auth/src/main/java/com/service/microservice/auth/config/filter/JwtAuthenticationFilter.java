package com.service.microservice.auth.config.filter;

import com.service.microservice.auth.common.exception.InvalidAuthenticationError;
import com.service.microservice.auth.config.security.SecurityService;
import com.service.microservice.auth.entities.AuthEntity;
import com.service.microservice.auth.response.AuthResponse;
import io.jsonwebtoken.Claims;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.util.ObjectUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;

@Slf4j
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtFilter jwtUtil;

    private final AuthenticationEntryPoint authenticationEntryPoint;


    private final SecurityService securityService;


    public JwtAuthenticationFilter(AuthenticationEntryPoint authenticationEntryPoint, JwtFilter jwtUtil, SecurityService securityService) {
        this.authenticationEntryPoint = authenticationEntryPoint;
        this.jwtUtil = jwtUtil;
        this.securityService = securityService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws ServletException, IOException {

        final String header = request.getHeader("Authorization");
        log.info("remoteAddr: {}", request.getRemoteAddr());

        if (header == null || !header.startsWith("Bearer ")) {
            chain.doFilter(request, response);
            return;
        }

        String token = header.substring(7);

        if (ObjectUtils.isEmpty(token))
            authenticationEntryPoint.commence(request, response, new InvalidAuthenticationError("Invalid token"));

        if (jwtUtil.isTokenExpired(token)) {
            authenticationEntryPoint.commence(request, response, new InvalidAuthenticationError("Token has expired or invalid"));
        }
        try {
            Claims claims = jwtUtil.getClaimsFromToken(token);
            if (claims != null && SecurityContextHolder.getContext().getAuthentication() == null) {
                AuthResponse res = jwtUtil.getAuthResponseFromToken(token);
                if (!token.matches(securityService.getCurrentToken(res.getId()))) {
                    this.authenticationEntryPoint.commence(request, response, new InvalidAuthenticationError("Token has expired !"));
                    return;
                }
                final List<GrantedAuthority> authorities = List.of(new SimpleGrantedAuthority(res.getRole()));
                UsernamePasswordAuthenticationToken authentication =
                        new UsernamePasswordAuthenticationToken(res, null, authorities);
                authentication.setDetails(new WebAuthenticationDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        } catch (InvalidAuthenticationError e) {
            this.authenticationEntryPoint.commence(request, response, new InvalidAuthenticationError("Token has expired !"));
            return;
        }
        chain.doFilter(request, response);
    }

}
