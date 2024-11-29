package com.service.microservice.auth.common.exception;

import org.springframework.security.core.AuthenticationException;

public class InvalidAuthenticationError extends AuthenticationException {

    public InvalidAuthenticationError() {
        super("Account is not present !");
    }
    public InvalidAuthenticationError(String msg) {
        super(msg);
    }

}
