package com.narin.ecommerce.mapper;

import com.narin.ecommerce.dto.CreateProductResponse;
import com.narin.ecommerce.entity.Product;

public class ProductMapper {
    public static CreateProductResponse toResponse(Product product){
        CreateProductResponse createProductResponse = new CreateProductResponse();
        createProductResponse.setName(product.getName());
        createProductResponse.setDescription(product.getDescription());
        createProductResponse.setPrice(product.getPrice());
        createProductResponse.setStock(product.getStock());
        createProductResponse.setCategoryId(product.getCategory().getId());
        return createProductResponse;
    }
}
