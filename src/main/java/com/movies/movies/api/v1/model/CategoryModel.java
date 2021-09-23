package com.movies.movies.api.v1.model;

import lombok.Data;
import org.springframework.hateoas.RepresentationModel;

import java.util.List;

@Data
public class CategoryModel extends RepresentationModel<CategoryModel> {

    private Long id;
    private String name;
    private List<CategoryMovieModel> movies;
    private List<CategoryTvShowModel> tvShows;
}
