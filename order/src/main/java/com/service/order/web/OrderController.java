package com.service.order.web;

import com.service.order.config.producer.ProducerMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.ExecutorService;

@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private ProducerMessage producerMessage;


    @PostMapping("/send-message")
    public String send() {
            // Gửi message trong từng luồng
            String message = "Message from thread ";
            producerMessage.send(message);
            System.out.println("Thread  sent: " + message);
        return "Messages are being sent by multiple threads.";
    }


    @PostMapping("/execute")
    public ResponseEntity<?> order(){
        return new ResponseEntity<>("order success", HttpStatus.CREATED);
    }
}
