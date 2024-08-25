package com.movie.moviedb.domain.service;

import com.movie.moviedb.constant.Constants;
import com.movie.moviedb.domain.Genre;
import com.movie.moviedb.persistence.entity.GenreEntity;
import com.movie.moviedb.persistence.mapper.GenreMapper;
import com.movie.moviedb.persistence.repository.GenreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GenreService {
    private final GenreRepository genreRepository;
    private final GenreMapper mapper;

    @Autowired
    public GenreService(GenreRepository genreRepository, GenreMapper mapper) {
        this.genreRepository = genreRepository;
        this.mapper = mapper;
    }

    public List<Genre> findAll(){
        return this.genreRepository.findByStateTrue().stream().map(this.mapper::toDomain).toList();
    }

    public Genre save(Genre genre){
        GenreEntity genreEntity = this.mapper.toEntity(genre);
        genreEntity.setState(Constants.ACTIVE_STATE);

        return this.mapper.toDomain(this.genreRepository.save(genreEntity));
    }

    public void delete(long id){
        this.genreRepository.deleteById(id);
    }

    public boolean exists(long id){
        return this.genreRepository.countByIdGenreAndStateTrue(id) > 0;
    }
}
