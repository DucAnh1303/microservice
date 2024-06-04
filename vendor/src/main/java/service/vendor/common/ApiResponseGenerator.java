package service.vendor.common;

import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;

public class ApiResponseGenerator {
    private ApiResponseGenerator() {
        throw new UnsupportedOperationException();
    }

//    public static <T> ApiResponse<T>  res(final T body, final HttpStatus status) {
//        return new ApiResponse<>(body,status);
//    }
//    public static <T> ApiResponse<Pagination<T>>  res(Page<T> body, HttpStatus status) {
//        return new ApiResponse<>(new Pagination<>(body),status);
//    }
}
