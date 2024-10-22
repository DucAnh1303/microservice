package com.service.shipping.config.producer;

import com.fasterxml.jackson.databind.ObjectMapper;
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

    public void send(String message) {
        kafkaTemplate1.send("order", message);
        kafkaTemplate1.flush();
        System.out.println("message success !");
    }
}
