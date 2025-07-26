package com.allcritics.api.pattern.mapper;

import com.allcritics.api.domain.entity.Content;
import com.allcritics.api.domain.entity.Game;
import com.allcritics.api.domain.entity.Movie;
import com.allcritics.api.domain.entity.Serie;
import com.allcritics.api.dto.conteudo.ContentDTO;
import org.springframework.stereotype.Component;

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


        return dtoBuilder.build();
    }
}
