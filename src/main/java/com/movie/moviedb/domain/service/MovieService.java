package com.movie.moviedb.domain.service;

import com.cloudinary.utils.ObjectUtils;
import com.movie.moviedb.constant.Constants;
import com.movie.moviedb.domain.Movie;
import com.movie.moviedb.domain.dto.UpdatePosterDto;
import com.movie.moviedb.persistence.entity.MovieEntity;
import com.movie.moviedb.persistence.mapper.MapperUtil;
import com.movie.moviedb.persistence.mapper.MovieMapper;
import com.movie.moviedb.persistence.repository.MovieRepository;
import com.movie.moviedb.web.config.CloudinaryConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.Map;
import java.util.Optional;

@Service
public class MovieService {
    private final MovieRepository movieRepository;
    private final MovieMapper mapper;
    private final CloudinaryConfiguration cloudinaryConfiguration;

    @Autowired
    public MovieService(MovieRepository movieRepository, MovieMapper mapper, CloudinaryConfiguration cloudinaryConfiguration) {
        this.movieRepository = movieRepository;
        this.mapper = mapper;
        this.cloudinaryConfiguration = cloudinaryConfiguration;
    }

    public Movie save(Movie movie) throws IOException {
        MovieEntity movieEntity = this.mapper.toMovieEntity(movie);

        Map uploadResult = this.cloudinaryConfiguration.cloudinary().uploader().upload(
                "data:image/png;base64,"+movie.getPoster(),
                ObjectUtils.asMap(
                        "name", "preset_movie",
                        "folder", "movie",
                        "format", "png"
                )
        );

        movieEntity.setPoster((String) uploadResult.get("secure_url"));

        movieEntity.getActors().forEach(actor-> actor.setMovie(movieEntity));
        movieEntity.setState(Constants.ACTIVE_STATE);

        return this.mapper.toMovie(this.movieRepository.save(movieEntity));
    }

    @Transactional
    public Movie updatePoster(UpdatePosterDto data) throws IOException {
        MovieEntity movieEntity = this.movieRepository.findById(data.getId()).orElse(null);

        Map uploadResult = this.cloudinaryConfiguration.cloudinary().uploader().upload(
                "data:image/png;base64,"+data.getPoster(),
                ObjectUtils.asMap(
                        "name", "preset_movie",
                        "folder", "movie",
                        "format", "png"
                )
        );

        String urlImage = (String) uploadResult.get("secure_url");

        this.movieRepository.updatePoster(urlImage, data.getId());
        movieEntity.setPoster(urlImage);

        return this.mapper.toMovie(movieEntity);
    }

    public Page<Movie> getAll(int page, int elements, String sortBy, String sortDirection){
        Sort sort = Sort.by(Sort.Direction.fromString(sortDirection), sortBy);
        Pageable pageRequest = PageRequest.of(page, elements, sort);
        return MapperUtil.toMoviesPage(this.movieRepository.findByStateTrue(pageRequest));
    }

    public Optional<Movie> getbyId(long id){
        return this.movieRepository.findByIdMovieAndStateTrue(id).
                map(mapper::toMovie);
    }

    @Transactional
    public void delete(long id){
        this.movieRepository.updateState(Constants.INACTIVE_STATE, id);
    }

    public boolean exists(long id){
        return this.movieRepository.countByIdMovieAndStateTrue(id) > 0;
    }
}
