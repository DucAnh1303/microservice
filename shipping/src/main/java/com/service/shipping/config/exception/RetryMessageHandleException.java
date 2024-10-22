package com.service.shipping.config.exception;

public class RetryMessageHandleException extends RuntimeException {
    private String message;

    public RetryMessageHandleException(String message) {
        super(message);  // Pass message to RuntimeException
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
