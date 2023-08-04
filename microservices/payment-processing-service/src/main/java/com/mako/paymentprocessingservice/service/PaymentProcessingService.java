package com.mako.paymentprocessingservice.service;

import com.mako.model.Order;

public interface PaymentProcessingService {

    void processPayment(Order order);
}
