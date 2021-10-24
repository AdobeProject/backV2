package com.example.demo.controllers;

import com.example.demo.api.facade.category.CategoryFacade;
import com.example.demo.model.category.CategoriesDetailsResponseModel;
import com.example.demo.model.category.CategoryCreateRequestModel;
import com.example.demo.model.category.CategoryDetailsResponseModel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/categories")
public class CategoriesController {

    private final CategoryFacade categoryFacade;

    public CategoriesController(CategoryFacade categoryFacade) {
        this.categoryFacade = categoryFacade;
    }

    //~CGLIB
    //JDK proxy
    @GetMapping("/")
    public CategoriesDetailsResponseModel getAll() {
        CategoriesDetailsResponseModel allCat = categoryFacade.getAll();
        return allCat;
    }

}
