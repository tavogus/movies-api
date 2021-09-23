package com.movies.movies.domain.exception;

public class TvShowNotFoundException extends EntityNotFoundException {

    private static final long serialVersionUID = 1L;

    public TvShowNotFoundException(String message) {
        super(message);
    }

    public TvShowNotFoundException(Long id) {
        this(String.format("There is no tv show with id %d", id));
    }

}
