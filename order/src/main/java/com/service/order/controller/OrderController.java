package com.service.order.controller;

import com.service.order.request.OrderRequest;
import com.service.order.response.OrderResponse;
import com.service.order.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/order")
public class OrderController {

    private final OrderService orderService;

    @PostMapping("/add")
    public OrderResponse addData(@RequestBody OrderRequest request){
        return orderService.order(request);
    }
}
