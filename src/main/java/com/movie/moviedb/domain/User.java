package com.movie.moviedb.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
public class User {
    private String name;
    private String username;
    private String email;
    private String password;
}
