package com.movies.movies.domain.service;


import com.movies.movies.api.v1.assembler.MovieAssembler;

import com.movies.movies.api.v1.model.MovieModel;
import com.movies.movies.domain.exception.ActorNotFoundException;
import com.movies.movies.domain.exception.BusinessException;
import com.movies.movies.domain.exception.CategoryNotFoundException;
import com.movies.movies.domain.exception.MovieNotFoundException;
import com.movies.movies.domain.model.Actor;
import com.movies.movies.domain.model.Category;
import com.movies.movies.domain.model.Movie;
import com.movies.movies.domain.repository.ActorRepository;
import com.movies.movies.domain.repository.CategoryRepository;
import com.movies.movies.domain.repository.MovieRepository;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.hateoas.CollectionModel;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;


@Service
public class MovieService {

    private static Logger logger = org.slf4j.LoggerFactory.getLogger(MovieService.class);

    private final MovieRepository movieRepository;
    private final MovieAssembler movieAssembler;
    private final CategoryRepository categoryRepository;
    private final ActorRepository actorRepository;

    @Autowired
    public MovieService(MovieRepository movieRepository, MovieAssembler movieAssembler, CategoryRepository categoryRepository, ActorRepository actorRepository) {
        this.movieRepository = movieRepository;
        this.movieAssembler = movieAssembler;
        this.categoryRepository = categoryRepository;
        this.actorRepository = actorRepository;
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

    @Transactional
    public CollectionModel<MovieModel> findByCategory(String categoryName) {
        Optional<Category> category = categoryRepository.findByName(categoryName);

        return movieAssembler.toCollectionModel(movieRepository.findMoviesByCategory(category.get().getId()));
    }

    public CollectionModel<MovieModel> findByActor(String actorName) {
        Optional<Actor> actor = actorRepository.findByName(actorName);

        return movieAssembler.toCollectionModel(movieRepository.findMoviesByActors(actor.get().getId()));
    }

    private Movie findOrFail(Long id) {
        return movieRepository.findById(id).orElseThrow(() -> new MovieNotFoundException(id));
    }


}
