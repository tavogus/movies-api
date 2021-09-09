package com.movies.movies.api.v1.assembler;

import com.movies.movies.api.v1.model.TvShowModel;
import com.movies.movies.domain.model.TvShow;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@Component
public class TvShowAssembler {

    private ModelMapper modelMapper;

    public TvShowModel toModel(TvShow tvShow){
        return modelMapper.map(tvShow, TvShowModel.class);
    }

    public Page<TvShowModel> toCollectionModel(Page<TvShow> tvShows){
        return tvShows.map(this::toModel);
    }
}
