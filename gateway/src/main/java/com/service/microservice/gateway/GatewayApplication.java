package com.service.microservice.gateway;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

@SpringBootApplication
//@EnableDiscoveryClient
public class GatewayApplication {

    public static void main(String[] args) {
//        Dotenv dotenv = Dotenv.load();
//        System.setProperty("SECRET_EMPLOYEE", dotenv.get("SECRET_EMPLOYEE"));

        new SpringApplicationBuilder(GatewayApplication.class)
                .web(WebApplicationType.REACTIVE)
                .run(args);
    }

}
