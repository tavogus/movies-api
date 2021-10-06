package com.movies.movies.domain.service;

import com.movies.movies.api.v1.ResourceUriHelper;
import com.movies.movies.api.v1.assembler.ActorAssembler;
import com.movies.movies.api.v1.model.ActorModel;
import com.movies.movies.domain.exception.ActorNotFoundException;
import com.movies.movies.domain.exception.BusinessException;
import com.movies.movies.domain.model.Actor;
import com.movies.movies.domain.repository.ActorRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.CollectionModel;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;


@Service
public class ActorService {

    private final ActorRepository actorRepository;
    private final ActorAssembler actorAssembler;

    public ActorService(ActorRepository actorRepository, ActorAssembler actorAssembler) {
        this.actorRepository = actorRepository;
        this.actorAssembler = actorAssembler;
    }

    @Transactional
    public ActorModel save(Actor actor){
        try {
            return actorAssembler.toModel(actorRepository.save(actor));
        } catch (ActorNotFoundException e){
            throw new BusinessException(e.getMessage());
        }
    }
    @Transactional(readOnly = true)
    public CollectionModel<ActorModel> getAll(){
        return actorAssembler.toCollectionModel(actorRepository.findAll());
    }
}
