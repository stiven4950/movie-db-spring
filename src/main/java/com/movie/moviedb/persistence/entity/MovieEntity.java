package com.movie.moviedb.persistence.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "movies")
@Setter
@Getter
@NoArgsConstructor
public class MovieEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_movie", nullable = false)
    private Long idMovie;

    @Column(nullable = false, length = 255, unique = true)
    private String title;

    @Column(nullable = false, length = 1000)
    private String synopsis;

    @Column(nullable = false)
    private Integer year;

    @Column(nullable = false)
    private Integer duration;

    @Column(name = "id_director", nullable = false)
    private Long idDirector;

    @Column(name = "id_genre", nullable = false)
    private Long idGenre;

    @Column(nullable = false, length = 255)
    private String poster;

    @Column(nullable = false, columnDefinition = "TINYINT")
    private Boolean state;

    @ManyToOne
    @JoinColumn(name = "id_director",referencedColumnName = "id_director", insertable = false, updatable = false)
    private DirectorEntity director;

    @ManyToOne
    @JoinColumn(name = "id_genre",referencedColumnName = "id_genre", insertable = false, updatable = false)
    private GenreEntity genre;

    @OneToMany(mappedBy = "movie")
    private List<ActorRoleEntity> actors;

    @OneToMany(mappedBy = "movie", fetch = FetchType.LAZY)
    private List<QualificationEntity> qualifications;
}
