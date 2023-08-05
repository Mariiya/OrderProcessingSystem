package com.mako.inventoryservice.kafka;

import com.mako.inventoryservice.service.InventoryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderConsumer {

    private static final Logger LOGGER = LoggerFactory.getLogger(OrderConsumer.class);

    private InventoryService inventoryService;

    @Autowired
    public OrderConsumer(InventoryService inventoryService) {
        this.inventoryService = inventoryService;
    }

/*    @KafkaListener(topics = "${kafka.order.topic.name}",
            groupId = "${spring.kafka.consumer.group-id}")
    public void consume(OrderEvent orderEvent) {
        LOGGER.info(String.format("Order event received -> %s", orderEvent));

        if (EventType.CREATE_ORDER.equals(orderEvent.getStatus())) {
            Order newOrder = inventoryService.storeOrder(orderEvent.getOrder());
            inventoryService.updateStockByOrder(newOrder);
        }

    }*/

}
