package com.movie.moviedb.persistence.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "qualifications")
@Setter
@Getter
@NoArgsConstructor
public class QualificationEntity {
    @Id
    @Column(name = "id_user", nullable = false)
    private Long idUser;

    @Id
    @Column(name = "id_movie", nullable = false)
    private Long idMovie;

    @Column(nullable = false)
    private Integer score;

    @Column(length = 100)
    private String comment;

    @ManyToOne
    @JoinColumn(name = "id_user", referencedColumnName = "id_user",insertable = false, updatable = false)
    private UserEntity user;

    @ManyToOne
    @JoinColumn(name = "id_movie", referencedColumnName = "id_movie", insertable = false, updatable = false)
    @MapsId("idMovie")
    @JsonIgnore
    private MovieEntity movie;
}
