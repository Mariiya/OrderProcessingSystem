package com.mako.notificationservice.kafka;

import com.mako.event.PasswordChangeEvent;
import com.mako.notificationservice.service.EmailNotificationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class UserConsumer {

    private static final Logger LOGGER = LoggerFactory.getLogger(OrderConsumer.class);

    @Value("${spring.mail.username}")
    private String emailFrom;

    private final EmailNotificationService emailService;

    public UserConsumer(EmailNotificationService emailService) {
        this.emailService = emailService;
    }

    @KafkaListener(topics = "${kafka.account.topic.name}",
            groupId = "${spring.kafka.consumer.group-id}")
    public void consumePasswordChangeRequest(PasswordChangeEvent event) {
        LOGGER.info(String.format("Password change event received -> %s", event));
        emailService.sendMessage(emailFrom, event.getPasswordChangeDTO().getUserEmail(),
                String.format("Token: %s", event.getPasswordChangeDTO().getToken()));
    }


}
