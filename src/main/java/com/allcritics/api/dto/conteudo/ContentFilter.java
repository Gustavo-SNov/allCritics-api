package com.allcritics.api.dto.conteudo;


import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;


@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ContentFilter {

    private ContentFilterType contentFilter;

}
