package com.jimmy.studyspring.restservicecors;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.atomic.AtomicLong;

@RestController
public class GreetingController {

    private static final String template = "Hello, %s!";
    private final AtomicLong counter = new AtomicLong();

    // Controller Method CORS Configuration - using @CrossOrigin
    @GetMapping("/greeting")
    @CrossOrigin(origins = "http://localhost:9000")
    public Greeting greeting(@RequestParam(name = "name", defaultValue = "World") String name) {
        return new Greeting(counter.incrementAndGet(), String.format(template, name));
    }

    // Using Global CORS configuration - using CorsConfig implements WebMvcConfigurer
    @GetMapping("/greeting-global-config")
    public Greeting greetingWithJavaConfig(@RequestParam(name = "name", defaultValue = "World") String name) {
        return new Greeting(counter.incrementAndGet(), String.format(template, name));
    }

}
