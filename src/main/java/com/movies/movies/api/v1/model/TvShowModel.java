package com.movies.movies.api.v1.model;

import lombok.Data;

import java.time.OffsetDateTime;
import java.util.List;

@Data
public class TvShowModel {

    private Long id;
    private String title;
    private String category;
    private OffsetDateTime insertionDate;
    private List<TvShowActorModel> actors;
    private String author;
    private String synopsis;
    private Integer seasons;
}
