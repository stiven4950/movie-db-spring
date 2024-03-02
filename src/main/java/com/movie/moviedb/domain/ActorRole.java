package com.movie.moviedb.domain;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class ActorRole {
    @NotNull
    private Long idMovie;

    @NotNull
    private Long idActor;

    @Size(max = 255)
    private String name;

    private Actor actor;
}
