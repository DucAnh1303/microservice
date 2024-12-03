package com.service.microservice.support;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

import java.io.Serializable;

@Getter
public class BaseResponse<B> implements Serializable {

    private int code;
    private String status;
    private B body;

    public BaseResponse(final B body, final String status) {
        this.body = body;
        this.status = status;
    }

    public BaseResponse(final int code, final String status, final B body) {
        this.code = code;
        this.status = status;
        this.body = body;
    }

    public BaseResponse(final String status) {
        this.status = status;
    }

    @Getter
    @AllArgsConstructor
    public static class FailureBody implements Serializable {

        private String code;
        private String message;

        public FailureBody(final String message) {
            this.message = message;
        }
    }
}
