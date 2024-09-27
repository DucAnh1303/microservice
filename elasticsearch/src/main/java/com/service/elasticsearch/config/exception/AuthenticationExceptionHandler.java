package com.service.elasticsearch.config.exception;

public class AuthenticationExceptionHandler extends RuntimeException {

    private String message;

    public AuthenticationExceptionHandler(String message) {
        super(message);
    }
}
