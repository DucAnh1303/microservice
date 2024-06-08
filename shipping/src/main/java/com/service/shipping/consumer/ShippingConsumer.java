package com.service.shipping.consumer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.service.shipping.request.ShippingRequest;
import com.service.shipping.response.ShippingResponse;
import com.service.shipping.service.ShippingService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ShippingConsumer {

    private final ObjectMapper objectMapper;
    private final ShippingService service;

    @KafkaListener(topics = "order", groupId ="${spring.kafka.consumer-group1.group-id}")
    public void noticeShipping(String message) throws JsonProcessingException {
        ShippingRequest orderReceipt = objectMapper.readValue(message, ShippingRequest.class);
        ShippingResponse response = service.orderReceipt(orderReceipt);
        System.out.println("Consumed message in order-group-1: " + objectMapper.writeValueAsString(response));
    }
}
