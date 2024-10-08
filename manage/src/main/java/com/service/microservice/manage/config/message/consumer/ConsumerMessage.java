package com.service.microservice.manage.config.message.consumer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.service.microservice.manage.repository.AccountRepository;
import com.service.microservice.manage.response.AuthResponse;
import com.service.microservice.manage.response.converter.AccountConverter;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ConsumerMessage {

    private final ObjectMapper objectMapper;
    private final AccountRepository accountRepository;

    @KafkaListener(topics = "account", groupId = "${spring.kafka.consumer.group-id}")
    public void noticeShipping(String message) throws JsonProcessingException {
        AuthResponse orderReceipt = objectMapper.readValue(message, AuthResponse.class);
        accountRepository.save(AccountConverter.to(orderReceipt));
        System.out.println("Consumed message in account: " + objectMapper.writeValueAsString(orderReceipt));
    }

    @KafkaListener(topics = "account-id-1", groupId = "${spring.kafka.consumer.group-id}")
    public void noticeMessage(String message) throws JsonProcessingException {
        System.out.println("Consumed message in account: " + message);
    }
}
