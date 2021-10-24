package com.example.demo.mapper;

import com.example.demo.entity.SubCategory;
import com.example.demo.model.subCategory.SubCategoryParams;
import org.springframework.stereotype.Component;

@Component
public class SubCategoryMapper {

    public SubCategoryParams map(SubCategory subCategory) {
        SubCategoryParams subCategoryParams = new SubCategoryParams();
        subCategoryParams.setCategory(subCategory.getCategory());
        subCategoryParams.setName(subCategory.getName());
        return subCategoryParams;
    }


}
