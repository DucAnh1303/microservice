package org.service.order.web.controller;

import com.data.entity.OrderEntity;
import lombok.extern.slf4j.Slf4j;
import org.service.order.common.SnackRequest;
import org.service.order.domain.convert.OrderCommandConverter;
import org.service.order.domain.usecase.OrderCommandUsecase;
import org.service.order.web.request.OrderRequest;
import org.service.order.web.response.OrderResponse;
import org.service.support.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/order")
@Slf4j
public class OrderController {

    @Autowired
    private OrderCommandUsecase commandUsecase;

    @PostMapping("/add")
    public ApiResponse<?> sendMessage(@SnackRequest final OrderRequest request) {
        OrderResponse response = commandUsecase.execute(OrderCommandConverter.to(request));
        return new ApiResponse<>(response, HttpStatus.CREATED);
    }
}
