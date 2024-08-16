package com.service.assync.response;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class Response {
    private int code;
    private String message;
    private Object data;
}
