package com.service.microservice.auth.common.exception;

import io.jsonwebtoken.JwtException;

public class SignatureInvalidError extends JwtException {

    public SignatureInvalidError(String message) {
        super(message);
    }

    public SignatureInvalidError(String message, Throwable cause) {
        super(message, cause);
    }
}
