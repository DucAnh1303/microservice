package com.service.microservice.auth.config.message;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.service.microservice.auth.converter.AuthResConverter;
import com.service.microservice.auth.entities.AuthEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProducerMessage {

    private final KafkaTemplate<String, String> message;
    private final ObjectMapper objectMapper;

    public void ReceiveOrderNotification(AuthEntity order) {
        try {
            System.out.println("message success !");
            message.send("account", objectMapper.writeValueAsString(AuthResConverter.from(order)));
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    public void ReceiveMessage(String send) {
        System.out.println("message success !");
        message.send("account-id-1", send);

    }
}
