package com.service.microservice.manage.exceptionhandle;

public class ExecuteException extends RuntimeException{

    public ExecuteException(String message) {
        super(message);
    }

    public ExecuteException(String message, Throwable cause) {
        super(message, cause);
    }
}
