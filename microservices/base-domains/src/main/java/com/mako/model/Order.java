package com.mako.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigInteger;
import java.util.Collection;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Order {

    private BigInteger id;
    private BigInteger accountId;
    private Collection<Product> products;
    private double totalPrice;

}
