package com.mako.inventoryservice.jpa;

import com.mako.inventoryservice.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigInteger;


public interface ProductRepository extends JpaRepository<Product, BigInteger> {

}
