package com.mako.paymentprocessingservice.service.impl;

import com.mako.model.EventType;
import com.mako.model.Order;
import com.mako.model.PaymentInfo;
import com.mako.model.events.PaymentEvent;
import com.mako.paymentprocessingservice.kafka.PaymentProducer;
import com.mako.paymentprocessingservice.service.PaymentProcessingService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class PaymentProcessingServiceImpl implements PaymentProcessingService {

    private static final Logger LOGGER = LoggerFactory.getLogger(PaymentProcessingServiceImpl.class);

    private PaymentProducer producer;

    public PaymentProcessingServiceImpl(PaymentProducer producer) {
        this.producer = producer;
    }

    @Override
    public void processPayment(Order order) {
        //calculate total

        //perform transactions...

        LOGGER.info(String.format("Payment processed -> " + order.toString()));

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        PaymentInfo info = new PaymentInfo(order, "Payment processed successfully");
        producer.sendMessage(new PaymentEvent(info, EventType.PROCESSED));
    }
}
