package com.example.demo.model.category;


public class CategoryCreateRequestModel {

    private String name;

    private String imgURL;

    public CategoryCreateRequestModel() {
    }

    public CategoryCreateRequestModel(String name, String imgId) {
        this.name = name;
        this.imgURL = imgId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImgId() {
        return imgURL;
    }

    public void setImgId(String imgId) {
        this.imgURL = imgId;
    }
}
