package com.service.order.response;

import com.data.entity.OrderEntity;
import lombok.experimental.UtilityClass;

@UtilityClass
public class OrderResponseConverter {

    public static OrderResponse get(OrderEntity entity){
        return OrderResponse.builder()
                .id(entity.getId())
                .name(entity.getName())
                .phone(entity.getPhone())
                .address(entity.getAddress())
                .number(entity.getNumber())
                .productId(entity.getProductId())
                .build();
    }
}
