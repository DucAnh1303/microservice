package com.service.order.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderRequest {
    private String name;
    private String address;
    private Integer productId;
    private Integer number;
    private String phone;
}
