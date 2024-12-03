package com.service.microservice.auth.common.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.nio.file.AccessDeniedException;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class CustomerExceptionHandler {

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ErrorResponse<?>> handleUnauthorizedException(NotFoundException ex) {
        ErrorResponse<?> exception = new ErrorResponse<>(HttpStatus.NOT_FOUND.value(), HttpStatus.NOT_FOUND, ex.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exception);
    }

    @ExceptionHandler(TokenExpiredException.class)
    public ResponseEntity<ErrorResponse<?>> tokenExpiredException(TokenExpiredException ex) {
        ErrorResponse<?> exception = new ErrorResponse<>(HttpStatus.UNAUTHORIZED.value(), HttpStatus.UNAUTHORIZED, ex.getMessage());
        return new ResponseEntity<>(exception, HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(InvalidError.class)
    public ResponseEntity<ErrorResponse<?>> invalidLoginException(InvalidError ex) {
        ErrorResponse<?> exception = new ErrorResponse<>(ex.getHttpStatus().value(), ex.getHttpStatus(), ex.getMessage());
        return new ResponseEntity<>(exception, ex.getHttpStatus());
    }

    @ExceptionHandler(JwtAuthenticationError.class)
    public ResponseEntity<ErrorResponse<?>> invalidLoginException(JwtAuthenticationError ex) {
        ErrorResponse<?> exception = new ErrorResponse<>(HttpStatus.UNAUTHORIZED.value(), HttpStatus.UNAUTHORIZED, ex.getMessage());
        return new ResponseEntity<>(exception, HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(SignatureInvalidError.class)
    public ResponseEntity<ErrorResponse<?>> signatureInvalidError(SignatureInvalidError ex) {
        ErrorResponse<?> exception = new ErrorResponse<>(HttpStatus.UNAUTHORIZED.value(), HttpStatus.UNAUTHORIZED, ex.getMessage());
        return new ResponseEntity<>(exception, HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(InvalidAuthenticationError.class)
    public ResponseEntity<ErrorResponse<?>> invalidAuthenticationError(InvalidAuthenticationError ex) {
        ErrorResponse<?> exception = new ErrorResponse<>(HttpStatus.UNAUTHORIZED.value(), HttpStatus.UNAUTHORIZED, ex.getMessage());
        return new ResponseEntity<>(exception, HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse<?>> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(error ->
                errors.put(error.getField(), error.getDefaultMessage()));
        ErrorResponse<?> errorResponse = new ErrorResponse<>(HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST, errors);
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<ErrorResponse<?>> handleAccessDeniedException(AccessDeniedException ex) {
        ErrorResponse<?> errorResponse = new ErrorResponse<>(HttpStatus.FORBIDDEN.value(), HttpStatus.FORBIDDEN, "Access is denied: " + ex.getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.FORBIDDEN);
    }
}
