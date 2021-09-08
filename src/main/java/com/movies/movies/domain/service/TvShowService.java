package com.movies.movies.domain.service;

import com.movies.movies.domain.exception.BusinessException;
import com.movies.movies.domain.model.TvShow;
import com.movies.movies.domain.repository.TvShowRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TvShowService {

    private final TvShowRepository tvShowRepository;

    @Autowired
    public TvShowService(TvShowRepository tvShowRepository) {
        this.tvShowRepository = tvShowRepository;
    }

    public TvShow save(TvShow tvShow){
        boolean alreadyExistingTvShow = tvShowRepository.findByTitle(tvShow.getTitle()).stream().anyMatch(existingTvShow -> !existingTvShow.equals(tvShow));

        if (alreadyExistingTvShow){
            throw new BusinessException("Already exists a tv show with this title");
        }

        return tvShowRepository.save(tvShow);
    }
}
