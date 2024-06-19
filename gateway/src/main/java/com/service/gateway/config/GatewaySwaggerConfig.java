package com.service.gateway.config;

import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GatewaySwaggerConfig{
    @Bean
    public GroupedOpenApi authApi() {
        return GroupedOpenApi.builder()
                .group("auth")
                .pathsToMatch("/auth/**")
                .build();
    }

    @Bean
    public GroupedOpenApi employeeApi() {
        return GroupedOpenApi.builder()
                .group("employee")
                .pathsToMatch("/api/employee/**")
                .build();
    }

    @Bean
    public GroupedOpenApi vendorApi() {
        return GroupedOpenApi.builder()
                .group("vendor")
                .pathsToMatch("/vendor/**")
                .build();
    }

//    @Bean
//    public SwaggerWelcomeWebFlux swaggerWelcome() {
//        return new SwaggerWelcomeWebFlux(MultipleOpenApiResource());
//    }
}
