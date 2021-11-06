package com.movies.movies.domain.service;


import com.movies.movies.api.v1.assembler.MovieAssembler;
import com.movies.movies.api.v1.model.MovieModel;
import com.movies.movies.domain.exception.*;
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

import java.util.List;
import java.util.Optional;



@Service
public class MovieService {

    private static Logger logger = org.slf4j.LoggerFactory.getLogger(MovieService.class);

    private final MovieRepository movieRepository;
    private final MovieAssembler movieAssembler;
    private final CategoryRepository categoryRepository;
    private final ActorRepository actorRepository;
    private final ActorService actorService;


    @Autowired
    public MovieService(MovieRepository movieRepository, MovieAssembler movieAssembler, CategoryRepository categoryRepository, ActorRepository actorRepository, ActorService actorService) {
        this.movieRepository = movieRepository;
        this.movieAssembler = movieAssembler;
        this.categoryRepository = categoryRepository;
        this.actorRepository = actorRepository;
        this.actorService = actorService;
    }

    @Transactional
    public MovieModel save(Movie movie){

        boolean movieExists = movieRepository.findByTitle(movie.getTitle()).stream().anyMatch(existingMovie -> !existingMovie.equals(movie));

        if (movieExists){
            throw new BusinessException("Already exists a movie with this title!");
        }

        Optional<Category> category = categoryRepository.findById(movie.getCategory().getId());

        if (!category.isPresent()){
            throw new CategoryNotFoundException("There is no category with the given id");
        }

        try{
            return movieAssembler.toModel(movieRepository.save(movie));
        } catch (EntityNotFoundException e){
            throw new BusinessException(e.getMessage(), e);
        }
    }

    @Transactional(readOnly = true)
    public CollectionModel<MovieModel> getAll(){
        return movieAssembler.toCollectionModel(movieRepository.findAll());
    }

    @Transactional(readOnly = true)
    public MovieModel getMovie(Long id){
        Movie movie = findOrFail(id);

        return movieAssembler.toModel(movie);
    }

    @Transactional(readOnly = true)
    public CollectionModel<MovieModel> findByTitle(String title) {
        return movieAssembler.toCollectionModel(movieRepository.findByTitleContaining(title));
    }

    @Transactional(readOnly = true)
    public CollectionModel<MovieModel> findByCategory(String categoryName) {
        Optional<Category> category = categoryRepository.findByName(categoryName);

        if (!category.isPresent()) {
            throw new CategoryNotFoundException("No category found");
        }

        List<Movie> movies = movieRepository.findMoviesByCategory(category.get().getId());

        return movieAssembler.toCollectionModel(movies);
    }
    @Transactional(readOnly = true)
    public CollectionModel<MovieModel> findByActor(String actorName) {
        Optional<Actor> actor = actorRepository.findByName(actorName);

        if (!actor.isPresent()) {
            throw new ActorNotFoundException("No actor found");
        }
        List<Movie> movies = movieRepository.findMoviesByActorsId(actor.get().getId());

        return movieAssembler.toCollectionModel(movies);
    }

    private Movie findOrFail(Long id) {
        return movieRepository.findById(id).orElseThrow(() -> new MovieNotFoundException(id));
    }
}
