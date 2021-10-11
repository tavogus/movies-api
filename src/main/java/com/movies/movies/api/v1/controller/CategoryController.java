package com.movies.movies.api.v1.controller;

import com.movies.movies.api.v1.model.CategoryModel;
import com.movies.movies.domain.model.Category;
import com.movies.movies.domain.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;


@RestController
@RequestMapping(path = "/v1/categories", produces = MediaType.APPLICATION_JSON_VALUE)
public class CategoryController {

    private final CategoryService categoryService;

    @Autowired
    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping
    public CollectionModel<CategoryModel> getAll(){
        return categoryService.getAll();
    }

    @GetMapping("/{id}")
    public CategoryModel getCategory(@PathVariable Long id){
        return categoryService.getCategory(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CategoryModel addCategory(@Valid @RequestBody Category category){
        return categoryService.save(category);
    }
}
