package com.movie.moviedb.persistence.repository;

import com.movie.moviedb.persistence.entity.MovieEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface MovieRepository extends ListCrudRepository<MovieEntity, Long> {
    Page<MovieEntity> findByStateTrue(Pageable pageable);
    Optional<MovieEntity> findByIdMovieAndStateTrue(long idMovie);

    @Query("UPDATE MovieEntity m SET m.poster = :posterUrl WHERE m.idMovie = :id")
    @Modifying
    void updatePoster(@Param("posterUrl") String posterUrl, @Param("id") long id);

    @Query("UPDATE MovieEntity m SET m.state = :newState WHERE m.idMovie = :idMovie")
    @Modifying
    void updateState(@Param("newState") boolean newState, @Param("idMovie") long idMovie);

    long countByIdMovieAndStateTrue(long idMovie);
}
