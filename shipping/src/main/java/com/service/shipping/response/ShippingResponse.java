package com.service.shipping.response;

import lombok.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ShippingResponse {

    private Long id;
    private String name;
    private String address;
    private Integer productId;
    private Integer number;
    private String phone;
    private Long orderId;
}
