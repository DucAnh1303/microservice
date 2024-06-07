package com.service.order.service;

import com.service.order.request.OrderRequest;
import com.service.order.response.OrderResponse;

public interface OrderService {
    public OrderResponse order(OrderRequest request);
}
