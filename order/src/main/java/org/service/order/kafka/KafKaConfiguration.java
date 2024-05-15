package org.service.order.kafka;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class KafKaConfiguration {

    @Bean
    public NewTopic order(){
        return new NewTopic("order",1,(short) 1);
    }

    @Bean
    public NewTopic shipping(){
        return new NewTopic("shipping",1,(short) 1);
    }
}
