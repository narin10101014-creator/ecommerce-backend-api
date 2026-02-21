package com.narin.ecommerce.controller;

import com.narin.ecommerce.dto.CreateProductRequest;
import com.narin.ecommerce.dto.CreateProductResponse;
import com.narin.ecommerce.dto.UpdateProductRequest;
import com.narin.ecommerce.entity.Product;
import com.narin.ecommerce.mapper.ProductMapper;
import com.narin.ecommerce.service.impl.ProductServiceImpl;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {
    @Autowired
    ProductServiceImpl productService;

    @PostMapping
    public ResponseEntity<String> create(@Valid @RequestBody CreateProductRequest req) {
        productService.create(req);
        return ResponseEntity.ok("Insert Product success");
    }

    @PutMapping("/{id}")
    public Product update(@PathVariable Long id,
                       @RequestBody UpdateProductRequest req) {
        return productService.update(id, req);
    }

    @GetMapping("/{id}")
    public CreateProductResponse getById(@PathVariable Long id){
        return ProductMapper.toResponse(productService.getById(id));
    }

    @GetMapping
    public List<Product> getAll() {
        return productService.getAll();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        productService.delete(id);
        return ResponseEntity.ok("Product deleted");
    }
}
