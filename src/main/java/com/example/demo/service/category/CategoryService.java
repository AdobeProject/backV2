package com.example.demo.service.category;

import com.example.demo.entity.Category;
import com.example.demo.model.category.CategoryCreateRequestModel;

import java.util.List;

public interface CategoryService {

    Category create(CategoryCreateRequestModel categoryCreateRequestModel);
    Category getByName(String name);
    List<Category> getAllCategories();
    Category update(CategoryCreateRequestModel categoryCreateRequestModel);
}
