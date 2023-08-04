package com.mako.ordermanagementservice.service.impl;

import com.mako.model.EventType;
import com.mako.model.Order;
import com.mako.model.events.OrderEvent;
import com.mako.ordermanagementservice.kafka.OrderProducer;
import com.mako.ordermanagementservice.service.OrderProcessingService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class OrderProcessingServiceImpl implements OrderProcessingService {

    private static final Logger LOGGER = LoggerFactory.getLogger(OrderProcessingServiceImpl.class);
    private final OrderProducer orderProducer;

    public OrderProcessingServiceImpl(OrderProducer orderProducer) {
        this.orderProducer = orderProducer;
    }

    @Override
    public Order createOrder(Order order) {
        //validate
        //save to DB

        OrderEvent orderEvent = new OrderEvent();
        orderEvent.setStatus(EventType.CREATE_ORDER);
        orderEvent.setOrder(order);

        orderProducer.sendMessage(orderEvent);

        return order;
    }
}
