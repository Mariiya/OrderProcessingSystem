package com.mako.ordermanagementservice.controller;

import com.mako.model.Order;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/orders/v1")
public class OrderProcessingController {

    public ResponseEntity<String> createOrder(@RequestBody Order order) {
        return ResponseEntity.ok("Order created");
    }

}
