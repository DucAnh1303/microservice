package org.service.order.domain.usecase;

import com.data.entity.OrderEntity;
import com.google.gson.Gson;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.service.order.domain.command.OrderCommand;
import org.service.order.domain.repository.OrderRepository;
import org.service.order.producer.ProducerMessage;
import org.service.order.web.converter.OrderResponseConverter;
import org.service.order.web.response.OrderResponse;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
@Slf4j
public class OrderCommandUsecase {

    private final OrderRepository repository;
    private final ProducerMessage producerMessage;
    private final Gson gson;

    @Transactional
    public OrderResponse execute(final OrderCommand command) {
        OrderEntity order = new OrderEntity();
        order.setName(command.getName());
        order.setNumber(command.getNumber());
        order.setDescription(command.getDescription());
        order.setCreatedDate(LocalDateTime.now());
        order = repository.save(order);
        log.info("Infor order:" + gson.toJson(order));
        producerMessage.order(order);
        return OrderResponseConverter.to(order);
    }
}
