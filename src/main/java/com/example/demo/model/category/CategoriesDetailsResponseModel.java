package com.example.demo.model.category;

import java.util.List;

public class CategoriesDetailsResponseModel {

    private List<CategoryDetailsResponseModel>  categoryDetailsResponseModels;

    public List<CategoryDetailsResponseModel> getCategoryDetailsResponseModels() {
        return categoryDetailsResponseModels;
    }

    public void setCategoryDetailsResponseModels(List<CategoryDetailsResponseModel> categoryDetailsResponseModels) {
        this.categoryDetailsResponseModels = categoryDetailsResponseModels;
    }
}
