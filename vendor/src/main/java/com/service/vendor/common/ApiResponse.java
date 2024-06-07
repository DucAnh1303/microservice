package com.service.vendor.common;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Page;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ApiResponse<T> {
    private int code;
    private T data;

    public static ApiResponse<?> res(final Object data, final int code) {
        return new ApiResponse<>(code, data);
    }

    public static ApiResponse<?> res(final Page<?> data, final int code) {
        return new ApiResponse<>(code, new Pagination<>(data));
    }
}
