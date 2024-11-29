package com.service.microservice.auth.common.exception;

public class TokenExpiredException extends RuntimeException {

    public TokenExpiredException() {
        super("Token has expired or not exits");
    }

    public TokenExpiredException(String message) {
        super(message);
    }
}
