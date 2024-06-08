package com.service.shipping.consumer;

import com.data.entity.ShippingEntity;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.service.shipping.repository.ShippingRepository;
import com.service.shipping.request.ShippingRequest;
import com.service.shipping.request.ShippingRequestConverter;
import com.service.shipping.response.ShippingResponse;
import com.service.shipping.response.ShippingResponseConverter;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ShippingConsummer {

    private final ObjectMapper objectMapper;
    private final ShippingRepository repository;

    @KafkaListener(topics = "order", groupId = "order-group-1")
    public ShippingResponse getMessage(String message) throws JsonProcessingException {
        ShippingRequest request = objectMapper.readValue(message, ShippingRequest.class);
        ShippingEntity receivedOrder = repository.save(ShippingRequestConverter.receive(request));
        System.out.println("Consumed message in order-group-1: " + message);
        return ShippingResponseConverter.response(receivedOrder);
    }
}
