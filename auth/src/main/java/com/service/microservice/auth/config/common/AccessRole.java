package com.service.microservice.auth.config.common;

public class AccessRole {

    public static String[] accessAll = new String[]{"/auth/login",
            "/auth/refresh-token",
            "/v3/api-docs/**",
            "/swagger-ui.html",
            "/swagger-ui/**",
            "/swagger-resources/**",
            "/webjars/**",
            "/eureka/**",
            "/api/limited"};

    public static String[] accessAdmin = new String[]{"/auth/search"};
    public static String[] accessManage = new String[]{"/auth/search"};
    public static String[] accessUser = new String[]{"/auth/search"};
}
