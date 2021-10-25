package com.example.demo.service.subCatecories;

import com.example.demo.entity.Category;
import com.example.demo.entity.SubCategory;
import com.example.demo.model.subCategory.SubCategoriesDetailsRequestModel;
import com.example.demo.repository.SubCategoryRepository;
import org.springframework.stereotype.Service;
import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

@Service
public class DefaultSubCategoryService implements SubCategoryService {

    private final SubCategoryRepository subCategoryRepository;

    public DefaultSubCategoryService(SubCategoryRepository subCategoryRepository) {
        this.subCategoryRepository = subCategoryRepository;
    }

    @Override
    public SubCategory create(SubCategoriesDetailsRequestModel subCategoryParams) {

        SubCategory subCategory = new SubCategory();

        subCategory.setName(subCategoryParams.getName());
        subCategory.setCategory(subCategoryParams.getCategory());

        SubCategory savedSubCategory = subCategoryRepository.save(subCategory);

        return savedSubCategory;
    }

    public Optional<SubCategory> getById(Long id) {
        return subCategoryRepository.findById(id);
    }

    @Override
    public SubCategory getByName(String name) {
        Optional<SubCategory> subCategory = subCategoryRepository.findByName(name);
        if (subCategory.isEmpty()){
            throw new EntityNotFoundException(
                    String.format("The Sub Category not found for the name %s", name)
                    );
        }
        return subCategory.get();
    }

    @Override
    public List<SubCategory> getAllByCategory(Category category) {

        List<SubCategory> subCategory = subCategoryRepository.getAllByCategory(category);
        if (subCategory.isEmpty()){
            throw new EntityNotFoundException(
                    String.format("The Sub Category not found for the name %s", category.getName())
            );
        }
        return subCategory;
    }

    @Override
    public SubCategory update(SubCategoriesDetailsRequestModel subCategoryParams) {
        SubCategory subCategory = getByName(subCategoryParams.getName());

        if (subCategoryParams.getName() != null) {
            subCategory.setName(subCategoryParams.getName());
        }
        if (subCategoryParams.getCategory() != null) {
            subCategory.setCategory(subCategoryParams.getCategory());
        }

        return subCategoryRepository.save(subCategory);
    }
}
