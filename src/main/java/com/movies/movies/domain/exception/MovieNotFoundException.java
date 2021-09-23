package com.movies.movies.domain.exception;


public class MovieNotFoundException extends EntityNotFoundException {

    private static final long serialVersionUID = 1L;

    public MovieNotFoundException(String message){
        super(message);
    }

    public MovieNotFoundException(Long id) {
        this(String.format("There is no movie with id %d", id));
    }
}
