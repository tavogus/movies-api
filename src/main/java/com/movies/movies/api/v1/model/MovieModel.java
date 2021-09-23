package com.movies.movies.api.v1.model;

import com.movies.movies.domain.model.Movie;
import lombok.Data;
import org.springframework.hateoas.RepresentationModel;


import java.time.OffsetDateTime;
import java.util.List;

@Data
public class MovieModel extends RepresentationModel<MovieModel> {

    private Long id;
    private String title;
    private String categoryName;
    private OffsetDateTime insertionDate;
    private List<ActorModel> actors;
    private String author;
    private String synopsis;
    private Integer releaseDate;
    private String tags;
}
