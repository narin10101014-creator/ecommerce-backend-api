package com.narin.ecommerce.service;

import com.narin.ecommerce.dto.CreateCategoryRequest;
import com.narin.ecommerce.dto.UpdateCategoryRequest;
import com.narin.ecommerce.entity.Category;

import java.util.List;

public interface CategoryService {
    void create(CreateCategoryRequest req);

    Category update(Long id, UpdateCategoryRequest req);

    void delete(Long id);

    List<Category> getAll();

    Category getById(Long id);
}
