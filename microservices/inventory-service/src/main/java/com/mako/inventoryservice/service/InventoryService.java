package com.mako.inventoryservice.service;

import com.mako.dto.OrderDTO;
import com.mako.inventoryservice.model.Order;

public interface InventoryService {
    Order storeOrder(OrderDTO order);

    void updateStockByOrder(Order order);
}
