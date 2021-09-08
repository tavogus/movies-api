package com.movies.movies.api.v1.controller;

import com.movies.movies.domain.model.Actor;
import com.movies.movies.domain.repository.ActorRepository;
import com.movies.movies.domain.service.ActorService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(path = "/v1/actors", produces = MediaType.APPLICATION_JSON_VALUE)
public class ActorController {

    private final ActorRepository actorRepository;
    private final ActorService actorService;

    public ActorController(ActorRepository actorRepository, ActorService actorService) {
        this.actorRepository = actorRepository;
        this.actorService = actorService;
    }

    @GetMapping
    public List<Actor> getAll(){
        return actorRepository.findAll();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Actor addActor(@Valid @RequestBody Actor actor){
        return actorService.save(actor);
    }
}
