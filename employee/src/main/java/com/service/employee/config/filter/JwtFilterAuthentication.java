package com.service.employee.config.filter;

import com.service.employee.domain.usecase.auth.UserDetailService;
import com.service.employee.repository.UserRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.ArrayList;

@Component
@RequiredArgsConstructor
public class JwtFilterAuthentication extends OncePerRequestFilter {

    private final JwtUtil jwtUtil;
    private final UserDetailService userDetailService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        final String authorizationHeader = request.getHeader("Authorization");
        String username = null;
        String jwtToken = null;

        if (authorizationHeader != null) {
            jwtToken = authorizationHeader;
            username = jwtUtil.extractUsername(jwtToken);
        }

        if (username != null && jwtUtil.validateToken(jwtToken, username)) {
            try {
                UserDetails userDetails = userDetailService.loadUserByUsername(username);
                UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                        userDetails, null, new ArrayList<>());
                SecurityContextHolder.getContext().setAuthentication(authentication);
            } catch (Exception e) {
                throw new RuntimeException("Invalid token");
            }
        }

        filterChain.doFilter(request, response);
    }
}
