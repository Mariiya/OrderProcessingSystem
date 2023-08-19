package com.mako.inventoryservice.controller;

import com.mako.dto.OrderDTO;
import com.mako.inventoryservice.model.Order;
import com.mako.inventoryservice.service.InventoryService;
import com.mako.inventoryservice.utils.Converters;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/inventory-service")
public class InventoryController {
    private static final Logger LOGGER = LoggerFactory.getLogger(InventoryController.class);
    private final InventoryService service;

    @Autowired
    public InventoryController(InventoryService service) {
        this.service = service;
    }

    @PostMapping("/orders")
    public ResponseEntity<OrderDTO> storeOrder(@RequestBody OrderDTO orderDTO) {
        LOGGER.info(String.format("Order event received -> %s", orderDTO));

        Order newOrder = service.storeOrder(orderDTO);
        service.updateStockByOrder(newOrder);

        return ResponseEntity.ok(Converters.convertToOrderDTO(newOrder));
    }

}
