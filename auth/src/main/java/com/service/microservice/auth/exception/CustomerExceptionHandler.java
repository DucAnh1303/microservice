package com.service.microservice.auth.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class CustomerExceptionHandler {

    @ExceptionHandler(UserNameOrPasswordInValidException.class)
    public ResponseEntity<ErrorResponse> handleUnauthorizedException(UserNameOrPasswordInValidException ex) {
        ErrorResponse exception = new ErrorResponse(HttpStatus.UNAUTHORIZED.value(), HttpStatus.UNAUTHORIZED, ex.getMessage());
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(exception);
    }

    @ExceptionHandler(TokenExpiredException.class)
    public ResponseEntity<ErrorResponse> tokenExpiredException(TokenExpiredException ex) {
        ErrorResponse exception = new ErrorResponse(HttpStatus.UNAUTHORIZED.value(), HttpStatus.UNAUTHORIZED, ex.getMessage());
        return new ResponseEntity<>(exception,HttpStatus.UNAUTHORIZED);
    }
}
