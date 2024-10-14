package com.service.microservice.auth.config.message;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.service.microservice.auth.converter.AuthResConverter;
import com.service.microservice.auth.entities.AuthEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProducerMessage {


    @Qualifier("kafkaTemplate1")
    private final KafkaTemplate<String, String> kafkaTemplate1;

    private final ObjectMapper objectMapper;

    public void saveAuth(AuthEntity order) {
        try {
            System.out.println("message success !");
            kafkaTemplate1.send("account", objectMapper.writeValueAsString(AuthResConverter.from(order)));
            kafkaTemplate1.flush();
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e.getMessage());
        }
    }
}
