package com.movie.moviedb.domain.service;

import com.cloudinary.utils.ObjectUtils;
import com.movie.moviedb.constant.Constants;
import com.movie.moviedb.domain.Actor;
import com.movie.moviedb.persistence.entity.ActorEntity;
import com.movie.moviedb.persistence.mapper.ActorMapper;
import com.movie.moviedb.persistence.mapper.MapperUtil;
import com.movie.moviedb.persistence.repository.ActorRepository;
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
public class ActorService {
    private final ActorRepository actorRepository;
    private final ActorMapper mapper;
    private final CloudinaryConfiguration cloudinaryConfiguration;

    @Autowired
    public ActorService(ActorRepository actorRepository, ActorMapper mapper, CloudinaryConfiguration cloudinaryConfiguration) {
        this.actorRepository = actorRepository;
        this.mapper = mapper;
        this.cloudinaryConfiguration = cloudinaryConfiguration;
    }

    public Actor save(Actor actor) throws IOException {
        ActorEntity actorEntity = new ActorEntity();
        actorEntity.setIdActor(actor.getId());
        actorEntity.setName(actor.getName());
        actorEntity.setBirthdate(actor.getBirthdate());

        Map uploadResult = cloudinaryConfiguration.cloudinary().uploader().upload(
                "data:image/png;base64,"+actor.getAvatar(),
                ObjectUtils.asMap(
                        "name", "preset_actor",
                        "folder", "actor",
                        "format", "png"
                )
        );

        actorEntity.setAvatar((String) uploadResult.get("secure_url"));
        actorEntity.setState(Constants.ACTIVE_STATE);

        actorEntity = this.actorRepository.save(actorEntity);

        actor.setAvatar(actorEntity.getAvatar());
        actor.setId(actorEntity.getIdActor());

        return actor;
    }

    public Page<Actor> getAll(int page, int elements, String sortBy, String sortDirection){
        Sort sort = Sort.by(Sort.Direction.fromString(sortDirection), sortBy);
        Pageable pageRequest = PageRequest.of(page, elements, sort);
        return MapperUtil.toActorsPage(this.actorRepository.findByStateTrue(pageRequest));
    }

    public Optional<Actor> getById(long id){
        return this.actorRepository.findByIdActorAndStateTrue(id)
                .map(this.mapper::toActor);
    }

    @Transactional
    public void delete(long id){
        this.actorRepository.updateState(Constants.INACTIVE_STATE, id);
    }

    public boolean exists(long id){
        return this.actorRepository.countByIdActorAndStateTrue(id) > 0;
    }
}
