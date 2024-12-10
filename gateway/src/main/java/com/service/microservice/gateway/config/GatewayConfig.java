package com.service.microservice.gateway.config;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.cloud.gateway.filter.ratelimit.KeyResolver;
import org.springframework.cloud.gateway.filter.ratelimit.RedisRateLimiter;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import reactor.core.publisher.Mono;

@Configuration
@RequiredArgsConstructor
@Slf4j
public class GatewayConfig {

    private final AuthenticationFilter filter;

    @Bean
    public RouteLocator routes(RouteLocatorBuilder builder) {
        return builder.routes()
                .route("auth", r -> r
                        .path("/auth/**")
                        .filters(f -> f.filter(filter))
                        .uri("http://localhost:9091"))
                .build();
    }

    @Bean
    public RedisRateLimiter redisRateLimiter() {
        return new RedisRateLimiter(10, 15,20);
    }

    @Bean
    public GlobalFilter customTooManyRequestsResponseFilter() {
        return (exchange, chain) -> chain.filter(exchange)
                .onErrorResume(WebClientResponseException.TooManyRequests.class, ex -> {
                    ServerHttpResponse response = exchange.getResponse();
                    response.setStatusCode(HttpStatus.TOO_MANY_REQUESTS);
                    response.getHeaders().add("Content-Type", "application/json");

                    String message = "{\"error\":\"Too many requests! Please try again later.\"}";

                    DataBuffer dataBuffer = response.bufferFactory().wrap(message.getBytes());
                    return response.writeWith(Mono.just(dataBuffer));
                });
    }

    @Bean
    public KeyResolver keyResolver() {
//        Set<String> blockedIps = Set.of("192.168.1.100", "203.0.113.0"); block ip in here
        return exchange -> {
            String clientIp = exchange.getRequest().getRemoteAddress().getAddress().getHostAddress();

//            if (blockedIps.contains(clientIp)) { block ip in here
//                return Mono.error(new NotFoundException("Access denied for IP: " + clientIp));
//            }

            // Tạo khóa rate limiter với IP hợp lệ
            return Mono.just("rate_limiter:" + clientIp);
        };
    }
}
