package com.movies.movies.domain.service;

import com.movies.movies.api.v1.ResourceUriHelper;
import com.movies.movies.api.v1.assembler.MovieAssembler;
import com.movies.movies.api.v1.model.MovieModel;
import com.movies.movies.domain.exception.BusinessException;
import com.movies.movies.domain.model.Movie;
import com.movies.movies.domain.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MovieService {

    private final MovieRepository movieRepository;
    private final MovieAssembler movieAssembler;

    @Autowired
    public MovieService(MovieRepository movieRepository, MovieAssembler movieAssembler) {
        this.movieRepository = movieRepository;
        this.movieAssembler = movieAssembler;
    }

    public MovieModel save(Movie movie){
        boolean alreadyExistingMovie = movieRepository.findByTitle(movie.getTitle()).stream().anyMatch(existingMovie -> !existingMovie.equals(movie));

        if (alreadyExistingMovie){
            throw new BusinessException("Already exists a movie with this title.");
        }
        movieRepository.save(movie);
        MovieModel movieModel = movieAssembler.toModel(movie);
        ResourceUriHelper.addUriInResponseHeader(movieModel.getId());
        return movieModel;
    }

    public List<MovieModel> getAll(){
        List<Movie> allMovies = movieRepository.findAll();
        return movieAssembler.toCollectionModel(allMovies);
    }

    public ResponseEntity<MovieModel> getMovie(Long id){
        return movieRepository.findById(id).map(movie -> ResponseEntity.ok(movieAssembler.toModel(movie))).orElse(ResponseEntity.notFound().build());
    }

}
