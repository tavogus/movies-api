package com.movies.movies.api.v1.model;

import lombok.Data;
import org.springframework.hateoas.RepresentationModel;

@Data
public class ActorModel extends RepresentationModel<ActorModel> {

    private Long id;
    private String name;
}
