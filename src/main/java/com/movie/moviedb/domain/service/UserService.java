package com.movie.moviedb.domain.service;

import com.movie.moviedb.constant.Constants;
import com.movie.moviedb.persistence.entity.UserEntity;
import com.movie.moviedb.persistence.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public boolean register(String username, String hashedPassword){
        UserEntity user = new UserEntity();
        user.setUsername(username);
        user.setPassword(hashedPassword);
        user.setLocked(false);
        user.setDisabled(false);

        user = this.userRepository.save(user);
        return !user.getIdUser().equals(Constants.ID_NOT_EXISTS);
    }

    public boolean exists(String username){
        return this.userRepository.countByUsername(username) > 0;
    }


}
