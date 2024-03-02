package com.movie.moviedb.web.controller;

import com.movie.moviedb.domain.Director;
import com.movie.moviedb.domain.service.DirectorService;
import com.movie.moviedb.web.validation.ValidateResponseBody;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("/directors")
public class DirectorController {
    private final DirectorService directorService;

    @Autowired
    public DirectorController(DirectorService directorService) {
        this.directorService = directorService;
    }

    @PostMapping
    public ResponseEntity<?> insert(@Valid @RequestBody Director director, BindingResult result){
        if(result.hasErrors()) ValidateResponseBody.throwErrors(result);

        if(director.getId() == null || !this.directorService.exists(director.getId())){
            try{
                return new ResponseEntity<>(this.directorService.save(director), HttpStatus.CREATED);
            }catch(IOException e){
                return ResponseEntity.badRequest().body(e.getCause());
            }
        }
        return ResponseEntity.badRequest().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@Valid @RequestBody Director director, @PathVariable("id") long id, BindingResult result){
        if(result.hasErrors()) ValidateResponseBody.throwErrors(result);

        if(this.directorService.exists(id)){
            director.setId(id);
            try{
                return new ResponseEntity<>(this.directorService.save(director), HttpStatus.CREATED);
            }catch(IOException e){
                return ResponseEntity.badRequest().body(e.getCause());
            }
        }

        return ResponseEntity.badRequest().build();
    }

    @GetMapping
    public ResponseEntity<Page<Director>> getAll(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int elements,
            @RequestParam(defaultValue = "name") String sortBy,
            @RequestParam(defaultValue = "ASC") String sortDirection){

        return ResponseEntity.ok(this.directorService.getAll(page, elements, sortBy, sortDirection));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Director> getById(@PathVariable long id){
        return this.directorService.getById(id).map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") long id){
        if(this.directorService.exists(id)){
            this.directorService.delete(id);
            return ResponseEntity.ok().build();
        }

        return ResponseEntity.notFound().build();
    }
}
