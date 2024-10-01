package com.service.order.service;

import com.service.microservice.data.entity.OrderEntity;
import com.service.order.kafka.ProducerMessage;
import com.service.order.repository.OrderRepository;
import com.service.order.request.OrderRequest;
import com.service.order.request.OrderRequestConverter;
import com.service.order.response.OrderResponse;
import com.service.order.response.OrderResponseConverter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final ProducerMessage message;
    private final OrderRepository repository;

    @Override
    public OrderResponse order(OrderRequest request) {
        OrderEntity add = repository.save(OrderRequestConverter.set(request));
        message.ReceiveOrderNotification(add);
        return OrderResponseConverter.get(add);
    }
}
