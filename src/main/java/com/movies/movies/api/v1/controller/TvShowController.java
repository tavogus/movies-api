package com.movies.movies.api.v1.controller;

import com.movies.movies.api.v1.model.TvShowModel;
import com.movies.movies.domain.model.TvShow;

import com.movies.movies.domain.service.TvShowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(path = "/v1/tvshows", produces = MediaType.APPLICATION_JSON_VALUE)
public class TvShowController {

    private final TvShowService tvShowService;

    @Autowired
    public TvShowController(TvShowService tvShowService){
        this.tvShowService = tvShowService;
    }

    @GetMapping
    public List<TvShowModel> getAll(){
        return tvShowService.getAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<TvShowModel> getTvshow(@PathVariable Long id){
        return tvShowService.getTvShow(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public TvShowModel addTvShow(@Valid @RequestBody TvShow tvShow){
        return tvShowService.save(tvShow);
    }
}
