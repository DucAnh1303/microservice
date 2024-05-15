package com.service.gateway.config;

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
        return builder.routes()
                .route("invoice-service", r -> r.path("/invoices/**")
                        .filters(f -> f.filter(filter))
                        .uri("http://localhost:8084"))

                .route("auth", r -> r.path("/auth/**")
                        .filters(f -> f.filter(filter))
                        .uri("http://localhost:9091"))
                .route("employee", r -> r.path("/api/employee/**")
                        .filters(f -> f.filter(filter))
                        .uri("http://localhost:9093"))
                .route("order", r -> r.path("/order/**")
                        .filters(f -> f.filter(filter))
                        .uri("http://localhost:9094"))
                .route("shipping", r -> r.path("/shipping/**")
                        .filters(f -> f.filter(filter))
                        .uri("http://localhost:9095"))
                .build();
    }
}
