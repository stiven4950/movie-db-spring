package com.movie.moviedb.persistence.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "actors")
@Setter
@Getter
@NoArgsConstructor
public class ActorEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_actor", nullable = false)
    private Long idActor;

    @Column(nullable = false, length = 255)
    private String name;

    @Column(nullable = false, columnDefinition = "DATETIME")
    private LocalDateTime birthdate;

    @Column(length = 255)
    private String avatar;

    @Column(nullable = false, columnDefinition = "TINYINT")
    private Boolean state;

    @OneToMany(mappedBy = "actor")
    private List<ActorRoleEntity> actorRoles;
}
