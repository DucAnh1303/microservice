package com.service.shipping.config.exception;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class ErrorResponse {
    private int code;
    private String message;
}
