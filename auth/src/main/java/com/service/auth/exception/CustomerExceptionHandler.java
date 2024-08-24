package com.service.auth.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class CustomerExceptionHandler {

    @ExceptionHandler(UnauthorizedException.class)
    public ResponseEntity<ErrorResponse> handleUnauthorizedException(UnauthorizedException ex) {
        ErrorResponse exception = new ErrorResponse(HttpStatus.UNAUTHORIZED, ex.getMessage());
        return new ResponseEntity<>(exception, HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(TokenExpiredException.class)
    public ResponseEntity<ErrorResponse> tokenExpiredException(UnauthorizedException ex) {
        ErrorResponse exception = new ErrorResponse(HttpStatus.UNAUTHORIZED, ex.getMessage());
        return new ResponseEntity<>(exception, HttpStatus.UNAUTHORIZED);
    }
}
