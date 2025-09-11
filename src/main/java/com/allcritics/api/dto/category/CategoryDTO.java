package com.allcritics.api.dto.category;

import com.allcritics.api.dto.conteudo.ContentDTO;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CategoryDTO {

    private Long idCategory;

    private String name;

    private List<ContentDTO> contents;
}
