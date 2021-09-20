package com.movies.movies.core.modelmapper;

import com.movies.movies.api.v1.model.MovieModel;
import com.movies.movies.api.v1.model.TvShowModel;
import com.movies.movies.domain.model.Movie;
import com.movies.movies.domain.model.TvShow;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModelMapperConfig {

    @Bean
    public ModelMapper modelMapper(){
        var modelMapper = new ModelMapper();

        var movieToMovieModelTypeMap = modelMapper.createTypeMap(Movie.class, MovieModel.class);
        movieToMovieModelTypeMap.<String>addMapping(src -> src.getCategory().getName(), (dest, value) -> dest.setCategoryName(value));

        var tvShowToTvShowModelTypeMap = modelMapper.createTypeMap(TvShow.class, TvShowModel.class);
        tvShowToTvShowModelTypeMap.<String>addMapping(src -> src.getCategory().getName(), (dest, value) -> dest.setCategory(value));

        return modelMapper;
    }
}
