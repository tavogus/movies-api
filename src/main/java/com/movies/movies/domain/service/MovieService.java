package com.movies.movies.domain.service;


import com.movies.movies.api.v1.assembler.MovieAssembler;

import com.movies.movies.api.v1.model.MovieModel;
import com.movies.movies.domain.exception.ActorNotFoundException;
import com.movies.movies.domain.exception.BusinessException;
import com.movies.movies.domain.exception.CategoryNotFoundException;
import com.movies.movies.domain.exception.MovieNotFoundException;
import com.movies.movies.domain.model.Movie;
import com.movies.movies.domain.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.hateoas.CollectionModel;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class MovieService {

    private final MovieRepository movieRepository;
    private final MovieAssembler movieAssembler;

    @Autowired
    public MovieService(MovieRepository movieRepository, MovieAssembler movieAssembler) {
        this.movieRepository = movieRepository;
        this.movieAssembler = movieAssembler;
    }

    @Transactional
    public MovieModel save(Movie movie){
        try{
            return movieAssembler.toModel(movieRepository.save(movie));
        } catch (CategoryNotFoundException | ActorNotFoundException e){
            throw new BusinessException(e.getMessage());
        }
    }

    @Transactional(readOnly = true)
    public CollectionModel<MovieModel> getAll(){
        return movieAssembler.toCollectionModel(movieRepository.findAll());
    }

    @Transactional
    public MovieModel getMovie(Long id){
        Movie movie = findOrFail(id);

        return movieAssembler.toModel(movie);
    }

    @Transactional
    public CollectionModel<MovieModel> findByTitle(String title) {
        return movieAssembler.toCollectionModel(movieRepository.findByTitleContaining(title));
    }

    private Movie findOrFail(Long id) {
        return movieRepository.findById(id).orElseThrow(() -> new MovieNotFoundException(id));
    }
}
