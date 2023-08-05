package com.mako.paymentprocessingservice.kafka;

import com.mako.dto.PaymentInfoDTO;
import com.mako.event.PaymentEvent;
import org.apache.kafka.clients.admin.NewTopic;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

@Service
public class PaymentProducer {
    private static final Logger LOGGER = LoggerFactory.getLogger(PaymentProducer.class);

    private NewTopic topic;
    private KafkaTemplate<String, PaymentInfoDTO> kafkaTemplate;

    public PaymentProducer(NewTopic topic, KafkaTemplate<String, PaymentInfoDTO> kafkaTemplate) {
        this.topic = topic;
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendMessage(PaymentEvent event) {
        Message<PaymentEvent> message = MessageBuilder
                .withPayload(event)
                .setHeader(KafkaHeaders.TOPIC, topic.name())
                .build();
        kafkaTemplate.send(message);

        LOGGER.info(String.format("Payment event sent -> %s", event));
    }
}
