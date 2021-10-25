package com.example.demo.api.facade.category;

import com.example.demo.entity.Category;
import com.example.demo.mapper.CategoryMapper;
import com.example.demo.model.category.CategoriesDetailsResponseModel;
import com.example.demo.service.category.CategoryService;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DefaultCategoryFacade implements CategoryFacade {

    private final CategoryService categoryService;
    private final CategoryMapper categoryMapper;

    public DefaultCategoryFacade(CategoryService categoryService, CategoryMapper categoryMapper) {
        this.categoryService = categoryService;
        this.categoryMapper = categoryMapper;
    }

    @Override
    public CategoriesDetailsResponseModel getAll() {

        List<Category> categories = categoryService.getAll();

        return categoryMapper.mapAllCategories(categories);
    }
}
