package com.service.microservice.support;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.io.Serializable;

@Getter
public class BaseResponse<B> implements Serializable {

    private B body;
    private HttpStatus status;

    public BaseResponse(final B body, final HttpStatus status) {
        this.body = body;
        this.status = status;
    }

    public BaseResponse(final HttpStatus status) {
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
