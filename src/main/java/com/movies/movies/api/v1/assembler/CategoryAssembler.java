package com.movies.movies.api.v1.assembler;

import com.movies.movies.api.v1.model.CategoryModel;
import com.movies.movies.domain.model.Category;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Component
public class CategoryAssembler {

    private ModelMapper modelMapper;

    public CategoryModel toModel(Category category){
        return modelMapper.map(category, CategoryModel.class);
    }

    public List<CategoryModel> toCollectionModel(List<Category> categories){
        return categories.stream().map(this::toModel).collect(Collectors.toList());
    }
}
