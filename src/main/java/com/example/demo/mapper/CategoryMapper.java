package com.example.demo.mapper;

import com.example.demo.entity.Category;
import com.example.demo.entity.SubCategory;
import com.example.demo.model.category.CategoryDetailsResponseModel;
import org.springframework.stereotype.Component;

@Component
public class CategoryMapper {

    public CategoryDetailsResponseModel map(Category category) {

        CategoryDetailsResponseModel categoryResponse = new CategoryDetailsResponseModel();

        categoryResponse.setName(category.getName());
        categoryResponse.setSubCategories(category.getSubCategories());
        categoryResponse.setImgId(category.getImgId());
        return categoryResponse;
    }
}