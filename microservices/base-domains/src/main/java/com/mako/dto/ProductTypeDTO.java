package com.mako.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigInteger;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductTypeDTO {
    private BigInteger id;
    private BigInteger parentProductId;
    private String type;
}
