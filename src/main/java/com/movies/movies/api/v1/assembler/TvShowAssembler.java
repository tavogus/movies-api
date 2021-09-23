package com.movies.movies.api.v1.assembler;

import com.movies.movies.api.v1.controller.TvShowController;
import com.movies.movies.api.v1.model.TvShowModel;
import com.movies.movies.domain.model.Movie;
import com.movies.movies.domain.model.TvShow;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

@Component
public class TvShowAssembler  extends RepresentationModelAssemblerSupport<TvShow, TvShowModel> {

    @Autowired
    private ModelMapper modelMapper;

    public TvShowAssembler() {
        super(TvShowController.class, TvShowModel.class);
    }

    @Override
    public TvShowModel toModel(TvShow tvShow){
        TvShowModel tvShowModel = createModelWithId(tvShow.getId(), tvShow);

        modelMapper.map(tvShow, tvShowModel);

        return tvShowModel;
    }

    @Override
    public CollectionModel<TvShowModel> toCollectionModel(Iterable<? extends TvShow> entities){
        CollectionModel<TvShowModel> collectionModel = super.toCollectionModel(entities);
        return collectionModel;
    }
}
