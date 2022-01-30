package com.movies.movies.domain.service;

import com.movies.movies.api.v1.assembler.TvShowAssembler;
import com.movies.movies.api.v1.model.TvShowModel;
import com.movies.movies.domain.exception.*;
import com.movies.movies.domain.model.Actor;
import com.movies.movies.domain.model.Category;
import com.movies.movies.domain.model.TvShow;
import com.movies.movies.domain.repository.ActorRepository;
import com.movies.movies.domain.repository.CategoryRepository;
import com.movies.movies.domain.repository.TvShowRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class TvShowService {

    private final CategoryRepository categoryRepository;
    private final ActorRepository actorRepository;
    private final TvShowRepository tvShowRepository;
    private final TvShowAssembler tvShowAssembler;

    @Autowired
    public TvShowService(CategoryRepository categoryRepository, ActorRepository actorRepository, TvShowRepository tvShowRepository, TvShowAssembler tvShowAssembler) {
        this.categoryRepository = categoryRepository;
        this.actorRepository = actorRepository;
        this.tvShowRepository = tvShowRepository;
        this.tvShowAssembler = tvShowAssembler;
    }

    @Transactional
    public TvShowModel save(TvShow tvShow){

        boolean tvshowExists = tvShowRepository.findByTitle(tvShow.getTitle()).stream().anyMatch(existingTvShow -> !existingTvShow.equals(tvShow));

        if (tvshowExists){
            throw new BusinessException("Already exists a tv show with this title!");
        }

        Optional<Category> category = categoryRepository.findById(tvShow.getCategory().getId());

        if (!category.isPresent()){
            throw new CategoryNotFoundException("There is no category with the given id");
        }

        try {
            return tvShowAssembler.toModel(tvShowRepository.save(tvShow));
        } catch (EntityNotFoundException e) {
            throw new BusinessException(e.getMessage(), e);
        }


    }
    @Transactional(readOnly = true)
    public CollectionModel<TvShowModel> getAll(){
        return tvShowAssembler.toCollectionModel(tvShowRepository.findAll());
    }

    @Transactional(readOnly = true)
    public TvShowModel getTvShow(Long id){
        TvShow tvShow = findOrFail(id);

        return tvShowAssembler.toModel(tvShow);
    }

    @Transactional(readOnly = true)
    public CollectionModel<TvShowModel> findByTitle(String title) {
        return tvShowAssembler.toCollectionModel(tvShowRepository.findByTitleContaining(title));
    }

    @Transactional(readOnly = true)
    public CollectionModel<TvShowModel> findByCategory(String categoryName) {
        List<TvShow> tvShows = tvShowRepository.findMoviesByCategoryName(categoryName);

        if (tvShows.isEmpty()) {
            throw new TvShowNotFoundException("TvShow with this category not found");
        }

        return tvShowAssembler.toCollectionModel(tvShows);
    }

    @Transactional(readOnly = true)
    public CollectionModel<TvShowModel> findByActor(String actorName) {

        List<TvShow> tvShows = tvShowRepository.findTvShowsByActorsName(actorName);

        if (tvShows.isEmpty()) {
            throw new TvShowNotFoundException("TvShow with this actor not found");
        }

        return tvShowAssembler.toCollectionModel(tvShows);
    }

    private TvShow findOrFail(Long id) {
        return tvShowRepository.findById(id).orElseThrow(() -> new TvShowNotFoundException(id));
    }
}
