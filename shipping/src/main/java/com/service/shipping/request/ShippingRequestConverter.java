package com.service.shipping.request;

import com.service.microservice.data.entity.ShippingEntity;
import lombok.experimental.UtilityClass;

import java.time.LocalDateTime;

@UtilityClass
public class ShippingRequestConverter {

    public static ShippingEntity receive(final ShippingRequest request) {
        ShippingEntity received = new ShippingEntity();
        received.setName(request.getName());
        received.setPhone(request.getPhone());
        received.setAddress(request.getAddress());
        received.setOrderId(request.getOrderId());
        received.setProductId(request.getProductId());
        received.setNumber(request.getNumber());
        received.setCreatedDate(LocalDateTime.now());
        return received;
    }
}
