package com.mako.accountservice.kafka;

import com.mako.event.UserEvent;
import org.apache.kafka.clients.admin.NewTopic;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;


@Service
public class AccountActionsProducer {

    private static final Logger LOGGER = LoggerFactory.getLogger(AccountActionsProducer.class);
    private final NewTopic topic;
    private final KafkaTemplate<String, UserEvent> kafkaTemplate;

    @Autowired
    public AccountActionsProducer(NewTopic topic, KafkaTemplate<String, UserEvent> kafkaTemplate) {
        this.topic = topic;
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendMessage(UserEvent userEvent) {
        Message<UserEvent> message = MessageBuilder.withPayload(userEvent)
                .setHeader(KafkaHeaders.TOPIC, topic)
                .build();
        kafkaTemplate.send(message);
        LOGGER.info(String.format("User event sent -> %s", userEvent));
    }
}
