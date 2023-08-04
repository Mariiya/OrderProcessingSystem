package com.mako.paymentprocessingservice.configuration;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class KafkaTopicConfig {

    @Value("${kafka.order.payment.topic.name}")
    private String topicName;

    @Bean
    public NewTopic orderPaymentTopic(){
        return TopicBuilder.name(topicName).build();
    }

}
