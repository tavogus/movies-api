package com.movies.movies.domain.service;

import com.movies.movies.api.v1.ResourceUriHelper;
import com.movies.movies.api.v1.assembler.CategoryAssembler;
import com.movies.movies.api.v1.model.CategoryModel;
import com.movies.movies.domain.exception.BusinessException;
import com.movies.movies.domain.model.Category;
import com.movies.movies.domain.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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
        boolean alreadyExistingCategory = categoryRepository.findByName(category.getName()).stream().anyMatch(existingCategory -> !existingCategory.equals(category));

        if (alreadyExistingCategory){
            throw new BusinessException("Already exists a category with this name.");
        }

        categoryRepository.save(category);
        CategoryModel categoryModel = categoryAssembler.toModel(category);
        ResourceUriHelper.addUriInResponseHeader(categoryModel.getId());
        return categoryModel;
    }
    @Transactional(readOnly = true)
    public Page<CategoryModel> getAll(Pageable pageable){
        Page<Category> allCategories = categoryRepository.findAll(pageable);
        return categoryAssembler.toCollectionModel(allCategories);
    }

    @Transactional
    public ResponseEntity<CategoryModel> getCategory(Long id){
        return categoryRepository.findById(id).map(category -> ResponseEntity.ok(categoryAssembler.toModel(category))).orElse(ResponseEntity.notFound().build());
    }
}
