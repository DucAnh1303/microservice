package com.service.shipping.config.consumer;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.listener.adapter.ConsumerRecordMetadata;
import org.springframework.stereotype.Component;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

@Component
@RequiredArgsConstructor
@Slf4j
public class ConsumerMessage {

    private final ObjectMapper objectMapper;

    @KafkaListener(
            id = "shipping",
            topics = "order-name",
            containerFactory = "consumerConfig",concurrency = "2")
    public void receiveMessage(String message, ConsumerRecordMetadata consumerRecordMetadata) throws InterruptedException {
        log.info("Received message '{}' from Partition {} at Offset {}",
                message, consumerRecordMetadata.partition(), consumerRecordMetadata.offset());

//        ScheduledExecutorService scheduler = Executors.newSingleThreadScheduledExecutor();
//
//        scheduler.schedule(() -> {
//            log.info("Processed message '{}' from Partition {}",
//                    message, consumerRecordMetadata.partition());
//            scheduler.shutdown();
//        }, 2, TimeUnit.SECONDS);
    }

}
