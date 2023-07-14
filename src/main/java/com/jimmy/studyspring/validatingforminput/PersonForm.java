package com.jimmy.studyspring.validatingforminput;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record PersonForm(
        @NotNull @Size(min = 2, max = 30)
        String name,
        @NotNull @Min(18)
        Integer age) {
}
