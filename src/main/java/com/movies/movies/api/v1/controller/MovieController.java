package com.movies.movies.api.v1.controller;


import com.movies.movies.api.v1.model.MovieModel;
import com.movies.movies.domain.model.Movie;
import com.movies.movies.domain.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;


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

    @GetMapping("/find-by-category/{categoryName}")
    public CollectionModel<MovieModel> findByCategory(@PathVariable String categoryName){
        return  movieService.findByCategory(categoryName);
    }

    @GetMapping("/find-by-actor/{actorName}")
    public CollectionModel<MovieModel> findByActor(@PathVariable String actorName){
        return  movieService.findByActor(actorName);
    }

}
