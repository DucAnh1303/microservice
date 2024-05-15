package org.service.shipping.events;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import org.service.shipping.domain.usecase.UserShippingCommandService;
import org.service.shipping.web.ShippingResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class KafkaConsumer {

    @Autowired
    public Gson gson;

    @Autowired
    public UserShippingCommandService service;

    @KafkaListener(topics = "order", groupId = "orders")
    public void handleOrder(String shipping) throws JsonProcessingException {
        ShippingResponse shippingResponse = new ObjectMapper().readValue(shipping, ShippingResponse.class);
        service.execute(shippingResponse);
        System.out.println("Shipping received: " + gson.toJson(shipping));
    }

}
