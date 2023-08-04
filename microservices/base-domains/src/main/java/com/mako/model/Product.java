package com.mako.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigInteger;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Product {

    private BigInteger id;
    private String name;
    private int quantity;
    private double price;

}
