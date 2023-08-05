package com.mako.inventoryservice.model;

import jakarta.persistence.*;

import java.math.BigInteger;

@Entity
@Table(name = "PRODUCTS")
public class Product {

    @Id
    @GeneratedValue
    private BigInteger id;

    private String name;

    @ManyToOne
    private ProductType type;

    private int quantity;

    private double price;

    public BigInteger getId() {
        return id;
    }

    public void setId(BigInteger id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ProductType getType() {
        return type;
    }

    public void setType(ProductType type) {
        this.type = type;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", type=" + type +
                ", quantity=" + quantity +
                ", price=" + price +
                '}';
    }
}
