package com.service.microservice.gateway.config;


import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.core.io.buffer.DataBufferUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.nio.charset.StandardCharsets;
import java.util.Set;

@RefreshScope
@Component
public class AuthenticationFilter implements GatewayFilter {

    private final RouterValidator routerValidator;

    private final JwtUtil jwtUtil;

    private static final Set<String> blockedIps = Set.of("192.168.1.100", "203.0.113.0");


    @Value("${microservices.employee.secret-key}")
    private String secretKeyEmployee;

    @Value("${microservices.employee.uri}")
    private String uriEmployee;

    @Autowired
    public AuthenticationFilter(RouterValidator routerValidator, JwtUtil jwtUtil) {
        this.routerValidator = routerValidator;
        this.jwtUtil = jwtUtil;
    }

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        ServerHttpRequest request = exchange.getRequest();

//        String clientIp = exchange.getRequest().getRemoteAddress().getAddress().getHostAddress(); block ip in here
//        if (blockedIps.contains(clientIp)) {
//            exchange.getResponse().setStatusCode(HttpStatus.FORBIDDEN);
//            return exchange.getResponse().setComplete();
//        }

        if (routerValidator.isSecured.test(request)) {
            if (this.isAuthMissing(request)) {
                return this.onError(exchange, HttpStatus.UNAUTHORIZED, "Account information invalid");
            }

            final String token = this.getAuthHeader(request).substring(7);

            try {
                jwtUtil.validateToken(token);
            } catch (Exception e) {
                return this.onError(exchange, HttpStatus.FORBIDDEN, "Token is expired or invalid");
            }

            if (request.getURI().getPath().equals(uriEmployee)) {
                this.updateRequest(exchange, token, secretKeyEmployee);
            } else {
                this.updateRequest(exchange, token, "");
            }
        }
        return chain.filter(exchange);
    }

    private Mono<Void> onError(ServerWebExchange exchange, HttpStatus httpStatus, String errorMessage) {

        ServerHttpResponse response = exchange.getResponse();
        response.setStatusCode(httpStatus);
        response.getHeaders().setContentType(MediaType.APPLICATION_JSON);
        response.getHeaders().set("charset", "UTF-8");

        try {
            byte[] bytes = String.format("{ \"error\": \"%s\", \"message\": \"%s\" }", httpStatus, errorMessage).getBytes(StandardCharsets.UTF_8);
            DataBuffer buffer = response.bufferFactory().wrap(bytes);
            return response.writeWith(Mono.just(buffer)).doOnError(throwable -> DataBufferUtils.release(buffer));
        } catch (Exception e) {
            return response.setComplete();
        }
    }

    private String getAuthHeader(ServerHttpRequest request) {
        return request.getHeaders().getOrEmpty("Authorization").get(0);
    }

    private boolean isAuthMissing(ServerHttpRequest request) {
        return !request.getHeaders().containsKey("Authorization");
    }

    private void updateRequest(ServerWebExchange exchange, String token, String secretKey) {
        Claims claims = jwtUtil.getAllClaimsFromToken(token);
        exchange.getRequest().mutate()
                .header("secret-key", secretKey)
                .build();
    }
}
