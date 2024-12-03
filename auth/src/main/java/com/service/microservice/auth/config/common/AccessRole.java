package com.service.microservice.auth.config.common;

import java.util.List;
import java.util.Map;

public class AccessRole {

    public static String[] accessAll = new String[]{
            "/auth/login",
            "/auth/refresh-token",
            "/v3/api-docs/**",
            "/swagger-ui.html",
            "/swagger-ui/**",
            "/swagger-resources/**",
            "/webjars/**",
            "/eureka/**"};

    private static final String ROLE_ADMIN = "ADMIN";
    private static final String ROLE_MANAGE = "MANAGE";
    private static final String ROLE_USER = "USER";

    public static final Map<String, List<String>> urlRoleMap = Map.of(
            "/auth/execute", List.of(ROLE_ADMIN, ROLE_MANAGE)
    );
}
