package org.service.order.producer;

import com.data.entity.OrderEntity;
import lombok.RequiredArgsConstructor;
import org.service.order.web.converter.OrderResponseConverter;
import org.service.order.web.response.OrderResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProducerMessage {

    @Autowired
    private KafkaTemplate<String, OrderResponse> kafkaTemplate;

    public void order(OrderEntity order) {
        kafkaTemplate.send("order", OrderResponseConverter.to(order));
    }
}
