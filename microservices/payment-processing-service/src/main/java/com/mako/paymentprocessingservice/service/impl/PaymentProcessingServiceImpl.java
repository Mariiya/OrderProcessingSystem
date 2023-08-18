package com.mako.paymentprocessingservice.service.impl;

import com.mako.dto.EventType;
import com.mako.dto.OrderDTO;
import com.mako.dto.PaymentInfoDTO;
import com.mako.event.PaymentEvent;
import com.mako.paymentprocessingservice.kafka.PaymentProducer;
import com.mako.paymentprocessingservice.service.PaymentProcessingService;
import com.mako.utils.EventHelper;
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
    public void processPayment(OrderDTO order) {
        //calculate total

        //perform transactions...

        LOGGER.info(String.format("Payment processed -> " + order.toString()));

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        PaymentInfoDTO info = new PaymentInfoDTO(order, "Payment processed successfully");
        producer.sendMessage(new PaymentEvent(EventType.PAYMENT_PROCESSED, EventHelper.generateCorrelationId(), info));
    }
}
