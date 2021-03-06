package com.example.demo.service.category;

import com.example.demo.entity.Category;
import com.example.demo.model.category.CategoryCreateRequestModel;
import com.example.demo.repository.CategoryRepository;
import org.springframework.stereotype.Service;
import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

@Service
public class DefaultCategoryService implements CategoryService{

    private final CategoryRepository categoryRepository;

    public DefaultCategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public Category create(CategoryCreateRequestModel categoryCreateRequestModel) {

        Category category = new Category();

        category.setName(categoryCreateRequestModel.getName());
        category.setImgId(categoryCreateRequestModel.getImgId());

        Category savedCategory = categoryRepository.save(category);
        return savedCategory;
    }

    @Override
    public Optional<Category> getByName(String name) {
        Optional<Category> categoryOptional = categoryRepository.findByName(name);
        return categoryOptional;
    }

    @Override
    public List<Category> getAll() {
        List<Category> allCategories = categoryRepository.findAll();

        if (allCategories.isEmpty()){
            throw new EntityNotFoundException(
                    "There is not categories"
            );
        }
        return allCategories;
    }

    @Override
    public Category update(CategoryCreateRequestModel categoryCreateRequestModel) {

        Category category = getByName(categoryCreateRequestModel.getName()).get();

        if (categoryCreateRequestModel.getName() != null){
            category.setName(categoryCreateRequestModel.getName());
        }

        if (categoryCreateRequestModel.getImgId() != null){
            category.setName(categoryCreateRequestModel.getImgId());
        }

        return categoryRepository.save(category);
    }
}
