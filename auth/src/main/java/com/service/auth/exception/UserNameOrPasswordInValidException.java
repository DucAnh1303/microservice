package com.service.auth.exception;


public class UserNameOrPasswordInValidException extends RuntimeException {

    public UserNameOrPasswordInValidException(String message) {
        super(message);
    }

}
