package com.mako.ordermanagementservice.service.impl;

import com.mako.dto.EventType;
import com.mako.dto.OrderDTO;
import com.mako.event.OrderEvent;
import com.mako.ordermanagementservice.kafka.OrderProducer;
import com.mako.ordermanagementservice.service.OrderProcessingService;
import com.mako.utils.EventHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import proxy.InventoryServiceProxy;

@Service
public class OrderProcessingServiceImpl implements OrderProcessingService {

    private static final Logger LOGGER = LoggerFactory.getLogger(OrderProcessingServiceImpl.class);
    private final OrderProducer orderProducer;

    private final InventoryServiceProxy inventoryServiceProxy;

    @Autowired
    public OrderProcessingServiceImpl(OrderProducer orderProducer, InventoryServiceProxy inventoryServiceProxy) {
        this.orderProducer = orderProducer;
        this.inventoryServiceProxy = inventoryServiceProxy;
    }

    @Override
    public OrderDTO createOrder(OrderDTO order) {
        OrderDTO newOrder = inventoryServiceProxy.storeOrder(order).getBody();

        OrderEvent orderEvent = new OrderEvent();
        orderEvent.setEventType(EventType.CREATE_ORDER);
        orderEvent.setOrder(newOrder);
        orderEvent.setCorrelationId(EventHelper.generateCorrelationId());

        orderProducer.sendMessage(orderEvent);

        return order;
    }
}
