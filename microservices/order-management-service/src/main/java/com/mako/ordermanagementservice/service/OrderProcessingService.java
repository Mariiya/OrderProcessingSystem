package com.mako.ordermanagementservice.service;

import com.mako.dto.OrderDTO;

public interface OrderProcessingService {

    OrderDTO createOrder(OrderDTO order);

}
