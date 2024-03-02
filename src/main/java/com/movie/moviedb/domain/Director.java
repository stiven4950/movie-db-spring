package com.movie.moviedb.domain;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Director {
    private Long id;

    @NotNull
    @Size(max = 255)
    private String name;

    @NotNull
    private LocalDateTime birthdate;

    @NotNull
    private String avatar;

    private Boolean state;
}
