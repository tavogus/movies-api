package com.movies.movies.api.v1.assembler;

import com.movies.movies.api.v1.model.CategoryModel;
import com.movies.movies.domain.model.Category;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;


@AllArgsConstructor
@Component
public class CategoryAssembler {

    private ModelMapper modelMapper;

    public CategoryModel toModel(Category category){
        return modelMapper.map(category, CategoryModel.class);
    }

    public Page<CategoryModel> toCollectionModel(Page<Category> categories){
        return categories.map(this::toModel);
    }
}
