package com.allcritics.api.pattern.mapper;

import com.allcritics.api.domain.entity.*;
import com.allcritics.api.domain.enums.ContentType;
import com.allcritics.api.dto.category.CategoryDTO;
import com.allcritics.api.dto.conteudo.ContentDTO;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ContentMapper {

    public ContentDTO toContentDTO(Content content) {
        if (content == null) {
            return null;
        }

        ContentDTO.ContentDTOBuilder dtoBuilder = ContentDTO.builder()
                .idContent(content.getIdContent())
                .title(content.getTitle())
                .contentType(content.getContentType())
                .description(content.getDescription())
                .imageURL(content.getImageURL())
                .releaseDate(content.getReleaseDate())
                .averageRating(content.getAverageRating());

        if (content instanceof Movie) {
            Movie movie = (Movie) content;
            dtoBuilder.director(movie.getDirector())
                    .duration(movie.getDuration());
        } else if (content instanceof Game) {
            Game game = (Game) content;
            dtoBuilder.studio(game.getStudio())
                    .platform(game.getPlatform())
                    .multiplayer(game.getMultiplayer());
        } else if (content instanceof Serie) {
            Serie serie = (Serie) content;
            dtoBuilder.numberOfSeasons(serie.getNumberOfSeasons())
                    .episodesPerSeason(serie.getEpisodesPerSeason())
                    .broadcaster(serie.getBroadcaster());
        }

        if (content.getCategories() != null && !content.getCategories().isEmpty()) {
            List<CategoryDTO> categories = content.getCategories().stream().map(this::toCategoryDTO).toList();
            dtoBuilder.categories(categories);
        }


        return dtoBuilder.build();
    }

    public CategoryDTO toCategoryDTO(Category category) {
        if (category == null) {
            return null;
        }
        CategoryDTO.CategoryDTOBuilder dtoBuilder = CategoryDTO.builder()
                .idCategory(category.getIdCategory())
                .name(category.getName());
        return dtoBuilder.build();
    }


    public ContentDTO toSplicedContentDTO(Page<Content> contents) {


        ContentDTO.ContentDTOBuilder dtoBuilder = ContentDTO.builder()
                .movies(contents.stream().filter(content -> content.getContentType().equals(ContentType.MOVIE)).map(this::toContentDTO).collect(Collectors.toList()))
                .series(contents.stream().filter(content -> content.getContentType().equals(ContentType.SERIE)).map(this::toContentDTO).collect(Collectors.toList()))
                .games(contents.stream().filter(content -> content.getContentType().equals(ContentType.GAME)).map(this::toContentDTO).collect(Collectors.toList()));
        return dtoBuilder.build();
    }
}
