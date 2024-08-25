package com.movie.moviedb.persistence.mapper;

import com.movie.moviedb.domain.Actor;
import com.movie.moviedb.domain.ActorRole;
import com.movie.moviedb.domain.Genre;
import com.movie.moviedb.domain.Movie;
import com.movie.moviedb.persistence.entity.ActorEntity;
import com.movie.moviedb.persistence.entity.MovieEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class MovieMapper {
    private final ActorRoleMapper actorRoleRoleMapper;
    private final DirectorMapper directorMapper;
    private final GenreMapper genreMapper;

    @Autowired
    public MovieMapper(ActorRoleMapper actorRoleRoleMapper, DirectorMapper directorMapper, GenreMapper genreMapper) {
        this.actorRoleRoleMapper = actorRoleRoleMapper;
        this.directorMapper = directorMapper;
        this.genreMapper = genreMapper;
    }

    public MovieEntity toEntity(Movie movie) {
        MovieEntity movieEntity = new MovieEntity();

        movieEntity.setIdMovie(movie.getId());
        movieEntity.setTitle(movie.getTitle());
        movieEntity.setPoster(movie.getPoster());
        movieEntity.setIdDirector(movie.getIdDirector());
        movieEntity.setDuration(movie.getDuration());
        movieEntity.setSynopsis(movie.getSynopsis());
        movieEntity.setIdGenre(movie.getIdGenre());

        return movieEntity;
    }

    public Movie toDomain(MovieEntity movieEntity){
        Movie movie = new Movie();

        movie.setId(movieEntity.getIdMovie());
        movie.setTitle(movieEntity.getTitle());
        movie.setPoster(movieEntity.getPoster());
        movie.setIdDirector(movieEntity.getIdDirector());
        movie.setDuration(movieEntity.getDuration());
        movie.setSynopsis(movieEntity.getSynopsis());
        movie.setIdGenre(movieEntity.getIdGenre());
        movie.setState(movieEntity.getState());

        GenreMapper genreMapper = new GenreMapper();

        movie.setGenre(genreMapper.toDomain(movieEntity.getGenre()));
        System.out.println(movieEntity.getGenre().getName());

        // movie.setDirector(this.directorMapper.toDomain(movieEntity.getDirector()));

        // List<ActorRole> actorRoles = movieEntity.getActors().stream().map(this.actorRoleRoleMapper::toDomain).toList();
        // movie.setActors(actorRoles);

        return movie;
    }
}
