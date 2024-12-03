package com.service.microservice.auth.common.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class InvalidError extends RuntimeException {
    private HttpStatus httpStatus;

    public InvalidError() {
        super("Account is not present !");
        this.httpStatus = HttpStatus.BAD_REQUEST;
    }

    public InvalidError(String message) {
        super(message);
    }

    public InvalidError(HttpStatus httpStatus, String message) {
        super(message);
        this.httpStatus = httpStatus;
    }
}
