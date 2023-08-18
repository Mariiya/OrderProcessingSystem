package com.mako.inventoryservice.controller;

import com.mako.dto.ProductDTO;
import com.mako.inventoryservice.service.CatalogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.math.BigInteger;
import java.net.URI;

@RestController
@RequestMapping("/catalog/v1")
public class CatalogController {

    private final CatalogService service;

    @Autowired
    public CatalogController(CatalogService service) {
        this.service = service;
    }

    @GetMapping("product/{id}")
    public ResponseEntity<ProductDTO> getProductById(@PathVariable BigInteger id) {
        ProductDTO product = service.getProductById(id);
        return ResponseEntity.ok(product);
    }

    @PostMapping("/product")
    public ResponseEntity<ProductDTO> addProduct(@RequestBody ProductDTO productDTO) {
        ProductDTO newProduct = service.saveProduct(productDTO);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(newProduct.getId())
                .toUri();
        return ResponseEntity.created(location).build();
    }

    @PatchMapping("/product")
    public ResponseEntity<ProductDTO> updateProduct(@RequestBody ProductDTO productDTO) {
        ProductDTO product = service.updateProduct(productDTO);
        return ResponseEntity.ok(product);
    }

    @DeleteMapping("/product")
    public ResponseEntity<Object> deleteProduct(@RequestBody ProductDTO productDTO) {
        service.deleteProduct(productDTO);
        return ResponseEntity.ok("Product deleted");
    }

    @GetMapping
    public ResponseEntity<Object> getCatalog() {
        return ResponseEntity.ok(service.getCatalog());
    }
}

