package com.service.order.request;

import com.service.microservice.data.entity.OrderEntity;
import lombok.experimental.UtilityClass;

import java.time.LocalDateTime;

@UtilityClass
public class OrderRequestConverter {

    public static OrderEntity set(OrderRequest request) {
        OrderEntity set = new OrderEntity();
        set.setName(request.getName());
        set.setPhone(request.getPhone());
        set.setAddress(request.getAddress());
        set.setProductId(request.getProductId());
        set.setNumber(request.getNumber());
        set.setCreatedDate(LocalDateTime.now());
        return set;
    }
}
