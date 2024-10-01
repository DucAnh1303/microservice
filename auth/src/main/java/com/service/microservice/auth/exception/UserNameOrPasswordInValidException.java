package com.service.microservice.auth.exception;


public class UserNameOrPasswordInValidException extends RuntimeException {

    public UserNameOrPasswordInValidException(String message) {
        super(message);
    }

}
