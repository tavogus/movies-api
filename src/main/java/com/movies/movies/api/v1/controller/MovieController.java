package com.movies.movies.api.v1.controller;


import com.movies.movies.api.v1.model.MovieModel;
import com.movies.movies.domain.model.Movie;
import com.movies.movies.domain.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(path = "/v1/movies", produces = MediaType.APPLICATION_JSON_VALUE)
public class MovieController {

    private final MovieService movieService;

    @Autowired
    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }

    @GetMapping
    public CollectionModel<MovieModel> getAll(){
        return movieService.getAll();
    }

    @GetMapping("/{id}")
    public MovieModel getMovie(@PathVariable Long id){
        return movieService.getMovie(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public MovieModel addMovie(@Valid @RequestBody Movie movie){
        return movieService.save(movie);
    }

    @GetMapping("/find-by-title/{title}")
    public CollectionModel<MovieModel> findByTitle(@PathVariable String title){
        return movieService.findByTitle(title);
    }
}
