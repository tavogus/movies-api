package com.movies.movies.domain.service;

import com.movies.movies.domain.exception.BusinessException;
import com.movies.movies.domain.model.Category;
import com.movies.movies.domain.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;

    @Autowired
    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public Category save(Category category) {
        boolean alreadyExistingCategory = categoryRepository.findByName(category.getName()).stream().anyMatch(existingCategory -> !existingCategory.equals(category));

        if (alreadyExistingCategory){
            throw new BusinessException("Already exists a category with this name.");
        }

        return categoryRepository.save(category);
    }
}
