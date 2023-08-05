package com.mako.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigInteger;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PurchaseDTO {
    private BigInteger id;
    private ProductDTO product;
    private int quantity;
}
