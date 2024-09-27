package com.service.elasticsearch.common;

import lombok.*;

@Getter
@Setter
@Builder
public class ExceptionResponse  {
    private int code;
    private String message;

    public ExceptionResponse(int code, String message) {
        this.code = code;
        this.message = message;
    }
}
