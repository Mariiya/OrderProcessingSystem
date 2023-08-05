package com.mako.inventoryservice.jpa;

import com.mako.inventoryservice.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigInteger;

public interface OrderRepository extends JpaRepository<Order, BigInteger> {
}
