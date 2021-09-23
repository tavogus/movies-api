package com.movies.movies.domain.service;

import com.movies.movies.api.v1.ResourceUriHelper;
import com.movies.movies.api.v1.assembler.TvShowAssembler;
import com.movies.movies.api.v1.model.TvShowModel;
import com.movies.movies.domain.exception.ActorNotFoundException;
import com.movies.movies.domain.exception.BusinessException;
import com.movies.movies.domain.exception.CategoryNotFoundException;
import com.movies.movies.domain.exception.TvShowNotFoundException;
import com.movies.movies.domain.model.TvShow;
import com.movies.movies.domain.repository.TvShowRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class TvShowService {

    private final TvShowRepository tvShowRepository;
    private final TvShowAssembler tvShowAssembler;

    @Autowired
    public TvShowService(TvShowRepository tvShowRepository, TvShowAssembler tvShowAssembler) {
        this.tvShowRepository = tvShowRepository;
        this.tvShowAssembler = tvShowAssembler;
    }

    @Transactional
    public TvShowModel save(TvShow tvShow){
        try{
            return tvShowAssembler.toModel(tvShowRepository.save(tvShow));
        } catch (CategoryNotFoundException | ActorNotFoundException e){
            throw new BusinessException(e.getMessage());
        }
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

    private TvShow findOrFail(Long id) {
        return tvShowRepository.findById(id).orElseThrow(() -> new TvShowNotFoundException(id));
    }
}
