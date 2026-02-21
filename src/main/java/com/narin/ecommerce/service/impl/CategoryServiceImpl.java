package com.narin.ecommerce.service.impl;

import com.narin.ecommerce.dto.CreateCategoryRequest;
import com.narin.ecommerce.dto.UpdateCategoryRequest;
import com.narin.ecommerce.entity.Category;
import com.narin.ecommerce.repository.CategoryRepository;
import com.narin.ecommerce.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    CategoryRepository categoryRepository;
    @Override
    public void create(CreateCategoryRequest req) {
        if (categoryRepository.findByName(req.getName()).isPresent()) {
            throw new RuntimeException("Category already exists");
        }
        Category category = Category.builder()
                .name(req.getName())
                .build();
        categoryRepository.save(category);
    }

    @Override
    public Category update(Long id, UpdateCategoryRequest req) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Category not found"));
        category.setName(req.getName());
        return categoryRepository.save(category);
    }

    @Override
    public void delete(Long id) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Category not found"));
        categoryRepository.delete(category);
    }

    @Override
    public List<Category> getAll() {
        return categoryRepository.findAll();
    }

    @Override
    public Category getById(Long id) {
        return categoryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Category not found"));
    }
}
