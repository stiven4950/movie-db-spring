package com.movie.moviedb.persistence.mapper;

import com.movie.moviedb.domain.Director;
import com.movie.moviedb.domain.Movie;
import com.movie.moviedb.persistence.entity.DirectorEntity;
import com.movie.moviedb.persistence.entity.MovieEntity;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper(componentModel = "spring")
@Component
public interface DirectorMapper {
    @Mappings({
            @Mapping(source = "idDirector", target = "id")
    })
    Director toDirector(DirectorEntity director);
    List<Director> toMovies(List<DirectorEntity> directors);

    @InheritInverseConfiguration
    @Mappings({
            @Mapping(source = "id", target ="idDirector"),
            @Mapping(target = "movies", ignore = true)
    })
    DirectorEntity toDirectorEntity(Director director);
}
