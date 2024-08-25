package com.movie.moviedb.web.config;

import com.cloudinary.Cloudinary;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CloudinaryConfiguration {
    @Value("${cloudinary.url:empty}")
    private String cloudinaryUrl;

    @Bean
    public Cloudinary cloudinary(){
        return new Cloudinary(this.cloudinaryUrl);
    }
}
