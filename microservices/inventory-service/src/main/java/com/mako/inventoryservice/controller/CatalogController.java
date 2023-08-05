package com.mako.inventoryservice.controller;

import com.mako.inventoryservice.service.CatalogService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/catalog/v1")
public class CatalogController {

    private CatalogService service;

    public CatalogController(CatalogService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<Object> getCatalog() {
        return ResponseEntity.ok(service.getCatalog());
    }
}

