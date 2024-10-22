package com.service.shipping.config.consumer;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.service.shipping.config.exception.RetryMessageHandleException;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ConsumerMessage {

    private final ObjectMapper objectMapper;

    @KafkaListener(topics = "order", groupId = "${spring.kafka.consumer.group-id-1}", containerFactory = "kafkaListenerContainerFactory1")
    public void receiveMessage(String message) {
        try {
            if (message.equals("Message from thread 7")) {
                throw new RetryMessageHandleException("Retry this message");
            } else {
                System.out.println("Successfully handled message: " + message);
            }
        } catch (RetryMessageHandleException exception) {
            System.out.println("Caught exception: " + exception.getMessage());
            throw exception;
        }
    }

}
