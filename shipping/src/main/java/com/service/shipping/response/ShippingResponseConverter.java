package com.service.shipping.response;

import com.data.entity.ShippingEntity;
import lombok.experimental.UtilityClass;

@UtilityClass
public class ShippingResponseConverter {

    public static ShippingResponse response(ShippingEntity entity){
        return ShippingResponse.builder()
                .id(entity.getId())
                .phone(entity.getPhone())
                .name(entity.getName())
                .address(entity.getAddress())
                .productId(entity.getProductId())
                .orderId(entity.getOrderId())
                .number(entity.getNumber())
                .build();
    }
}
