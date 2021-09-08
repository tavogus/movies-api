package com.movies.movies.api.v1.controller;

import com.movies.movies.api.v1.ResourceUriHelper;
import com.movies.movies.api.v1.assembler.MovieAssembler;
import com.movies.movies.api.v1.model.MovieModel;
import com.movies.movies.domain.model.Movie;
import com.movies.movies.domain.repository.MovieRepository;
import com.movies.movies.domain.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(path = "/v1/movies", produces = MediaType.APPLICATION_JSON_VALUE)
public class MovieController {

    private final MovieRepository movieRepository;
    private final MovieService movieService;
    private final MovieAssembler movieAssembler;

    @Autowired
    public MovieController(MovieRepository movieRepository, MovieService movieService, MovieAssembler movieAssembler) {
        this.movieRepository = movieRepository;
        this.movieService = movieService;
        this.movieAssembler = movieAssembler;
    }

    @GetMapping
    public List<MovieModel> getAll(){
        return movieAssembler.toCollectionModel(movieRepository.findAll());

    }

    @GetMapping("/{id}")
    public ResponseEntity<MovieModel> getMovie(@PathVariable Long id){
        return movieRepository.findById(id).map(movie -> ResponseEntity.ok(movieAssembler.toModel(movie))).orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public MovieModel addMovie(@Valid @RequestBody Movie movie){
        movieService.save(movie);
        MovieModel movieModel = movieAssembler.toModel(movie);
        ResourceUriHelper.addUriInResponseHeader(movieModel.getId());
        return movieModel;
    }
}
