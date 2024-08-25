package com.movie.moviedb.persistence.mapper;

import com.movie.moviedb.domain.ActorRole;
import com.movie.moviedb.persistence.entity.ActorRoleEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ActorRoleMapper {
    private final ActorMapper actorMapper;

    @Autowired
    public ActorRoleMapper(ActorMapper actorMapper) {
        this.actorMapper = actorMapper;
    }

    public ActorRoleEntity toEntity(ActorRole actorRole) {
        ActorRoleEntity actorEntity = new ActorRoleEntity();

        actorEntity.setIdActor(actorRole.getIdActor());
        actorEntity.setIdMovie(actorRole.getIdMovie());
        actorEntity.setName(actorRole.getName());

        actorEntity.setActor(this.actorMapper.toEntity(actorRole.getActor()));

        return actorEntity;
    }

    public ActorRole toDomain(ActorRoleEntity actorEntity){
        ActorRole actor = new ActorRole();

        actor.setIdActor(actorEntity.getIdActor());
        actor.setIdMovie(actorEntity.getIdMovie());
        actor.setName(actorEntity.getName());

        actor.setActor(this.actorMapper.toDomain(actorEntity.getActor()));

        return actor;
    }
}
