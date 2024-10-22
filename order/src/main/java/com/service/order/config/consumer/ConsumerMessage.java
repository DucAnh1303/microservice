package com.service.order.config.consumer;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ConsumerMessage {

    private final ObjectMapper objectMapper;

//    @KafkaListener(topics = "account", groupId = "${spring.kafka.consumer.group-id-1}", containerFactory = "kafkaListenerContainerFactory1")
//    public void receiveInforToSaveAccount(String message) throws JsonProcessingException {
//        AuthResponse orderReceipt = objectMapper.readValue(message, AuthResponse.class);
//        System.out.println("Consumed message in account: " + objectMapper.writeValueAsString(orderReceipt));
//    }

}
