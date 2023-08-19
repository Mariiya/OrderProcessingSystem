package com.mako.ordermanagementservice.proxy;

import com.mako.dto.OrderDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "${microservices.inventory-service.name}")
public interface InventoryServiceProxy {

    @PostMapping("/inventory-service/orders")
    ResponseEntity<OrderDTO> storeOrder(@RequestBody OrderDTO orderDTO);
}
