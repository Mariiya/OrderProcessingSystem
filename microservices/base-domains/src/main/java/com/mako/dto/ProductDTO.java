package com.mako.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigInteger;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductDTO {

    private BigInteger id;
    private String name;
    private ProductTypeDTO type;
    private int quantity;
    private double price;

}
