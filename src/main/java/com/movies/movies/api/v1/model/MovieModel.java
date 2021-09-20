package com.movies.movies.api.v1.model;

import lombok.Data;

import java.time.OffsetDateTime;
import java.util.List;

@Data
public class MovieModel {

    private Long id;
    private String title;
    private String categoryName;
    private OffsetDateTime insertionDate;
    private List<MovieActorModel> actors;
    private String author;
    private String synopsis;
    private Integer releaseDate;
    private String tags;
}
