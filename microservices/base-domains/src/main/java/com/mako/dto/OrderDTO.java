package com.mako.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigInteger;
import java.util.Collection;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderDTO {

    private BigInteger id;
    private BigInteger accountId;
    private Collection<PurchaseDTO> purchases;
    private double totalPrice;

}
