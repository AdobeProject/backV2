package com.example.demo.service.subCatecories;

import com.example.demo.entity.Category;
import com.example.demo.entity.SubCategory;
import com.example.demo.model.subCategory.SubCategoriesDetailsRequestModel;

import java.util.List;

public interface SubCategoryService {
    SubCategory create(SubCategoriesDetailsRequestModel subCategoryParams);
    SubCategory getByName(String name);
    List<SubCategory> getAllByCategory(Category category);
    SubCategory update(SubCategoriesDetailsRequestModel subCategoryParams);
}
