package com.service.support;

import org.springframework.http.HttpStatus;

public class ApiResponseGenerator {

    private ApiResponseGenerator() {
        throw new UnsupportedOperationException();
    }

    public static BaseResponse<Void> success(final HttpStatus status) {
        return new BaseResponse<>(status);
    }

    public static <D> BaseResponse<D> success(final D data, final HttpStatus status) {
        return new BaseResponse<>(data, status);
    }
    public static <D> BaseResponse<Page<D>> success(
            final org.springframework.data.domain.Page<D> data, final HttpStatus status) {
        return new BaseResponse<>(new Page<>(data), status);
    }
}
