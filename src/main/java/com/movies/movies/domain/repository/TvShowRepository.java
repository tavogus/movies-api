package com.movies.movies.domain.repository;

import com.movies.movies.domain.model.TvShow;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TvShowRepository extends JpaRepository<TvShow, Long> {
    Optional<TvShow> findByTitle(String title);
}
