package com.movie.moviedb.persistence.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "actor_roles")
@IdClass(ActorRoleId.class)
@Setter
@Getter
@NoArgsConstructor
public class ActorRoleEntity {
    @Id
    @Column(name = "id_movie", nullable = false)
    private Long idMovie;

    @Id
    @Column(name = "id_actor", nullable = false)
    private Long idActor;

    @Column(nullable = false, length = 255)
    private String name;

    @ManyToOne
    @JoinColumn(name = "id_movie", referencedColumnName = "id_movie", insertable = false, updatable = false)
    @MapsId("idMovie")
    @JsonIgnore
    private MovieEntity movie;

    @ManyToOne
    @JoinColumn(name = "id_actor", referencedColumnName = "id_actor", insertable = false, updatable = false)
    private ActorEntity actor;
}
