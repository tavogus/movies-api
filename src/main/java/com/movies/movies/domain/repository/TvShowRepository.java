package com.movies.movies.domain.repository;

import com.movies.movies.domain.model.TvShow;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface TvShowRepository extends JpaRepository<TvShow, Long> {

    List<TvShow> findByTitleContaining(String title);

    Optional<TvShow> findByTitle(String title);

    @Query("SELECT t FROM TvShow t JOIN t.actors a WHERE a.name LIKE %?1%")
    List<TvShow> findTvShowsByActorsName(String name);

    @Query("SELECT t FROM TvShow t JOIN t.category c WHERE c.name LIKE %?1%")
    List<TvShow> findMoviesByCategoryName(String categoryName);
}
