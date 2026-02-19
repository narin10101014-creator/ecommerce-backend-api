package com.narin.ecommerce.service;

import com.narin.ecommerce.dto.CreateProductRequest;
import com.narin.ecommerce.dto.UpdateProductRequest;
import com.narin.ecommerce.entity.Product;

import java.util.List;

public interface ProductService {
    void create(CreateProductRequest req);
    Product update(Long id, UpdateProductRequest req);
    Product getById(Long id);
    List<Product> getAll();
}
