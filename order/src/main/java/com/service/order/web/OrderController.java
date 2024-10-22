package com.service.order.web;

import com.service.order.config.producer.ProducerMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OrderController {

    @Autowired
    private ProducerMessage producerMessage;

    @PostMapping("/send-message")
    public String send() {
        for (int i = 0; i < 10; i++) {
            int threadNumber = i;
            // Tạo và chạy một luồng mới
            Thread thread = new Thread(() -> {
                try {
                    // Gửi message trong từng luồng
                    String message = "Message from thread " + threadNumber;
                    producerMessage.send(message);
                    System.out.println("Thread " + threadNumber + " sent: " + message);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });

            // Bắt đầu luồng
            thread.start();
        }
        return "Messages are being sent by multiple threads.";
    }
}
