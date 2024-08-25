package com.movie.moviedb.persistence.repository;

import com.movie.moviedb.persistence.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
    Optional<UserEntity> findByUsername(String username);
    long countByUsername(String username);
}
