package com.movie.moviedb.domain;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Genre {
    private Long id;

    @NotNull
    @Size(max = 255)
    private String name;

    private Boolean state;
}
