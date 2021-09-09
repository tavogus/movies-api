package com.movies.movies.api.v1.assembler;

import com.movies.movies.api.v1.model.ActorModel;
import com.movies.movies.domain.model.Actor;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Component
public class ActorAssembler {

    public ModelMapper modelMapper;

    public ActorModel toModel(Actor actor){
        return modelMapper.map(actor, ActorModel.class);
    }

    public Page<ActorModel> toCollectionModel(Page<Actor> actors){
        return actors.map(this::toModel);
    }
}
