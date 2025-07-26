package com.allcritics.api.dto.conteudo;

import com.allcritics.api.domain.enums.ContentType;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ContentDTO {

    // Campos comuns de Content
    private Long idContent;
    private String title;
    private ContentType contentType;
    private String description;
    private String imageURL;
    private LocalDate releaseDate;
    private Double averageRating;

    // Campos específicos de Movie
    private String director;
    private Integer duration;

    // Campos específicos de Game
    private String studio;
    private String platform;
    private Boolean multiplayer;

    // Campos específicos de Serie
    private Long numberOfSeasons;
    private Long episodesPerSeason;
    private String broadcaster;

}
