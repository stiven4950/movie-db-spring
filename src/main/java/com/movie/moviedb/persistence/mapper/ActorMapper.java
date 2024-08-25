package com.movie.moviedb.persistence.mapper;

import com.movie.moviedb.domain.Actor;
import com.movie.moviedb.persistence.entity.ActorEntity;
import org.springframework.stereotype.Component;

@Component
public class ActorMapper {
    public ActorEntity toEntity(Actor actor) {
        ActorEntity actorEntity = new ActorEntity();

        actorEntity.setIdActor(actor.getId());
        actorEntity.setName(actor.getName());
        actorEntity.setAvatar(actor.getAvatar());
        actorEntity.setBirthdate(actor.getBirthdate());

        return actorEntity;
    }

    public Actor toDomain(ActorEntity actorEntity){
        Actor actor = new Actor();

        actor.setId(actorEntity.getIdActor());
        actor.setName(actorEntity.getName());
        actor.setAvatar(actorEntity.getAvatar());
        actor.setBirthdate(actorEntity.getBirthdate());
        actor.setState(actorEntity.getState());

        return actor;
    }
}
