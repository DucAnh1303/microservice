package com.service.order.kafka;

import com.service.microservice.data.entity.OrderEntity;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.service.order.response.OrderResponseConverter;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProducerMessage {

    private final KafkaTemplate<String, String> message;
    private final ObjectMapper objectMapper;

    public void ReceiveOrderNotification(OrderEntity order) {
        try {
            System.out.println("message success !");
            message.send("order", objectMapper.writeValueAsString(OrderResponseConverter.get(order)));
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e.getMessage());
        }
    }
}
