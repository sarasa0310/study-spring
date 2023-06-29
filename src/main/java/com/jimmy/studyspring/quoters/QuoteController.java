package com.jimmy.studyspring.quoters;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class QuoteController {

    private final static Quote NONE = new Quote("None");
    private final static Random RANDOMIZER = new Random();

    private final QuoteRepository repository;

    @GetMapping
    public List<QuoteResource> getAll() {
        return repository.findAll()
                .stream()
                .map(quote -> new QuoteResource("success", quote))
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public QuoteResource getOne(@PathVariable Long id) {
        return repository.findById(id)
                .map(quote -> new QuoteResource("success", quote))
                .orElse(new QuoteResource("failure", NONE));
    }

    @GetMapping("/random")
    public QuoteResource getRandomOne() {
        return getOne(nextLong(repository.count() + 1));
    }

    private long nextLong(long upperRange) {
        return (long) (RANDOMIZER.nextDouble() * (upperRange - (long) 1)) + (long) 1;
    }

}
