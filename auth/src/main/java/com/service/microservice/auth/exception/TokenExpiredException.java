package com.service.microservice.auth.exception;

public class TokenExpiredException extends RuntimeException {

    public TokenExpiredException() {
        super("Token has expired. Please login again to obtain a new token.");
    }

    public TokenExpiredException(String message) {
        super(message);
    }
}
