package com.movie.moviedb.persistence.mapper;

import com.movie.moviedb.domain.Actor;
import com.movie.moviedb.domain.Director;
import com.movie.moviedb.domain.Movie;
import com.movie.moviedb.persistence.entity.ActorEntity;
import com.movie.moviedb.persistence.entity.DirectorEntity;
import com.movie.moviedb.persistence.entity.MovieEntity;
import org.mapstruct.factory.Mappers;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class MapperUtil {
    public static Page<Director> toDirectorsPage(Page<DirectorEntity> directorEntities) {
        List<Director> directors = directorEntities.getContent()
                .stream()
                .map(Mappers.getMapper(DirectorMapper.class)::toDirector)
                .collect(Collectors.toList());
        return new PageImpl<>(directors, directorEntities.getPageable(), directorEntities.getTotalElements());
    }

    public static Page<Actor> toActorsPage(Page<ActorEntity> actorEntities) {
        List<Actor> actors = actorEntities.getContent()
                .stream()
                .map(Mappers.getMapper(ActorMapper.class)::toActor)
                .collect(Collectors.toList());
        return new PageImpl<>(actors, actorEntities.getPageable(), actorEntities.getTotalElements());
    }

    public static Page<Movie> toMoviesPage(Page<MovieEntity> actorEntities) {
        List<Movie> movies = actorEntities.getContent()
                .stream()
                .map(Mappers.getMapper(MovieMapper.class)::toMovie)
                .collect(Collectors.toList());
        return new PageImpl<>(movies, actorEntities.getPageable(), actorEntities.getTotalElements());
    }
}
