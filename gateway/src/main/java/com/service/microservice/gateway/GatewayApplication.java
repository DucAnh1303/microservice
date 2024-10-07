package com.service.microservice.gateway;

import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class GatewayApplication {

    public static void main(String[] args) {
        Dotenv dotenv = Dotenv.load();
        System.setProperty("SECRET_EMPLOYEE", dotenv.get("SECRET_EMPLOYEE"));

        SpringApplication.run(GatewayApplication.class, args);
    }

}
