package com.movie.moviedb.web.controller;

import com.movie.moviedb.domain.Actor;
import com.movie.moviedb.domain.Director;
import com.movie.moviedb.domain.service.ActorService;
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
@RequestMapping("/actors")
public class ActorController {
    private final ActorService actorService;

    @Autowired
    public ActorController(ActorService actorService) {
        this.actorService = actorService;
    }

    @PostMapping
    public ResponseEntity<?> insert(@Valid @RequestBody Actor actor, BindingResult result){
        if(result.hasErrors()) ValidateResponseBody.throwErrors(result);

        if(actor.getId() == null || !this.actorService.exists(actor.getId())){
            try{
                return new ResponseEntity<>(this.actorService.save(actor), HttpStatus.CREATED);
            }catch(IOException e){
                return ResponseEntity.badRequest().body(e.getCause());
            }
        }
        return ResponseEntity.badRequest().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@Valid @RequestBody Actor actor, @PathVariable("id") long id, BindingResult result){
        if(result.hasErrors()) ValidateResponseBody.throwErrors(result);

        if(this.actorService.exists(id)){
            actor.setId(id);
            try{
                return new ResponseEntity<>(this.actorService.save(actor), HttpStatus.CREATED);
            }catch(IOException e){
                return ResponseEntity.badRequest().body(e.getCause());
            }
        }

        return ResponseEntity.badRequest().build();
    }

    @GetMapping
    public ResponseEntity<Page<Actor>> getAll(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int elements,
            @RequestParam(defaultValue = "name") String sortBy,
            @RequestParam(defaultValue = "ASC") String sortDirection){

        return ResponseEntity.ok(this.actorService.getAll(page, elements, sortBy, sortDirection));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Actor> getById(@PathVariable long id){
        return this.actorService.getById(id).map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") long id){
        if(this.actorService.exists(id)){
            this.actorService.delete(id);
            return ResponseEntity.ok().build();
        }

        return ResponseEntity.notFound().build();
    }
}
