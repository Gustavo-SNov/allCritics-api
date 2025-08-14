package com.allcritics.api.dto.review;


import jakarta.validation.constraints.NotNull;

public record ReviewCreateDTO(@NotNull(message = "Content ID must be informed") Long idContent,
                              String title,
                              Double rate,
                              String comment) {
}
