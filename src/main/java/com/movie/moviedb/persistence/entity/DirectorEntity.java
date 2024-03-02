package com.movie.moviedb.persistence.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "directors")
@Setter
@Getter
@NoArgsConstructor
public class DirectorEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_director", nullable = false)
    private Long idDirector;

    @Column(nullable = false, length = 255)
    private String name;

    @Column(nullable = false, columnDefinition = "DATETIME")
    private LocalDateTime birthdate;

    @Column(length = 255)
    private String avatar;

    @Column(nullable = false, columnDefinition = "TINYINT")
    private Boolean state;

    @OneToMany(mappedBy = "director", fetch = FetchType.LAZY)
    private List<MovieEntity> movies;
}
