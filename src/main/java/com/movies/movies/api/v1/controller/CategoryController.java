package com.movies.movies.api.v1.controller;

import com.movies.movies.api.v1.ResourceUriHelper;
import com.movies.movies.api.v1.assembler.CategoryAssembler;
import com.movies.movies.api.v1.model.CategoryModel;
import com.movies.movies.domain.model.Category;
import com.movies.movies.domain.repository.CategoryRepository;
import com.movies.movies.domain.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(path = "/v1/categories", produces = MediaType.APPLICATION_JSON_VALUE)
public class CategoryController {

    private final CategoryRepository categoryRepository;
    private final CategoryService categoryService;
    private final CategoryAssembler categoryAssembler;

    @Autowired
    public CategoryController(CategoryRepository categoryRepository, CategoryService categoryService, CategoryAssembler categoryAssembler) {
        this.categoryRepository = categoryRepository;
        this.categoryService = categoryService;
        this.categoryAssembler = categoryAssembler;
    }

    @GetMapping
    public List<CategoryModel> getAll(){
        return categoryAssembler.toCollectionModel(categoryRepository.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoryModel> getCategory(@PathVariable Long id){
        return categoryRepository.findById(id).map(category -> ResponseEntity.ok(categoryAssembler.toModel(category))).orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CategoryModel addCategory(@Valid @RequestBody Category category){
        categoryService.save(category);
        CategoryModel categoryModel = categoryAssembler.toModel(category);
        ResourceUriHelper.addUriInResponseHeader(categoryModel.getId());
        return categoryModel;
    }
}
