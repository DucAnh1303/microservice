package com.service.order.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Builder
public class OrderResponse {
    private Long id;
    private String name;
    private String address;
    private Integer productId;
    private Integer number;
    private String phone;
}
