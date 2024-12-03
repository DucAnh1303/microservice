package com.service.microservice.auth.config.security;


import com.service.microservice.auth.config.common.AccessRole;
import com.service.microservice.auth.config.filter.JwtAuthenticationFilter;
import com.service.microservice.auth.config.filter.JwtFilter;
import com.service.microservice.auth.config.filter.SecurityService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.List;

@Configuration
@EnableWebSecurity
public class WebSecurity {

    private final AuthenticationEntryPoint authenticationEntryPoint;

    private final JwtFilter jwtUtil;

    private final SecurityService securityService;

    private final AccessDeniedHandlerError accessDeniedHandlerError;

    private final CustomAuthorizationManager authorizationManager;


    public WebSecurity(final AuthenticationEntryPoint authenticationEntryPoint, final JwtFilter jwtUtil, final SecurityService securityService,
                        AccessDeniedHandlerError accessDeniedHandlerError, CustomAuthorizationManager authorizationManager) {
        this.authenticationEntryPoint = authenticationEntryPoint;
        this.jwtUtil = jwtUtil;
        this.securityService = securityService;
        this.accessDeniedHandlerError = accessDeniedHandlerError;
        this.authorizationManager = authorizationManager;
    }

    @Bean
    protected SecurityFilterChain configure(final HttpSecurity http) throws Exception {
        http.csrf(AbstractHttpConfigurer::disable)
                .cors(cors -> cors.configurationSource(corsConfigurationSource()))
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers(AccessRole.accessAll).permitAll()
                        .requestMatchers(AccessRole.urlRoleMap.keySet().toArray(new String[0])).access(authorizationManager)
                        .anyRequest().authenticated())
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .exceptionHandling(ext -> ext
                        .accessDeniedHandler(this.accessDeniedHandlerError)
                        .authenticationEntryPoint(this.authenticationEntryPoint));
        http.addFilterBefore(new JwtAuthenticationFilter(this.authenticationEntryPoint, this.jwtUtil,
                this.securityService), UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(
            final AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    private UrlBasedCorsConfigurationSource corsConfigurationSource() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowedOrigins(List.of("http://localhost:9091", "http://localhost:9090"));
        config.addAllowedMethod("GET");
        config.addAllowedMethod("POST");
        config.addAllowedMethod("PUT");
        config.addAllowedMethod("DELETE");
        config.addAllowedHeader("*");
        config.setAllowCredentials(true);
        source.registerCorsConfiguration("/**", config);
        return source;
    }

}
