package com.allcritics.api.domain.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
public enum ContentType {
    MOVIE("MOVIE"),SERIE("SERIE"),GAME("GAME"), BOOK("BOOK");

    private final String type;

    ContentType(String type) {
        this.type = type;
    }
}
