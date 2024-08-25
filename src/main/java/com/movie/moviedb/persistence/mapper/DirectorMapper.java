package com.movie.moviedb.persistence.mapper;

import com.movie.moviedb.domain.Director;
import com.movie.moviedb.persistence.entity.DirectorEntity;
import org.springframework.stereotype.Component;

@Component
public class DirectorMapper {
    public DirectorEntity toEntity(Director director) {
        DirectorEntity directorEntity = new DirectorEntity();

        directorEntity.setIdDirector(director.getId());
        directorEntity.setName(director.getName());
        directorEntity.setAvatar(director.getAvatar());
        directorEntity.setBirthdate(director.getBirthdate());

        return directorEntity;
    }

    public Director toDomain(DirectorEntity directorEntity){
        Director director = new Director();

        director.setId(directorEntity.getIdDirector());
        director.setName(directorEntity.getName());
        director.setAvatar(directorEntity.getAvatar());
        director.setBirthdate(directorEntity.getBirthdate());
        director.setState(directorEntity.getState());

        return director;
    }
}
