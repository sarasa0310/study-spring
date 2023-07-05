package com.jimmy.studyspring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class StudySpringApplication {

    public static void main(String[] args) throws InterruptedException {
        SpringApplication.run(StudySpringApplication.class, args).close();
    }

}
