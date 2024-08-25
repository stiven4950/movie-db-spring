package com.movie.moviedb.persistence.mapper;

import com.movie.moviedb.domain.Actor;
import com.movie.moviedb.domain.Director;
import com.movie.moviedb.domain.Movie;
import com.movie.moviedb.persistence.entity.ActorEntity;
import com.movie.moviedb.persistence.entity.DirectorEntity;
import com.movie.moviedb.persistence.entity.MovieEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class MapperUtil {
    private final ActorMapper actorMapper;
    private final DirectorMapper directorMapper;
    private final MovieMapper movieMapper;

    @Autowired
    public MapperUtil(ActorMapper actorMapper, DirectorMapper directorMapper, MovieMapper movieMapper) {
        this.actorMapper = actorMapper;
        this.directorMapper = directorMapper;
        this.movieMapper = movieMapper;
    }

    public Page<Director> toDirectorsPage(Page<DirectorEntity> directorEntities) {
        List<Director> directors = directorEntities.getContent()
                .stream()
                .map(this.directorMapper::toDomain)
                .collect(Collectors.toList());
        return new PageImpl<>(directors, directorEntities.getPageable(), directorEntities.getTotalElements());
    }

    public Page<Actor> toActorsPage(Page<ActorEntity> actorEntities) {
        List<Actor> actors = actorEntities.getContent()
                .stream()
                .map(this.actorMapper::toDomain)
                .collect(Collectors.toList());
        return new PageImpl<>(actors, actorEntities.getPageable(), actorEntities.getTotalElements());
    }

    public Page<Movie> toMoviesPage(Page<MovieEntity> actorEntities) {
        List<Movie> movies = actorEntities.getContent()
                .stream()
                .map(this.movieMapper::toDomain)
                .collect(Collectors.toList());
        return new PageImpl<>(movies, actorEntities.getPageable(), actorEntities.getTotalElements());
    }
}
