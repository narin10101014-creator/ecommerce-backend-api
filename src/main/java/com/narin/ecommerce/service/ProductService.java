package com.narin.ecommerce.service;

import com.narin.ecommerce.dto.request.CreateProductRequest;
import com.narin.ecommerce.dto.request.UpdateProductRequest;
import com.narin.ecommerce.dto.response.ProductResponse;
import com.narin.ecommerce.entity.Product;

import java.util.List;

public interface ProductService {
    void create(CreateProductRequest req);
    Product update(Long id, UpdateProductRequest req);
    void delete(Long id);
    Product getById(Long id);
    List<ProductResponse> getAll();
}
