package com.mako.inventoryservice.service.impl;

import com.mako.dto.OrderDTO;
import com.mako.inventoryservice.jpa.OrderRepository;
import com.mako.inventoryservice.jpa.ProductRepository;
import com.mako.inventoryservice.jpa.PurchaseRepository;
import com.mako.inventoryservice.model.Order;
import com.mako.inventoryservice.model.Product;
import com.mako.inventoryservice.model.Purchase;
import com.mako.inventoryservice.service.InventoryService;
import com.mako.inventoryservice.utils.Converters;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InventoryServiceImpl implements InventoryService {

    private static final Logger LOGGER = LoggerFactory.getLogger(InventoryServiceImpl.class);

    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;
    private final PurchaseRepository purchaseRepository;

    @Autowired
    public InventoryServiceImpl(OrderRepository orderRepository, ProductRepository productRepository, PurchaseRepository purchaseRepository) {
        this.orderRepository = orderRepository;
        this.productRepository = productRepository;
        this.purchaseRepository = purchaseRepository;
    }

    @Override
    public Order storeOrder(OrderDTO orderDTO) {
        List<Purchase> purchases = orderDTO.getPurchases()
                .stream()
                .map(Converters::convertToPurchaseEntity)
                .toList();
        purchases = purchaseRepository.saveAll(purchases);
        Order newOrder = Converters.convertToOrderEntity(orderDTO);
        newOrder.setPurchases(purchases);
        newOrder = orderRepository.save(newOrder);
        LOGGER.info(String.format("Order saved to database -> %s", newOrder));
        return newOrder;
    }

    @Override
    public void updateStockByOrder(Order order) {
        List<Product> productList = productRepository.findAllById(order
                .getPurchases()
                .stream().map(Purchase::getId).toList());
        for (Product p : productList) {
            p.setQuantity(p.getQuantity() - 1);
        }
        productRepository.saveAll(productList);
    }
}
