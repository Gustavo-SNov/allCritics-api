package com.allcritics.api.dto.conteudo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
public enum ContentFilterType {
    MOVIE("MOVIE"), SERIE("SERIE"), GAME("GAME"), BOOK("BOOK"), ALL("ALL"), SPLICE("SPLICE");

    private final String type;

    ContentFilterType(String type) {
        this.type = type;
    }

}
