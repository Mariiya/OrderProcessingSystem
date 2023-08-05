package com.mako.inventoryservice.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

import java.math.BigInteger;

@Entity
public class ProductType {
    @Id
    @GeneratedValue
    private BigInteger id;
    private BigInteger parentProductId;
    private String type;

    public BigInteger getId() {
        return id;
    }

    public void setId(BigInteger id) {
        this.id = id;
    }

    public BigInteger getParentProductId() {
        return parentProductId;
    }

    public void setParentProductId(BigInteger parentProductId) {
        this.parentProductId = parentProductId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "ProductType{" +
                "id=" + id +
                ", parentProductId=" + parentProductId +
                ", type='" + type + '\'' +
                '}';
    }
}
