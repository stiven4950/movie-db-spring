package com.movie.moviedb.persistence.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "genres")
@Setter
@Getter
@NoArgsConstructor
public class GenreEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_genre", nullable = false)
    private Long idGenre;

    @Column(length = 255, nullable = false)
    private String name;

    @Column(nullable = false, columnDefinition = "TINYINT")
    private Boolean state;

    @OneToMany(mappedBy = "genre", fetch = FetchType.LAZY)
    private List<MovieEntity> movies;
}
