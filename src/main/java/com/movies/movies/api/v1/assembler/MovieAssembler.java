package com.movies.movies.api.v1.assembler;

import com.movies.movies.api.v1.controller.MovieController;
import com.movies.movies.api.v1.model.MovieModel;
import com.movies.movies.domain.model.Movie;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import java.lang.reflect.Type;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class MovieAssembler extends RepresentationModelAssemblerSupport<Movie, MovieModel> {

    @Autowired
    private ModelMapper modelMapper;

    public MovieAssembler(){
        super(MovieController.class, MovieModel.class);
    }

    @Override
    public MovieModel toModel(Movie movie){
        MovieModel movieModel = createModelWithId(movie.getId(), movie);

        modelMapper.map(movie, movieModel);

        return movieModel;
    }

    @Override
    public CollectionModel<MovieModel> toCollectionModel(Iterable<? extends Movie> entities) {
        CollectionModel<MovieModel> collectionModel = super.toCollectionModel(entities);
        return collectionModel;
    }
}
