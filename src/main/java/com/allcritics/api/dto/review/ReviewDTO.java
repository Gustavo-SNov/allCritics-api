package com.allcritics.api.dto.review;

import com.allcritics.api.dto.conteudo.ContentDTO;
import com.allcritics.api.dto.user.UserDTO;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ReviewDTO {

    private Long idReview;

    private UserDTO user;

    private ContentDTO content;

    private String title;

    private Double rate;

    private String comment;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;
}
