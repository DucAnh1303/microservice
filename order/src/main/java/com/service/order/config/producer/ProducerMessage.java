package com.service.order.config.producer;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.apache.kafka.common.Uuid;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class ProducerMessage {


    @Qualifier("kafkaTemplate1")
    private final KafkaTemplate<String, String> kafkaTemplate1;

    private final ObjectMapper objectMapper;

    public void send(String message) {
        kafkaTemplate1.send("order-name", Uuid.randomUuid().toString(), message);
        System.out.println("message success !");
    }

}
