package com.allcritics.api.dto.conteudo;

import lombok.Getter;


@Getter
public enum ContentTypeFilter {
    MOVIE("MOVIE"), SERIE("SERIE"), GAME("GAME"), BOOK("BOOK");

    private final String type;

    ContentTypeFilter(String type) {
        this.type = type;
    }

}
