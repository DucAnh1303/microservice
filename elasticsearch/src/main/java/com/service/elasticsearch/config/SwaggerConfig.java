package com.service.elasticsearch.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Employee API")
                        .version("1.0")
                        .description("API Employee with Spring Cloud Gateway and Swagger")
                        .contact(new Contact()
                                .name("API Support")
                                .url("http://example.com/contact")
                                .email("support@example.com"))
                        .license(new License()
                                .name("Apache 2.0")
                                .url("http://springdoc.org")))
                .addServersItem(new Server().url("http://localhost:9093")
                        .description("local"))
                .addServersItem(new Server().url("https://staging1111.company.com")
                        .description("Staging server"))
                .addServersItem(new Server().url("https://production1111.company.com")
                        .description("Production server"))
                .addSecurityItem(new SecurityRequirement().addList("Authentication"))
                .components(new io.swagger.v3.oas.models.Components()
                        .addSecuritySchemes("Authentication", new SecurityScheme()
                                .type(SecurityScheme.Type.HTTP)
                                .scheme("bearer")
                                .bearerFormat("JWT")));
    }

}
