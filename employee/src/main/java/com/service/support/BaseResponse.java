package com.service.support;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.io.Serializable;

@Getter
public class BaseResponse<B> extends ResponseEntity<B> implements Serializable {
    public BaseResponse(final HttpStatus status) {
        super(status);
    }

    public BaseResponse(final B body, final HttpStatus status) {
        super(body, status);
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
