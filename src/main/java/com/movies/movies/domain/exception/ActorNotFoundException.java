package com.movies.movies.domain.exception;

public class ActorNotFoundException extends EntityNotFoundException{

    private static final long serialVersionUID = 1L;

    public ActorNotFoundException(String message) {
        super(message);
    }

    public ActorNotFoundException(Long id){
        this(String.format("There is no actor with id %d", id));
    }
}
