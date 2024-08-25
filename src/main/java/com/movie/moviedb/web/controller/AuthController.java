package com.movie.moviedb.web.controller;

import com.movie.moviedb.domain.dto.LoginRegisterDto;
import com.movie.moviedb.domain.service.UserService;
import com.movie.moviedb.web.config.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {
    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;
    private final UserService userService;

    @Autowired
    public AuthController(AuthenticationManager manager, JwtUtil jwtUtil, UserService userService) {
        this.authenticationManager = manager;
        this.jwtUtil = jwtUtil;
        this.userService = userService;
    }

    @PostMapping("/login")
    public ResponseEntity<Void> login(@RequestBody LoginRegisterDto loginRegisterDto) {
        UsernamePasswordAuthenticationToken login = new UsernamePasswordAuthenticationToken(
                loginRegisterDto.getUsername(), loginRegisterDto.getPassword()
        );

        Authentication authentication = this.authenticationManager.authenticate(login);

        String jwt = this.jwtUtil.create(loginRegisterDto.getUsername());
        return ResponseEntity.ok().header(HttpHeaders.AUTHORIZATION, jwt).build();
    }

    @PostMapping("/register")
    public ResponseEntity<Void> register(@RequestBody LoginRegisterDto register){
        if(!this.userService.exists(register.getUsername())){
            PasswordEncoder encoder = new BCryptPasswordEncoder(14);
            String hashedPassword = encoder.encode(register.getPassword());

            if (this.userService.register(register.getUsername(), hashedPassword)){
                return ResponseEntity.ok().build();
            }
        }

        return ResponseEntity.badRequest().build();
    }
}
