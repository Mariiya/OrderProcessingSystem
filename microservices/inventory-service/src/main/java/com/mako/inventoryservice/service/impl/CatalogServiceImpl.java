package com.mako.inventoryservice.service.impl;

import com.mako.inventoryservice.jpa.ProductRepository;
import com.mako.inventoryservice.model.Product;
import com.mako.inventoryservice.service.CatalogService;
import com.mako.dto.ProductDTO;
import com.mako.inventoryservice.utils.Converters;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Collection;

@Service
public class CatalogServiceImpl implements CatalogService {

    private static final Logger LOGGER = LoggerFactory.getLogger(CatalogServiceImpl.class);
    private ProductRepository productRepository;

    public CatalogServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public Collection<ProductDTO> getCatalog() {
        Collection<Product> products = productRepository.findAll();
        LOGGER.info(String.format("Products retrieved from DB: %s", Arrays.toString(products.toArray())));
        return products.stream().map(Converters::convertToProductDTO).toList();
    }

}
