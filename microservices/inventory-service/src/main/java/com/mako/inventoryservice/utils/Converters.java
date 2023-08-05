package com.mako.inventoryservice.utils;

import com.mako.dto.OrderDTO;
import com.mako.dto.ProductDTO;
import com.mako.dto.ProductTypeDTO;
import com.mako.dto.PurchaseDTO;
import com.mako.inventoryservice.model.Order;
import com.mako.inventoryservice.model.Product;
import com.mako.inventoryservice.model.ProductType;
import com.mako.inventoryservice.model.Purchase;
import com.mako.utils.CommonTool;

import java.util.stream.Collectors;

public class Converters {

    public static Product convertToProductEntity(ProductDTO productDTO) {
        if (CommonTool.isEmpty(productDTO)) {
            return null;
        }
        Product product = new Product();
        product.setId(productDTO.getId());
        product.setName(productDTO.getName());
        product.setPrice(productDTO.getPrice());
        product.setType(convertToProductTypeEntity(productDTO.getType()));
        product.setQuantity(productDTO.getQuantity());
        return product;
    }

    public static ProductDTO convertToProductDTO(Product product) {
        if (CommonTool.isEmpty(product)) {
            return null;
        }
        ProductDTO productDTO = new ProductDTO();
        productDTO.setId(product.getId());
        productDTO.setName(product.getName());
        productDTO.setPrice(product.getPrice());
        productDTO.setType(convertToProductTypeDTO(product.getType()));
        productDTO.setQuantity(product.getQuantity());
        return productDTO;
    }

    public static ProductType convertToProductTypeEntity(ProductTypeDTO productTypeDTO) {
        if (CommonTool.isEmpty(productTypeDTO)) {
            return null;
        }
        ProductType productType = new ProductType();
        productType.setId(productTypeDTO.getId());
        productType.setParentProductId(productTypeDTO.getParentProductId());
        productType.setType(productTypeDTO.getType());
        return productType;
    }

    public static ProductTypeDTO convertToProductTypeDTO(ProductType productType) {
        if (CommonTool.isEmpty(productType)) {
            return null;
        }
        ProductTypeDTO productTypeDTO = new ProductTypeDTO();
        productTypeDTO.setId(productType.getId());
        productTypeDTO.setParentProductId(productType.getParentProductId());
        productTypeDTO.setType(productType.getType());
        return productTypeDTO;
    }

    public static Order convertToOrderEntity(OrderDTO orderDTO) {
        if (CommonTool.isEmpty(orderDTO)) {
            return null;
        }
        Order order = new Order();
        order.setId(orderDTO.getId());
        order.setAccountId(orderDTO.getAccountId());
        order.setPurchases(orderDTO.getPurchases().stream().map(Converters::convertToPurchaseEntity).collect(Collectors.toList()));
        order.setTotalPrice(orderDTO.getTotalPrice());
        return order;
    }

    public static OrderDTO convertToOrderDTO(Order order) {
        if (CommonTool.isEmpty(order)) {
            return null;
        }
        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setId(order.getId());
        orderDTO.setAccountId(order.getAccountId());
        orderDTO.setPurchases(order.getPurchases().stream().map(Converters::convertToPurchaseDTO).collect(Collectors.toList()));
        orderDTO.setTotalPrice(order.getTotalPrice());
        return orderDTO;
    }

    public static PurchaseDTO convertToPurchaseDTO(Purchase purchase) {
        if (CommonTool.isEmpty(purchase)) {
            return null;
        }
        PurchaseDTO purchaseDTO = new PurchaseDTO();
        purchaseDTO.setId(purchase.getId());
        purchaseDTO.setProduct(convertToProductDTO(purchase.getProduct()));
        purchaseDTO.setQuantity(purchase.getQuantity());
        return purchaseDTO;
    }

    public static Purchase convertToPurchaseEntity(PurchaseDTO purchaseDTO) {
        if (CommonTool.isEmpty(purchaseDTO)) {
            return null;
        }
        Purchase purchase = new Purchase();
        purchase.setId(purchaseDTO.getId());
        purchase.setProduct(convertToProductEntity(purchaseDTO.getProduct()));
        purchase.setQuantity(purchaseDTO.getQuantity());
        return purchase;
    }

}
