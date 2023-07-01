package com.jimmy.studyspring.uploadingfiles.config;

import com.jimmy.studyspring.uploadingfiles.storage.StorageService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class StorageServiceConfig {

    @Bean
    CommandLineRunner init(StorageService service) {
        return (args) -> {
            service.deleteAll();
            service.init();
        };
    }

}
