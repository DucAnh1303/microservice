package com.service.shipping.config.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalHandleException {

    @ExceptionHandler(RetryMessageHandleException.class)
    public ResponseEntity<ErrorResponse> retryMessage(RetryMessageHandleException ex) {
        ErrorResponse errorResponse = new ErrorResponse(429, ex.getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.TOO_MANY_REQUESTS);
    }
}
