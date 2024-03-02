package com.movie.moviedb.persistence.mapper;

import com.movie.moviedb.domain.Actor;
import com.movie.moviedb.domain.ActorRole;
import com.movie.moviedb.persistence.entity.ActorEntity;
import com.movie.moviedb.persistence.entity.ActorRoleEntity;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper(componentModel = "spring", uses = {ActorMapper.class})
@Component
public interface ActorRoleMapper {
    @Mappings({
            @Mapping(source = "idActor", target = "idActor"),
            @Mapping(source = "idMovie", target = "idMovie"),
            @Mapping(source = "name", target = "name"),
    })
    ActorRole toActorRole(ActorRoleEntity actorRole);
    List<ActorRole> toActorRoles(List<ActorRoleEntity> actorRoles);

    @InheritInverseConfiguration
    @Mappings({
            @Mapping(target = "movie", ignore = true)
    })
    ActorRoleEntity toActorRoleEntity(ActorRole actorRole);
}
