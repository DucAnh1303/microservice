package com.service.microservice.support;

import org.springframework.data.domain.Page;

public class ApiResponseGenerator {

    private ApiResponseGenerator() {
        throw new UnsupportedOperationException();
    }

    public static BaseResponse<Void> success(final String status) {
        return new BaseResponse<>(status);
    }

    public static <D> BaseResponse<D> success(final int code, final String status, final D data) {
        return new BaseResponse<>(code, status, data);
    }

//    public static <D> BaseResponse<BasePage<D>> success(final int code, final String status, final org.springframework.data.domain.Page<D> data) {
//        return new BaseResponse<>(code, status, new BasePage<>(data));
//    }


}
