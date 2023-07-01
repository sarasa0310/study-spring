package com.jimmy.studyspring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

@SpringBootApplication
@ConfigurationPropertiesScan
public class StudySpringApplication {

    public static void main(String[] args) {
        SpringApplication.run(StudySpringApplication.class, args);
    }

}
