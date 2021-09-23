package com.movies.movies.api.v1.controller;


import com.movies.movies.api.v1.model.TvShowModel;
import com.movies.movies.domain.model.TvShow;

import com.movies.movies.domain.service.TvShowService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@RestController
@RequestMapping(path = "/v1/tvshows", produces = MediaType.APPLICATION_JSON_VALUE)
public class TvShowController {

    private final TvShowService tvShowService;

    @Autowired
    public TvShowController(TvShowService tvShowService){
        this.tvShowService = tvShowService;
    }

    @GetMapping
    public CollectionModel<TvShowModel> getAll(){
        return tvShowService.getAll();
    }

    @GetMapping("/{id}")
    public TvShowModel getTvshow(@PathVariable Long id){
        return tvShowService.getTvShow(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public TvShowModel addTvShow(@Valid @RequestBody TvShow tvShow){
        return tvShowService.save(tvShow);
    }

    @GetMapping("/find-by-category/{categoryName}")
    public CollectionModel<TvShowModel> findByCategory(@PathVariable String categoryName){
        return tvShowService.findByCategory(categoryName);
    }
}
