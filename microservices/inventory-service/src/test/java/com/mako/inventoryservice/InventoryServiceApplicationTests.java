package com.mako.inventoryservice;

import com.mako.dto.*;
import com.mako.inventoryservice.jpa.OrderRepository;
import com.mako.inventoryservice.model.Order;
import com.mako.inventoryservice.service.InventoryService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigInteger;
import java.util.Collections;

import static junit.framework.TestCase.assertNotNull;

@RunWith(SpringRunner.class)
@SpringBootTest
public class InventoryServiceApplicationTests {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private InventoryService inventoryService;

    @Test
    public void testOrderProcessing() {
        // sample Order
        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setAccountId(BigInteger.valueOf(123L));
        PurchaseDTO purchase = new PurchaseDTO();
        purchase.setProduct(new ProductDTO(BigInteger.valueOf(1L), "Product_1",
                new ProductTypeDTO(BigInteger.valueOf(3L), null, "Bed"), 2, 3));
        orderDTO.setPurchases(Collections.singleton(purchase));

        Order newOrder = inventoryService.storeOrder(orderDTO);
        inventoryService.updateStockByOrder(newOrder);

        // Verify that the order is saved to the database
        Order savedOrder = orderRepository.findById(newOrder.getId()).orElse(null);
        System.out.println(savedOrder.getId());
        assertNotNull(savedOrder);
    }

}
