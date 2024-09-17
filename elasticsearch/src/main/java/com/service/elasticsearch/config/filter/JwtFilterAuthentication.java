package com.service.elasticsearch.config.filter;

import com.service.elasticsearch.config.service.auth.UserDetailService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@Slf4j
public class JwtFilterAuthentication extends OncePerRequestFilter {

    private final JwtUtil jwtUtil;
    private final UserDetailService userDetailsService;

    @Autowired
    public JwtFilterAuthentication(JwtUtil jwtUtil, UserDetailService userDetailsService) {
        this.jwtUtil = jwtUtil;
        this.userDetailsService = userDetailsService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws ServletException, IOException {

//        final String header = request.getHeader("Authorization");
//        final String token;
//        final String userName;
//
//        if (header == null || !header.startsWith("Bearer ")) {
//            chain.doFilter(request, response);
//            return;
//        }
//
//        token = header.substring(7);
//
//        Claims claims = jwtUtil.getAllClaimsFromToken(token);
//        userName = claims.getSubject();
//        try {
//            if (userName != null && SecurityContextHolder.getContext().getAuthentication() == null) {
//                UserDetails userDetails = userDetailsService.loadUserByUsername(userName);
//                if (!jwtUtil.isTokenExpired(token)) {
//                    UsernamePasswordAuthenticationToken authentication =
//                            new UsernamePasswordAuthenticationToken(userDetails, null, Collections.emptyList());
//                    SecurityContext securityContext = SecurityContextHolder.createEmptyContext();
//                    securityContext.setAuthentication(authentication);
//                    SecurityContextHolder.setContext(securityContext);
//                    Authentication authentication1 = securityContext.getAuthentication();
//
//                }
//            }
//        } catch (AuthenticationException e) {
//            throw new ServletException(e.getMessage());
//        }
        chain.doFilter(request, response);
    }
}
