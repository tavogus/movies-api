package com.movies.movies.api.v1.assembler;

import com.movies.movies.api.v1.controller.CategoryController;
import com.movies.movies.api.v1.model.CategoryModel;

import com.movies.movies.domain.model.Category;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;


@Component
public class CategoryAssembler extends RepresentationModelAssemblerSupport<Category, CategoryModel> {

    @Autowired
    private ModelMapper modelMapper;

    public CategoryAssembler() {
        super(CategoryController.class, CategoryModel.class);
    }

    @Override
    public CategoryModel toModel(Category category){
        CategoryModel categoryModel = createModelWithId(category.getId(), category);

        modelMapper.map(category, categoryModel);

        return categoryModel;
    }

    @Override
    public CollectionModel<CategoryModel> toCollectionModel(Iterable<? extends Category> entities){
        CollectionModel<CategoryModel> collectionModel = super.toCollectionModel(entities);
        return collectionModel;
    }
}
