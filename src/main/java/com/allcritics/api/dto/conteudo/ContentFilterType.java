package com.allcritics.api.dto.conteudo;

import lombok.Getter;


@Getter
public enum ContentFilterType {
    MOVIE("MOVIE"), SERIE("SERIE"), GAME("GAME"), BOOK("BOOK");

    private final String type;

    ContentFilterType(String type) {
        this.type = type;
    }

}
