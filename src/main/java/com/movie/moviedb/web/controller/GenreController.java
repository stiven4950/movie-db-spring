package com.movie.moviedb.web.controller;

import com.movie.moviedb.domain.Genre;
import com.movie.moviedb.domain.service.GenreService;
import com.movie.moviedb.web.validation.ValidateResponseBody;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/genres")
public class GenreController {
    private final GenreService genreService;

    @Autowired
    public GenreController(GenreService genreService) {
        this.genreService = genreService;
    }

    @PostMapping
    public ResponseEntity<?> insert(@Valid @RequestBody Genre genre, BindingResult result){
        if(result.hasErrors()) ValidateResponseBody.throwErrors(result);

        if(genre.getId() == null || !this.genreService.exists(genre.getId())){
            return new ResponseEntity<>(this.genreService.save(genre), HttpStatus.CREATED);
        }
        return ResponseEntity.badRequest().build();
    }

    @GetMapping
    public ResponseEntity<List<Genre>> getAll(){
        return ResponseEntity.ok(this.genreService.findAll());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") long id){
        if(this.genreService.exists(id)){
            this.genreService.delete(id);
            return ResponseEntity.ok().build();
        }

        return ResponseEntity.notFound().build();
    }
}
