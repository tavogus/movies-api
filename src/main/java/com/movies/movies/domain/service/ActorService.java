package com.movies.movies.domain.service;


import com.movies.movies.api.v1.assembler.ActorAssembler;
import com.movies.movies.api.v1.model.ActorModel;
import com.movies.movies.domain.exception.BusinessException;
import com.movies.movies.domain.model.Actor;
import com.movies.movies.domain.repository.ActorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;



@Service
public class ActorService {

    private final ActorRepository actorRepository;
    private final ActorAssembler actorAssembler;

    @Autowired
    public ActorService(ActorRepository actorRepository, ActorAssembler actorAssembler) {
        this.actorRepository = actorRepository;
        this.actorAssembler = actorAssembler;
    }

    @Transactional
    public ActorModel save(Actor actor){

        boolean actorExists = actorRepository.findByName(actor.getName()).stream().anyMatch(existingActor -> !existingActor.equals(actor));

        if (actorExists){
            throw new BusinessException("Already exists an actor with this name!");
        }

        return actorAssembler.toModel(actorRepository.save(actor));

    }
    @Transactional(readOnly = true)
    public CollectionModel<ActorModel> getAll(){
        return actorAssembler.toCollectionModel(actorRepository.findAll());
    }
}
