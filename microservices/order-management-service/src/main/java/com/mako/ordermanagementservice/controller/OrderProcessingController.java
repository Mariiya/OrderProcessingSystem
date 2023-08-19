package com.mako.ordermanagementservice.controller;

import com.mako.dto.OrderDTO;
import com.mako.ordermanagementservice.service.OrderProcessingService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/order-management-service/orders/v1")
public class OrderProcessingController {

    private OrderProcessingService orderService;

    public OrderProcessingController(OrderProcessingService orderService) {
        this.orderService = orderService;
    }

    @PostMapping()
    public ResponseEntity<String> createOrder(@RequestBody OrderDTO order) {
        OrderDTO newOrder = orderService.createOrder(order);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(newOrder.getId())
                .toUri();
        return ResponseEntity.created(location).build();
    }

}
