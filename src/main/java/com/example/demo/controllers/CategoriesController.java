package com.example.demo.controllers;

import com.example.demo.api.facade.category.CategoryFacade;
import com.example.demo.model.category.CategoriesDetailsResponseModel;
import com.example.demo.model.category.CategoryCreateRequestModel;
import com.example.demo.model.category.CategoryDetailsResponseModel;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/categories")
//@CrossOrigin("*")
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
