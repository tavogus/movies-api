package com.movies.movies.domain.service;

import com.movies.movies.domain.exception.BusinessException;
import com.movies.movies.domain.model.Movie;
import com.movies.movies.domain.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MovieService {

    private final MovieRepository movieRepository;

    @Autowired
    public MovieService(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    public Movie save(Movie movie){
        boolean alreadyExistingMovie = movieRepository.findByTitle(movie.getTitle()).stream().anyMatch(existingMovie -> !existingMovie.equals(movie));

        if (alreadyExistingMovie){
            throw new BusinessException("Already exists a movie with this title.");
        }
        return movieRepository.save(movie);
    }

}
