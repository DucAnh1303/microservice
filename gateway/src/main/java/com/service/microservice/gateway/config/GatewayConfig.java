package com.service.microservice.gateway.config;

import lombok.RequiredArgsConstructor;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class GatewayConfig {

    private final AuthenticationFilter filter;

    @Bean
    public RouteLocator routes(RouteLocatorBuilder builder) {
        return builder
                .routes()
                .route("swagger-route", r -> r.path("/swagger-ui/**")
                        .uri("http://localhost:9090/webjars/swagger-ui/index.html"))
                .route("auth", r -> r.path("/auth/**")
                        .filters(f -> f.filter(filter))
                        .uri("http://localhost:9091"))
                .route("manage", r -> r.path("/manage/**")
                        .filters(f -> f.filter(filter))
                        .uri("http://localhost:9093"))
                .build();
    }

}
