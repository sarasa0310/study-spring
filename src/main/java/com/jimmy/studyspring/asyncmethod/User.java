package com.jimmy.studyspring.asyncmethod;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record User(
        String type,
        String name,
        String blog
) {
}
