package com.movies.movies.api.v1.assembler;

import com.movies.movies.api.v1.model.TvShowModel;
import com.movies.movies.domain.model.TvShow;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Component
public class TvShowAssembler {

    private ModelMapper modelMapper;

    public TvShowModel toModel(TvShow tvShow){
        return modelMapper.map(tvShow, TvShowModel.class);
    }

    public List<TvShowModel> toCollectionModel(List<TvShow> tvShows){
        return tvShows.stream().map(this::toModel).collect(Collectors.toList());
    }
}
