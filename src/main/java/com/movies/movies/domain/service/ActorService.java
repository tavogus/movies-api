package com.movies.movies.domain.service;

import com.movies.movies.api.v1.ResourceUriHelper;
import com.movies.movies.api.v1.assembler.ActorAssembler;
import com.movies.movies.api.v1.model.ActorModel;
import com.movies.movies.domain.model.Actor;
import com.movies.movies.domain.repository.ActorRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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
        actorRepository.save(actor);
        ActorModel actorModel = actorAssembler.toModel(actor);
        ResourceUriHelper.addUriInResponseHeader(actorModel.getId());
        return actorModel;
    }
    @Transactional(readOnly = true)
    public Page<ActorModel> getAll(Pageable pageable){
        Page<Actor> allActors = actorRepository.findAll(pageable);
        return actorAssembler.toCollectionModel(allActors);
    }
}
