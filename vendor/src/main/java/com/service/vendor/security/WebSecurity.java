package com.service.vendor.security;


import com.service.vendor.config.CorsConfig;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


@RequiredArgsConstructor
@Configuration
@EnableWebSecurity
public class WebSecurity {

    private final JwtAuthenticationFilter jwtAuthenticationFilter;
    private final DelegatedAuthenticationEntryPoint authenticationEntryPoint;

    @Bean
    protected SecurityFilterChain configure(final HttpSecurity http) throws Exception {
        http.csrf(AbstractHttpConfigurer::disable)
                .cors(AbstractHttpConfigurer::disable)
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(auth -> auth
                        .anyRequest()
                        .authenticated())
                .exceptionHandling(exp -> exp.authenticationEntryPoint(authenticationEntryPoint));
        http.addFilterAt(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }


    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public FilterRegistrationBean<CorsConfig> corsFilterRegistration() {
        FilterRegistrationBean<CorsConfig> registrationBean = new FilterRegistrationBean<>();
        registrationBean.setFilter(new CorsConfig());
        registrationBean.addUrlPatterns("/*");
        registrationBean.setName("CorsConfig");
        return registrationBean;
    }

}
