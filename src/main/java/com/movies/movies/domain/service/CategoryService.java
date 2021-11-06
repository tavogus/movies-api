package com.movies.movies.domain.service;

import com.movies.movies.api.v1.ResourceUriHelper;
import com.movies.movies.api.v1.assembler.CategoryAssembler;
import com.movies.movies.api.v1.model.CategoryModel;
import com.movies.movies.domain.exception.BusinessException;
import com.movies.movies.domain.exception.CategoryNotFoundException;
import com.movies.movies.domain.model.Category;
import com.movies.movies.domain.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;
    private final CategoryAssembler categoryAssembler;

    @Autowired
    public CategoryService(CategoryRepository categoryRepository, CategoryAssembler categoryAssembler) {
        this.categoryRepository = categoryRepository;
        this.categoryAssembler = categoryAssembler;
    }

    @Transactional
    public CategoryModel save(Category category) {
        try {
            return categoryAssembler.toModel(categoryRepository.save(category));
        } catch (CategoryNotFoundException e) {
            throw new BusinessException(e.getMessage());
        }
    }

    @Transactional(readOnly = true)
    public CollectionModel<CategoryModel> getAll(){
        return categoryAssembler.toCollectionModel(categoryRepository.findAll());
    }

    @Transactional(readOnly = true)
    public CategoryModel getCategory(Long id){
        Category category = findOrFail(id);

        return categoryAssembler.toModel(category);
    }

    private Category findOrFail(Long id) {
        return categoryRepository.findById(id).orElseThrow(() -> new CategoryNotFoundException(id));
    }
}
