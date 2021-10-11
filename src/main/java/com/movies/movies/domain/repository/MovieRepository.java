package com.movies.movies.domain.repository;

import com.movies.movies.domain.model.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Long> {

    List<Movie> findByTitleContaining(String Title);

    @Query("from Movie m where m.category.id =:category ")
    List<Movie> findMoviesByCategory(@Param("category")  Long id);


    List<Movie> findMoviesByActorsId(Long id);

    Optional<Movie> findByTitle(String title);
}
