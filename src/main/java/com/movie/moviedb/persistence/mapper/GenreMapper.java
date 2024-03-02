package com.movie.moviedb.persistence.mapper;

import com.movie.moviedb.domain.Genre;
import com.movie.moviedb.persistence.entity.GenreEntity;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper(componentModel = "spring")
@Component
public interface GenreMapper {
    @Mappings({
            @Mapping(source = "idGenre", target = "id"),
    })
    Genre toGenre(GenreEntity genre);
    List<Genre> toGenres(List<GenreEntity> genres);

    @InheritInverseConfiguration
    @Mappings({
            @Mapping(target = "movies", ignore = true),
    })
    GenreEntity toGenreEntity(Genre genre);
}
