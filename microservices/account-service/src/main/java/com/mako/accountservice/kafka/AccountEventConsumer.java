package com.mako.accountservice.kafka;

import com.mako.accountservice.service.UserService;
import com.mako.accountservice.utils.Converter;
import com.mako.event.UserEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class AccountEventConsumer {

    private static final Logger LOGGER = LoggerFactory.getLogger(AccountEventConsumer.class);
    private final UserService userService;

    public AccountEventConsumer(UserService userService) {
        this.userService = userService;
    }

    @KafkaListener(topics = "${kafka.user.topic.name}",
            groupId = "${spring.kafka.consumer.group-id}")
    public void consumeAccountEvent(UserEvent event){
        userService.save(Converter.convertToUserEntity(event.getUser()));
    }

}
