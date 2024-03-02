package com.movie.moviedb.persistence.mapper;

import com.movie.moviedb.domain.Movie;
import com.movie.moviedb.persistence.entity.MovieEntity;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper(componentModel = "spring", uses = {GenreMapper.class,DirectorMapper.class,  ActorRoleMapper.class})
@Component
public interface MovieMapper {
    @Mappings({
            @Mapping(source = "idMovie", target = "id"),
    })
    Movie toMovie(MovieEntity actor);
    List<Movie> toMovies(List<MovieEntity> actors);

    @InheritInverseConfiguration
    @Mappings({
            @Mapping(target = "qualifications", ignore = true),
            @Mapping(target = "actors", ignore = true),
    })
    MovieEntity toMovieEntity(Movie actor);
}
