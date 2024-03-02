package com.movie.moviedb.domain;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.List;

@Data
public class Movie {
    private Long id;

    @NotNull
    @Size(max = 255)
    private String title;

    @NotNull
    @Size(max = 1000)
    private String synopsis;

    @NotNull
    @Min(value = 1900, message = "year must be greater than or equals 1900")
    @Max(value = 2100, message = "year must be less than or equals to 2100")
    private Integer year;

    @NotNull
    @Min(value = 1, message = "duration must be greater than or equals to 1 minute")
    @Max(value = 600, message = "duration must be less than or equals to 600 minutes (10 hours)")
    private Integer duration;

    @NotNull
    private Long idDirector;

    @NotNull
    private Long idGenre;

    @NotNull
    private String poster;

    private Boolean state;

    private Director director;
    private Genre genre;

    @NotNull
    private List<ActorRole> actors;
}
