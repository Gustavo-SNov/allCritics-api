package com.allcritics.api.dto.category;

import com.allcritics.api.dto.conteudo.ContentTypeFilter;

public record CategoryFilter(ContentTypeFilter contentType, Boolean withContent) {
}
