package com.mako.inventoryservice.service;

import com.mako.dto.ProductDTO;

import java.math.BigInteger;
import java.util.Collection;

public interface CatalogService {

    Collection<ProductDTO> getCatalog();

    ProductDTO getProductById(BigInteger id);

    ProductDTO addProduct(ProductDTO product);

    ProductDTO updateProduct(ProductDTO product);

    void deleteProduct(ProductDTO product);

}
