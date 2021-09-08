package com.movies.movies.domain.service;

import com.movies.movies.domain.model.Actor;
import com.movies.movies.domain.repository.ActorRepository;
import org.springframework.stereotype.Service;

@Service
public class ActorService {

    private final ActorRepository actorRepository;

    public ActorService(ActorRepository actorRepository) {
        this.actorRepository = actorRepository;
    }

    public Actor save(Actor actor){
        return actorRepository.save(actor);
    }
}
