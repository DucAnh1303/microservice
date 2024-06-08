package com.service.shipping.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class ShippingRequest {
    private Long orderId;
    private String name;
    private String address;
    private Integer productId;
    private Integer number;
    private String phone;
}
