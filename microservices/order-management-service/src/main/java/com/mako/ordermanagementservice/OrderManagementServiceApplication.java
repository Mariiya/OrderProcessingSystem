package com.mako.ordermanagementservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import com.mako.ordermanagementservice.proxy.InventoryServiceProxy;

@SpringBootApplication
@EnableFeignClients(clients = {InventoryServiceProxy.class})
public class OrderManagementServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(OrderManagementServiceApplication.class, args);
    }

}
