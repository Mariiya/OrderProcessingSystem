package com.mako.inventoryservice.jpa;

import com.mako.inventoryservice.model.Purchase;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigInteger;


public interface PurchaseRepository extends JpaRepository<Purchase, BigInteger> {

}
