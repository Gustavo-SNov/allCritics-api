package com.allcritics.api.dto.conteudo;


import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.Getter;


@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ContentFilter {

    private ContentFilterType contentFilter;

}
