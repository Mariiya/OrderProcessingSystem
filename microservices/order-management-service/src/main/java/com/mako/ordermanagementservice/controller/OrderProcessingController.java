package com.mako.ordermanagementservice.controller;

import com.mako.model.Order;
import com.mako.ordermanagementservice.service.OrderProcessingService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/orders/v1")
public class OrderProcessingController {

    private OrderProcessingService orderService;

    public OrderProcessingController(OrderProcessingService orderService) {
        this.orderService = orderService;
    }

    @PostMapping()
    public ResponseEntity<String> createOrder(@RequestBody Order order) {
        Order newOrder = orderService.createOrder(order);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(newOrder.getId())
                .toUri();
        return ResponseEntity.created(location).build();
    }

}
