package com.example.demo.mapper;

import com.example.demo.entity.Category;
import com.example.demo.entity.SubCategory;
import com.example.demo.model.category.CategoriesDetailsResponseModel;
import com.example.demo.model.category.CategoryDetailsResponseModel;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class CategoryMapper {

    public CategoryDetailsResponseModel map(Category category) {
        CategoryDetailsResponseModel categoryResponse = new CategoryDetailsResponseModel();

        categoryResponse.setName(category.getName());
        categoryResponse.setSubCategories(category.getSubCategories());
        categoryResponse.setImgId(category.getImgId());

        return categoryResponse;
    }

    public CategoriesDetailsResponseModel mapAllCategories(List<Category> categories) {
        CategoriesDetailsResponseModel categoriesDetailsResponseModel = new CategoriesDetailsResponseModel();

        List<CategoryDetailsResponseModel> mappedCategories = new ArrayList<>();

        for (Category cat : categories) {
            mappedCategories.add(map(cat));
        }

        categoriesDetailsResponseModel.setCategoryDetailsResponseModels(mappedCategories);

        return categoriesDetailsResponseModel;
    }
}