package com.example.demo.mapper;

import com.example.demo.entity.SubCategory;
import com.example.demo.model.subCategory.SubCategoriesDetailsRequestModel;
import com.example.demo.model.subCategory.SubCategoriesDetailsResponseModel;
import org.springframework.stereotype.Component;

@Component
public class SubCategoryMapper {

    public SubCategoriesDetailsResponseModel map(SubCategory subCategory) {
        SubCategoriesDetailsResponseModel subCategoryParams = new SubCategoriesDetailsResponseModel();
        subCategoryParams.setCategory(subCategory.getCategory());
        subCategoryParams.setName(subCategory.getName());
        return subCategoryParams;
    }


}
