package com.example.demo.model.category;

import com.example.demo.entity.SubCategory;

import java.util.List;

public class CategoryDetailsResponseModel {


    private String name;

    private String imgId;

    private List<SubCategory> subCategories;

    public CategoryDetailsResponseModel() {
    }

    public CategoryDetailsResponseModel(String name, String imgId, List<SubCategory> subCategories) {
        this.name = name;
        this.imgId = imgId;
        this.subCategories = subCategories;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImgId() {
        return imgId;
    }

    public void setImgId(String imgId) {
        this.imgId = imgId;
    }

    public List<SubCategory> getSubCategories() {
        return subCategories;
    }

    public void setSubCategories(List<SubCategory> subCategories) {
        this.subCategories = subCategories;
    }
}
