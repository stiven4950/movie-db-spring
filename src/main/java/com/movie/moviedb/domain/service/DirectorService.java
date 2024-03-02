package com.movie.moviedb.domain.service;

import com.cloudinary.utils.ObjectUtils;
import com.movie.moviedb.constant.Constants;
import com.movie.moviedb.domain.Director;
import com.movie.moviedb.persistence.entity.DirectorEntity;
import com.movie.moviedb.persistence.mapper.DirectorMapper;
import com.movie.moviedb.persistence.mapper.MapperUtil;
import com.movie.moviedb.persistence.repository.DirectorRepository;
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
public class DirectorService {
    private final DirectorRepository directorRepository;
    private final DirectorMapper mapper;
    private final CloudinaryConfiguration cloudinaryConfiguration;

    @Autowired
    public DirectorService(DirectorRepository directorRepository, DirectorMapper mapper, CloudinaryConfiguration cloudinaryConfiguration) {
        this.directorRepository = directorRepository;
        this.mapper = mapper;
        this.cloudinaryConfiguration = cloudinaryConfiguration;
    }

    public Director save(Director director) throws IOException {
        DirectorEntity directorEntity = this.mapper.toDirectorEntity(director);

        Map uploadResult = cloudinaryConfiguration.cloudinary().uploader().upload(
                "data:image/png;base64,"+director.getAvatar(),
                ObjectUtils.asMap(
                        "name", "preset_director",
                        "folder", "director",
                        "format", "png"
                )
        );

        directorEntity.setAvatar((String) uploadResult.get("secure_url"));
        directorEntity.setState(Constants.ACTIVE_STATE);
        return this.mapper.toDirector(this.directorRepository.save(directorEntity));
    }

    public Page<Director> getAll(int page, int elements, String sortBy, String sortDirection){
        Sort sort = Sort.by(Sort.Direction.fromString(sortDirection), sortBy);
        Pageable pageRequest = PageRequest.of(page, elements, sort);
        return MapperUtil.toDirectorsPage(this.directorRepository.findByStateTrue(pageRequest));
    }

    public Optional<Director> getById(long id){
        return this.directorRepository.findByIdDirectorAndStateTrue(id)
                .map(this.mapper::toDirector);
    }

    @Transactional
    public void delete(long id){
        this.directorRepository.updateState(Constants.INACTIVE_STATE, id);
    }

    public boolean exists(long id){
        long count = this.directorRepository.countByIdDirectorAndStateTrue(id);
        return count > 0;
    }
}
