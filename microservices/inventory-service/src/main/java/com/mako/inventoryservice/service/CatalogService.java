package com.mako.inventoryservice.service;

import com.mako.dto.ProductDTO;

import java.util.Collection;

public interface CatalogService {

    Collection<ProductDTO> getCatalog();

}
