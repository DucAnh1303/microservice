package com.service.microservice.manage.exceptionhandle;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Service;

@Getter
@Setter
@AllArgsConstructor
public class ErrorResponse {

    private int code;
    private String message;
    private Object error;
}
