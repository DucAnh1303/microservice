package com.service.elasticsearch.config.exception;

import com.service.elasticsearch.common.ExceptionResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class CustomerExceptionHandler {

    @ExceptionHandler(AuthenticationException.class)
    public ResponseEntity<?> exception(ExceptionResponse exceptionResponse) {
        ExceptionResponse response = ExceptionResponse.builder().code(exceptionResponse.getCode()).message(exceptionResponse.getMessage()).build();
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
    }
}
