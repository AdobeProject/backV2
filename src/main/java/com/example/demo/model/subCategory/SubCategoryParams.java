package com.example.demo.model.subCategory;

import com.example.demo.entity.Category;

public class SubCategoryParams {

    private String name;

    private Category category;

    public SubCategoryParams(String name, Category category) {
        this.name = name;
        this.category = category;
    }

    public SubCategoryParams() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }
}
