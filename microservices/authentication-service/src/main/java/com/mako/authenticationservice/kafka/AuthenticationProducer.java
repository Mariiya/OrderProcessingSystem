package com.mako.authenticationservice.kafka;

import com.mako.event.PasswordChangeEvent;
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
public class AuthenticationProducer {

    private static final Logger LOGGER = LoggerFactory.getLogger(AuthenticationProducer.class);
    private final NewTopic topic;
    private final KafkaTemplate<String, UserEvent> kafkaTemplate;

    @Autowired
    public AuthenticationProducer(NewTopic topic, KafkaTemplate<String, UserEvent> kafkaTemplate) {
        this.topic = topic;
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendMessage(UserEvent userEvent) {
        Message<UserEvent> message = MessageBuilder.withPayload(userEvent)
                .setHeader(KafkaHeaders.TOPIC, topic.name())
                .build();
        kafkaTemplate.send(message);
        LOGGER.info(String.format("User event sent -> %s", userEvent));
    }

    public void sendPasswordChangeMessage(PasswordChangeEvent event) {
        Message<PasswordChangeEvent> message = MessageBuilder.withPayload(event)
                .setHeader(KafkaHeaders.TOPIC, topic.name())
                .build();
        kafkaTemplate.send(message);
        LOGGER.info(String.format("Password change event sent -> %s", event));
    }
}
