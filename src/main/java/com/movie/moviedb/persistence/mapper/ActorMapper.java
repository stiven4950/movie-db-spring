package com.movie.moviedb.persistence.mapper;

import com.movie.moviedb.domain.Actor;
import com.movie.moviedb.persistence.entity.ActorEntity;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ActorMapper {
    @Mappings({
            @Mapping(source = "idActor", target = "id"),
    })
    Actor toActor(ActorEntity actor);
    List<Actor> toActors(List<ActorEntity> actors);

    @InheritInverseConfiguration
    @Mappings({
            @Mapping(target = "actorRoles", ignore = true)
    })
    ActorEntity toActorEntity(Actor actor);
}
