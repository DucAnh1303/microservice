package com.service.microservice.auth.config.filter;

import com.service.microservice.auth.common.exception.InvalidAuthenticationError;
import com.service.microservice.auth.config.common.AccessRole;
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
import java.util.Arrays;
import java.util.List;
import java.util.regex.PatternSyntaxException;

@Slf4j
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtFilter jwtUtil;
    private final AuthenticationEntryPoint authenticationEntryPoint;
    private final SecurityService securityService;
    private static final String TOKEN_INVALID_OR_EXPIRED = "Token invalid or expired !";
    private static final String AUTHORIZATION = "Authorization";
    private static final String BEARER = "Bearer ";

    public JwtAuthenticationFilter(AuthenticationEntryPoint authenticationEntryPoint, JwtFilter jwtUtil, SecurityService securityService) {
        this.authenticationEntryPoint = authenticationEntryPoint;
        this.jwtUtil = jwtUtil;
        this.securityService = securityService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws ServletException, IOException {
        try {
            final String header = request.getHeader(AUTHORIZATION);
            log.info("remoteAddr: {}", request.getRemoteAddr());

            if (isAccessAllPath(request.getRequestURI())) {
                if (header == null || !header.startsWith(BEARER)) {
                    chain.doFilter(request, response);
                    return;
                }
            }

            String token = header.substring(7);

            if (ObjectUtils.isEmpty(token) || jwtUtil.isTokenExpired(token)) {
                authenticationEntryPoint.commence(request, response, new InvalidAuthenticationError(TOKEN_INVALID_OR_EXPIRED));
                return;
            }

            Claims claims = jwtUtil.getClaimsFromToken(token);
            if (claims != null && SecurityContextHolder.getContext().getAuthentication() == null) {
                AuthResponse res = jwtUtil.getAuthResponseFromToken(token);
                if (!token.matches(securityService.getCurrentToken(res.getId()))) {
                    this.authenticationEntryPoint.commence(request, response, new InvalidAuthenticationError(TOKEN_INVALID_OR_EXPIRED));
                    return;
                }
                final List<GrantedAuthority> authorities = List.of(new SimpleGrantedAuthority(res.getRole()));
                UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
                        new UsernamePasswordAuthenticationToken(res, null, authorities);
                usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetails(request));
                SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
            }
        } catch (InvalidAuthenticationError | NullPointerException e) {
            this.authenticationEntryPoint.commence(request, response, new InvalidAuthenticationError(TOKEN_INVALID_OR_EXPIRED));
            return;
        }
        chain.doFilter(request, response);
    }

    private boolean isAccessAllPath(String requestUri) {
        try {
            return Arrays.stream(AccessRole.accessAll).toList().stream().anyMatch(requestUri::matches);

        } catch (PatternSyntaxException e) {
            return false;
        }
    }

}
