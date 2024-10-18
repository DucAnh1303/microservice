package com.service.microservice.auth.config.message.consumer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.service.microservice.auth.converter.AuthResConverter;
import com.service.microservice.auth.repository.AuthRepository;
import com.service.microservice.auth.response.AuthResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ConsumerMessage {

    private final ObjectMapper objectMapper;
    private final AuthRepository authRepository;

    @KafkaListener(topics = "account", groupId = "${spring.kafka.consumer.group-id-1}", containerFactory = "kafkaListenerContainerFactory1")
    public void receiveInforToSaveAccount(String message) throws JsonProcessingException {
        AuthResponse orderReceipt = objectMapper.readValue(message, AuthResponse.class);
        authRepository.save(AuthResConverter.to(orderReceipt));
        System.out.println("Consumed message in account: " + objectMapper.writeValueAsString(orderReceipt));
    }

}
