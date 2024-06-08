package com.service.order.service;

import com.service.order.request.OrderRequest;
import com.service.order.response.OrderResponse;

public interface OrderService {

    OrderResponse order(OrderRequest request);
}
