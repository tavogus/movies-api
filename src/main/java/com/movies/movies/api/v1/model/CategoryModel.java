package com.movies.movies.api.v1.model;

import lombok.Data;

import java.util.List;

@Data
public class CategoryModel {

    private Long id;
    private String name;
    private List<CategoryMovieModel> movies;
    private List<CategoryTvShowModel> tvShows;
}
