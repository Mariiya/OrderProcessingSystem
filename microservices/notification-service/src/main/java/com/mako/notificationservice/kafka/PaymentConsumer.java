package com.mako.notificationservice.kafka;

import com.mako.event.PaymentEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class PaymentConsumer {

    private static final Logger LOGGER = LoggerFactory.getLogger(PaymentConsumer.class);

    @KafkaListener(topics = "${kafka.order.payment.topic.name}")
    public void consumer(PaymentEvent event) {
        LOGGER.info(String.format("Payment event received -> %s", event));
    }

}
