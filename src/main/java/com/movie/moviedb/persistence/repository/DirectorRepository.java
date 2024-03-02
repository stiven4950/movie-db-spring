package com.movie.moviedb.persistence.repository;


import com.movie.moviedb.persistence.entity.DirectorEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface DirectorRepository extends ListCrudRepository<DirectorEntity, Long> {
    Page<DirectorEntity> findByStateTrue(Pageable pageable);
    Optional<DirectorEntity> findByIdDirectorAndStateTrue(long idDirector);

    @Query(value = "UPDATE directors SET state = :newState WHERE id_director = :idDirector", nativeQuery = true)
    @Modifying
    void updateState(@Param("newState") boolean newState, @Param("idDirector") long idDirector);

    long countByIdDirectorAndStateTrue(long idDirector);
}
