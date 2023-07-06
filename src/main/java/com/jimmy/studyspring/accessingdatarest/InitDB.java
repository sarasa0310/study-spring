package com.jimmy.studyspring.accessingdatarest;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class InitDB {

    @Bean
    CommandLineRunner init(PersonRepository repository) {
        return args -> {
            repository.save(new Person("jimmy", "eun"));
            repository.save(new Person("dabin", "kim"));
        };
    }

}
