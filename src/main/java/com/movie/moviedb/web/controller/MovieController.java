package com.movie.moviedb.web.controller;

import com.movie.moviedb.domain.Actor;
import com.movie.moviedb.domain.Movie;
import com.movie.moviedb.domain.service.ActorService;
import com.movie.moviedb.domain.service.MovieService;
import com.movie.moviedb.web.validation.ValidateResponseBody;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("/movies")
public class MovieController {
    private final MovieService movieService;

    @Autowired
    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }

    @PostMapping
    public ResponseEntity<?> insert(@Valid @RequestBody Movie movie, BindingResult result){
        if(result.hasErrors()) ValidateResponseBody.throwErrors(result);

        if(movie.getId() == null || !this.movieService.exists(movie.getId())){
            try{
                return new ResponseEntity<>(this.movieService.save(movie), HttpStatus.CREATED);
            }catch(IOException e){
                return ResponseEntity.badRequest().body(e.getCause());
            }
        }
        return ResponseEntity.badRequest().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@Valid @RequestBody Movie movie, @PathVariable("id") long id, BindingResult result){
        if(result.hasErrors()) ValidateResponseBody.throwErrors(result);

        if(this.movieService.exists(id)){
            movie.setId(id);
            try{
                return new ResponseEntity<>(this.movieService.save(movie), HttpStatus.CREATED);
            }catch(IOException e){
                return ResponseEntity.badRequest().body(e.getCause());
            }
        }

        return ResponseEntity.badRequest().build();
    }

    @GetMapping
    public ResponseEntity<Page<Movie>> getAll(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int elements,
            @RequestParam(defaultValue = "title") String sortBy,
            @RequestParam(defaultValue = "ASC") String sortDirection){
        return ResponseEntity.ok(this.movieService.getAll(page, elements, sortBy, sortDirection));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Movie> getById(@PathVariable long id){
        return this.movieService.getbyId(id).map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") long id){
        if(this.movieService.exists(id)){
            this.movieService.delete(id);
            return ResponseEntity.ok().build();
        }

        return ResponseEntity.notFound().build();
    }
}
