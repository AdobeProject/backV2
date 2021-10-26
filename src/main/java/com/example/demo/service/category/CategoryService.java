package com.example.demo.service.category;

import com.example.demo.entity.Category;
import com.example.demo.model.category.CategoryCreateRequestModel;

import java.util.List;
import java.util.Optional;

public interface CategoryService {

    Category create(CategoryCreateRequestModel categoryCreateRequestModel);

    Optional<Category> getByName(String name);

    List<Category> getAll();

    Category update(CategoryCreateRequestModel categoryCreateRequestModel);
}
