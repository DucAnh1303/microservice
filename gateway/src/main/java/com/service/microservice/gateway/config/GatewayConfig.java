package com.service.microservice.gateway.config;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
@Slf4j
public class GatewayConfig {

    private final AuthenticationFilter filter;

    @Bean
    public RouteLocator routes(RouteLocatorBuilder builder) {
        return builder
                .routes()
                .route("auth", r -> r.path("/auth/**")
                        .filters(f -> f.filter(filter))
                        .uri("http://localhost:9091"))
                .route("manage", r -> r.path("/manage/**")
                        .filters(f -> f.filter(filter))
                        .uri("http://localhost:9093"))
                .build();
    }

//    @Bean
//    public KeyResolver hostKeyResolver() {
//        return exchange -> {
//            String hostName = exchange.getRequest().getRemoteAddress() != null ?
//                    exchange.getRequest().getRemoteAddress().getHostName() : "unknown";
//            return Mono.just(hostName);
//        };
//    }

//    @Bean
//    public RedisRateLimiter redisRateLimiter() {
//        return new RedisRateLimiter(100, 100) {
//            @Override
//            public Mono<Response> isAllowed(String routeId, String id) {
//                return super.isAllowed(routeId, id).flatMap(response -> {
//                    if (!response.isAllowed()) {
//                        // Log or handle when max limit
//                        log.warn("Too many requests for route: {}", routeId);
//                    }
//                    return Mono.just(response);
//                });
//            }
//        };
//    }
}
