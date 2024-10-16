package com.service.microservice.gateway.config;

import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.function.Predicate;

@Component
public class RouterValidator {

    public static final List<String> openApiEndpoints = List.of(
            "/auth/login",
            "/manage/execute",
            "/auth/refresh-token",
            "/v3/api-docs/**",
            "/swagger-ui.html/**",
            "/swagger-ui/**",
            "/swagger-resources/**",
            "/webjars/**",
            "/webjars/swagger-ui/index.html"
    );

    public Predicate<ServerHttpRequest> isSecured =
            request -> openApiEndpoints
                    .stream()
                    .noneMatch(uri -> request.getURI().getPath().contains(uri));
}
