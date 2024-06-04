package service.vendor.common;
import lombok.Getter;
import org.springframework.http.HttpStatus;


public record ApiResponse<T>(T data, HttpStatus code) {
}
