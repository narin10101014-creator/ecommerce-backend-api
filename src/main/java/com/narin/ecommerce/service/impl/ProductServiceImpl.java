package com.narin.ecommerce.service.impl;

import com.narin.ecommerce.dto.CreateProductRequest;
import com.narin.ecommerce.dto.UpdateProductRequest;
import com.narin.ecommerce.entity.Category;
import com.narin.ecommerce.entity.Product;
import com.narin.ecommerce.repository.CategoryRepository;
import com.narin.ecommerce.repository.ProductRepository;
import com.narin.ecommerce.service.ProductService;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    ProductRepository productRepository;
    @Autowired
    CategoryRepository categoryRepository;

    @Override
    public void create(CreateProductRequest req) {

        Category category = categoryRepository.findById(req.getCategoryId())
                .orElseThrow(() -> new RuntimeException("Category not found"));

        Product product = Product.builder()
                .name(req.getName())
                .description(req.getDescription())
                .price(req.getPrice())
                .stock(req.getStock())
                .category(category)
                .build();

        productRepository.save(product);
    }

    @Override
    public Product update(Long id, UpdateProductRequest req) {

        Product existing = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found"));

        if(req.getName() != null && !req.getName().isBlank()){
            existing.setName(req.getName());
        }

        existing.setDescription(req.getDescription());

        if(req.getPrice() != null){
            existing.setPrice(req.getPrice());
        }
        if(req.getStock() != null) {
            existing.setStock(req.getStock());
        }

        if (req.getCategoryId() != null) {
            Category category = categoryRepository.findById(req.getCategoryId())
                    .orElseThrow(() -> new RuntimeException("Category not found"));
            existing.setCategory(category);
        }
        return productRepository.save(existing);
    }

    @Override
    public void delete(Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found"));
        product.setDeleted(true); //Soft Delete
        productRepository.save(product);
    }

    @Override
    public Product getById(Long id) {
        return productRepository.findById(id).filter(p -> !p.getDeleted())
                .orElseThrow(() -> new RuntimeException("Product not found"));
    }
    @Override
    public List<Product> getAll() {
        return productRepository.findByDeletedFalse();
    }

}
