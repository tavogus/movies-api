package com.movies.movies.api.v1.controller;

import com.movies.movies.api.v1.ResourceUriHelper;
import com.movies.movies.api.v1.assembler.TvshowAssembler;
import com.movies.movies.api.v1.model.TvShowModel;
import com.movies.movies.domain.model.TvShow;
import com.movies.movies.domain.repository.TvShowRepository;
import com.movies.movies.domain.service.TvShowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(path = "/v1/tvshows", produces = MediaType.APPLICATION_JSON_VALUE)
public class TvShowController {

    private final TvShowRepository tvShowRepository;
    private final TvShowService tvShowService;
    private final TvshowAssembler tvShowAssembler;

    @Autowired
    public TvShowController(TvShowRepository tvShowRepository, TvShowService tvShowService, TvshowAssembler tvShowAssembler) {
        this.tvShowRepository = tvShowRepository;
        this.tvShowService = tvShowService;
        this.tvShowAssembler = tvShowAssembler;
    }

    @GetMapping
    public List<TvShowModel> getAll(){
        return tvShowAssembler.toCollectionModel(tvShowRepository.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<TvShowModel> getTvshow(@PathVariable Long id){
        return tvShowRepository.findById(id).map(tvShow -> ResponseEntity.ok(tvShowAssembler.toModel(tvShow))).orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public TvShowModel addTvShow(@Valid @RequestBody TvShow tvShow){
        tvShowService.save(tvShow);
        TvShowModel tvShowModel = tvShowAssembler.toModel(tvShow);
        ResourceUriHelper.addUriInResponseHeader(tvShowModel.getId());
        return tvShowModel;
    }
}
