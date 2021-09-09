package com.movies.movies.domain.service;

import com.movies.movies.api.v1.ResourceUriHelper;
import com.movies.movies.api.v1.assembler.TvShowAssembler;
import com.movies.movies.api.v1.model.TvShowModel;
import com.movies.movies.domain.exception.BusinessException;
import com.movies.movies.domain.model.TvShow;
import com.movies.movies.domain.repository.TvShowRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

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

    public TvShowModel save(TvShow tvShow){
        boolean alreadyExistingTvShow = tvShowRepository.findByTitle(tvShow.getTitle()).stream().anyMatch(existingTvShow -> !existingTvShow.equals(tvShow));

        if (alreadyExistingTvShow){
            throw new BusinessException("Already exists a tv show with this title");
        }

        tvShowRepository.save(tvShow);
        TvShowModel tvShowModel = tvShowAssembler.toModel(tvShow);
        ResourceUriHelper.addUriInResponseHeader(tvShowModel.getId());
        return tvShowModel;
    }

    public List<TvShowModel> getAll(){
        return tvShowAssembler.toCollectionModel(tvShowRepository.findAll());
    }

    public ResponseEntity<TvShowModel> getTvShow(Long id){
        return tvShowRepository.findById(id).map(tvShow -> ResponseEntity.ok(tvShowAssembler.toModel(tvShow))).orElse(ResponseEntity.notFound().build());
    }
}
