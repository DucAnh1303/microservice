package com.service.elasticsearch.config.exception;

public class AuthenticationException extends RuntimeException {

    private String message;

    public AuthenticationException(String message) {
        super(message);
    }
}
