package com.movie.moviedb.persistence.repository;


import com.movie.moviedb.persistence.entity.ActorEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface ActorRepository extends ListCrudRepository<ActorEntity, Long> {
    Page<ActorEntity> findByStateTrue(Pageable pageable);
    Optional<ActorEntity> findByIdActorAndStateTrue(long idActor);

    @Query(value = "UPDATE actors SET state = :newState WHERE id_actor = :idActor", nativeQuery = true)
    @Modifying
    void updateState(@Param("newState") boolean newState, @Param("idActor") long idActor);

    long countByIdActorAndStateTrue(long idActor);
}
