package com.mako.paymentprocessingservice.service;

import com.mako.dto.OrderDTO;

public interface PaymentProcessingService {

    void processPayment(OrderDTO order);
}
