package service.vendor.config;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.http.HttpStatusCode;

public abstract class ApiResponseMixin<T> {
    @JsonCreator
    public ApiResponseMixin(@JsonProperty("body") T body, @JsonProperty("status") HttpStatusCode status) {
        // Constructor chỉ để Jackson hiểu, sẽ không thực sự được sử dụng
    }
}
