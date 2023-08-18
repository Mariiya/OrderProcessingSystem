package com.mako.inventoryservice.service.impl;

import com.mako.inventoryservice.exception.ObjectNotFoundException;
import com.mako.inventoryservice.jpa.ProductRepository;
import com.mako.inventoryservice.model.Product;
import com.mako.inventoryservice.service.CatalogService;
import com.mako.dto.ProductDTO;
import com.mako.inventoryservice.utils.Converters;
import com.mako.utils.CommonTool;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.Collection;
import java.util.Optional;

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

    @Override
    public ProductDTO getProductById(BigInteger id) {
        Optional<Product> product = productRepository.findById(id);
        if (product.isEmpty()) {
            throw new ObjectNotFoundException("Object not found: " + id);
        }
        return Converters.convertToProductDTO(product.get());
    }

    @Override
    public ProductDTO saveProduct(ProductDTO productDTO) {
        if (CommonTool.isEmpty(productDTO)) {
            return productDTO;
        }
        Product newProduct = Converters.convertToProductEntity(productDTO);
        newProduct = productRepository.save(newProduct);
        return Converters.convertToProductDTO(newProduct);
    }

    @Override
    public ProductDTO updateProduct(ProductDTO productDTO) {
        ProductDTO updated = getProductById(productDTO.getId());
        updated.setName(productDTO.getName());
        updated.setPrice(productDTO.getPrice());
        updated.setQuantity(productDTO.getQuantity());
        updated.setType(productDTO.getType());
        return saveProduct(updated);
    }

    @Override
    public void deleteProduct(ProductDTO productDTO) {
        if (CommonTool.isEmpty(productDTO)) {
            return;
        }
        Product deleteProduct = Converters.convertToProductEntity(productDTO);
        productRepository.delete(deleteProduct);
    }

}
