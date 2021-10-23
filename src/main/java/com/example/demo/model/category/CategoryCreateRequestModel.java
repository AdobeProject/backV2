package com.example.demo.model.category;


public class CategoryCreateRequestModel {

    private String name;

    private String imgId;

    public CategoryCreateRequestModel() {
    }

    public CategoryCreateRequestModel(String name, String imgId) {
        this.name = name;
        this.imgId = imgId;
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
}
