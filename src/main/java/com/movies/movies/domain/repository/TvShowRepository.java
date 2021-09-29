package com.movies.movies.domain.repository;

import com.movies.movies.domain.model.TvShow;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TvShowRepository extends JpaRepository<TvShow, Long> {

    @Query("from TvShow t where t.category.id =:category ")
    List<TvShow> findMoviesByCategory(@Param("category")Long id);

    List<TvShow> findByTitleContaining(String title);
}
