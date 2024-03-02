package com.movie.moviedb.persistence.repository;

import com.movie.moviedb.persistence.entity.GenreEntity;
import org.springframework.data.repository.ListCrudRepository;

import java.util.List;

public interface GenreRepository extends ListCrudRepository<GenreEntity, Long> {
    List<GenreEntity> findByStateTrue();

    long countByIdGenreAndStateTrue(long idGenre);
}
