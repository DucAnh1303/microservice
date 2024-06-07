package com.service.order.request;

import com.data.entity.OrderEntity;
import lombok.experimental.UtilityClass;

@UtilityClass
public class OrderRequestConverter {

    public static OrderEntity set(OrderRequest request) {
        OrderEntity set = new OrderEntity();
        set.setName(request.getName());
        set.setPhone(request.getPhone());
        set.setAddress(request.getAddress());
        set.setProductId(request.getProductId());
        set.setNumber(request.getNumber());
        return set;
    }
}
