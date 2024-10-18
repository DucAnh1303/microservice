package com.service.microservice.manage.config.message.producer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.service.microservice.manage.entity.account.AccountEntity;
import com.service.microservice.manage.response.converter.AccountConverter;
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

    public void send(AccountEntity order,String password) {
        try {
            kafkaTemplate1.send("account", objectMapper.writeValueAsString(AccountConverter.from(order,password)));
            kafkaTemplate1.flush();
            System.out.println("message success !");
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e.getMessage());
        }
    }
}
