package com.mako.inventoryservice.model;

import jakarta.persistence.*;

import java.math.BigInteger;
import java.util.Collection;

@Entity
@Table(name = "ORDERS")
public class Order {
    @Id
    @GeneratedValue
    private BigInteger id;
    private BigInteger accountId;
    @OneToMany
    private Collection<Purchase> purchases;
    private double totalPrice;

    public BigInteger getId() {
        return id;
    }

    public void setId(BigInteger id) {
        this.id = id;
    }

    public BigInteger getAccountId() {
        return accountId;
    }

    public void setAccountId(BigInteger accountId) {
        this.accountId = accountId;
    }

    public Collection<Purchase> getPurchases() {
        return purchases;
    }

    public void setPurchases(Collection<Purchase> purchases) {
        this.purchases = purchases;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", accountId=" + accountId +
                ", purchases=" + purchases +
                ", totalPrice=" + totalPrice +
                '}';
    }
}
