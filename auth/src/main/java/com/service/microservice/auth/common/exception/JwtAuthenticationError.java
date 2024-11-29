package com.service.microservice.auth.common.exception;

import io.jsonwebtoken.JwtException;

public class JwtAuthenticationError extends JwtException {

    public JwtAuthenticationError(String message) {
        super(message);
    }
}
