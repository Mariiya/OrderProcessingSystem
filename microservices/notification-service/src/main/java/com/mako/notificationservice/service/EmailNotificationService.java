package com.mako.notificationservice.service;

public interface EmailNotificationService {

    void sendMessage(String to, String subject, String text);
}
