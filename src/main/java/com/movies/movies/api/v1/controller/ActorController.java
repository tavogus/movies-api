package com.movies.movies.api.v1.controller;

import com.movies.movies.api.v1.model.ActorModel;
import com.movies.movies.domain.model.Actor;
import com.movies.movies.domain.service.ActorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(path = "/v1/actors", produces = MediaType.APPLICATION_JSON_VALUE)
public class ActorController {

    private final ActorService actorService;

    @Autowired
    public ActorController(ActorService actorService) {
        this.actorService = actorService;
    }

    @GetMapping
    public CollectionModel<ActorModel> getAll(){
        return actorService.getAll();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ActorModel addActor(@Valid @RequestBody Actor actor){
        return actorService.save(actor);
    }
}
