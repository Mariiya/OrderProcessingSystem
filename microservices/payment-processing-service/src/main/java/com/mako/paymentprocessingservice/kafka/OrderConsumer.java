package com.mako.paymentprocessingservice.kafka;

import com.mako.event.OrderEvent;
import com.mako.paymentprocessingservice.service.PaymentProcessingService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class OrderConsumer {

    private static final Logger LOGGER = LoggerFactory.getLogger(OrderConsumer.class);

    private PaymentProcessingService service;

    public OrderConsumer(PaymentProcessingService service) {
        this.service = service;
    }

    @KafkaListener(topics = "${kafka.order.topic.name}",
            groupId = "${spring.kafka.consumer.group-id}")
    public void consume(OrderEvent orderEvent) {
        LOGGER.info(String.format("Order event received -> %s", orderEvent));

        service.processPayment(orderEvent.getOrder());
    }

}
