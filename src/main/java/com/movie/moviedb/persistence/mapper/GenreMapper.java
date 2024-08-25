package com.movie.moviedb.persistence.mapper;

import com.movie.moviedb.domain.Genre;
import com.movie.moviedb.domain.Genre;
import com.movie.moviedb.persistence.entity.GenreEntity;
import org.springframework.stereotype.Component;

@Component
public class GenreMapper {
    public GenreEntity toEntity(Genre genre) {
        GenreEntity genreEntity = new GenreEntity();

        genreEntity.setIdGenre(genre.getId());
        genreEntity.setName(genre.getName());

        return genreEntity;
    }

    public Genre toDomain(GenreEntity genreEntity){
        Genre genre = new Genre();

        genre.setId(genreEntity.getIdGenre());
        genre.setName(genreEntity.getName());
        genre.setState(genreEntity.getState());

        return genre;
    }
}
