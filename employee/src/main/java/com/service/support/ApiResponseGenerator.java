package com.service.support;

import org.springframework.http.HttpStatus;

public class ApiResponseGenerator {

    private ApiResponseGenerator() {
        throw new UnsupportedOperationException();
    }

    public static ApiResponse<Void> success(final HttpStatus status) {
        return new ApiResponse<>(status);
    }

    public static <D> ApiResponse<D> success(final D data, final HttpStatus status) {
        return new ApiResponse<>(data, status);
    }
    public static <D> ApiResponse<Page<D>> success(
            final org.springframework.data.domain.Page<D> data, final HttpStatus status) {
        return new ApiResponse<>(new Page<>(data), status);
    }
}
