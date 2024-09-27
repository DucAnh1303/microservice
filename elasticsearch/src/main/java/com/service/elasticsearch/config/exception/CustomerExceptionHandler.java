package com.service.elasticsearch.config.exception;

import com.service.elasticsearch.common.ExceptionResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class CustomerExceptionHandler {

    @ExceptionHandler(AuthenticationExceptionHandler.class)
    public ResponseEntity<?> exception(AuthenticationException e) {
        ExceptionResponse response = ExceptionResponse.builder().code(HttpStatus.UNAUTHORIZED.value()).message(e.getMessage()).build();
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
    }
}
