package com.movies.movies.api.v1.assembler;

import com.movies.movies.api.v1.model.MovieModel;
import com.movies.movies.domain.model.Movie;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Component
public class MovieAssembler {

    private ModelMapper modelMapper;

    public MovieModel toModel(Movie movie){
        return modelMapper.map(movie, MovieModel.class);
    }

    public Page<MovieModel> toCollectionModelPaged(Page<Movie> movies){
        return movies.map(this::toModel);
    }

    public List<MovieModel> toCollectionModel(List<Movie> movies) {
        return movies.stream().map(this::toModel).collect(Collectors.toList());
}
}
