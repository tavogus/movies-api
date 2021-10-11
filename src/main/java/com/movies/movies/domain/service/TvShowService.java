package com.movies.movies.domain.service;

import com.movies.movies.api.v1.assembler.TvShowAssembler;
import com.movies.movies.api.v1.model.TvShowModel;
import com.movies.movies.domain.exception.BusinessException;
import com.movies.movies.domain.exception.CategoryNotFoundException;
import com.movies.movies.domain.exception.TvShowNotFoundException;
import com.movies.movies.domain.model.Category;
import com.movies.movies.domain.model.TvShow;
import com.movies.movies.domain.repository.CategoryRepository;
import com.movies.movies.domain.repository.TvShowRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class TvShowService {

    private final CategoryRepository categoryRepository;
    private final TvShowRepository tvShowRepository;
    private final TvShowAssembler tvShowAssembler;

    @Autowired
    public TvShowService(CategoryRepository categoryRepository, TvShowRepository tvShowRepository, TvShowAssembler tvShowAssembler) {
        this.categoryRepository = categoryRepository;
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

        if (category.isEmpty()){
            throw new CategoryNotFoundException("There is no category with the given id");
        }

        return tvShowAssembler.toModel(tvShowRepository.save(tvShow));

    }
    @Transactional(readOnly = true)
    public CollectionModel<TvShowModel> getAll(){
        return tvShowAssembler.toCollectionModel(tvShowRepository.findAll());
    }

    @Transactional
    public TvShowModel getTvShow(Long id){
        TvShow tvShow = findOrFail(id);

        return tvShowAssembler.toModel(tvShow);
    }

    @Transactional
    public CollectionModel<TvShowModel> findByTitle(String title) {
        return tvShowAssembler.toCollectionModel(tvShowRepository.findByTitleContaining(title));
    }

    @Transactional
    public CollectionModel<TvShowModel> findByCategory(String categoryName) {
        Optional<Category> movie = categoryRepository.findByName(categoryName);

        return tvShowAssembler.toCollectionModel(tvShowRepository.findMoviesByCategory(movie.get().getId()));
    }

    private TvShow findOrFail(Long id) {
        return tvShowRepository.findById(id).orElseThrow(() -> new TvShowNotFoundException(id));
    }
}
