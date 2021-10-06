package com.movies.movies.api.v1.assembler;

import com.movies.movies.api.v1.controller.ActorController;
import com.movies.movies.api.v1.model.ActorModel;
import com.movies.movies.domain.model.Actor;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class ActorAssembler extends RepresentationModelAssemblerSupport<Actor, ActorModel> {

    @Autowired
    public ModelMapper modelMapper;

    public ActorAssembler() {
        super(ActorController.class, ActorModel.class);
    }

    @Override
    public ActorModel toModel(Actor actor){
        ActorModel actorModel = createModelWithId(actor.getId(), actor);

        modelMapper.map(actor, actorModel);

        return actorModel;
    }

    @Override
    public CollectionModel<ActorModel> toCollectionModel(Iterable<? extends Actor> entities){
        CollectionModel<ActorModel> collectionModel = super.toCollectionModel(entities);
        return collectionModel;
    }
}
