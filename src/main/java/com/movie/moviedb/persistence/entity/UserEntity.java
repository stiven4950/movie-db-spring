package com.movie.moviedb.persistence.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "users")
@Setter
@Getter
@NoArgsConstructor
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_user", nullable = false)
    private Long idUser;

    @Column(length = 255)
    private String name;

    @Column(nullable = false, unique = true, length = 20)
    private String username;

    @Column(unique = true, length = 100)
    private String email;

    @Column(nullable = false, length = 200)
    private String password;

    @Column(length = 255)
    private String avatar;

    @Column(nullable = false, columnDefinition = "TINYINT")
    private Boolean locked;

    @Column(nullable = false, columnDefinition = "TINYINT")
    private Boolean disabled;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private List<QualificationEntity> qualifications;

    @OneToMany(mappedBy = "user", fetch=FetchType.EAGER)
    private List<UserRoleEntity> roles;
}
