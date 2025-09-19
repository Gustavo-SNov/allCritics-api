package com.allcritics.api.pattern.mapper;

import com.allcritics.api.domain.entity.Content;
import com.allcritics.api.domain.entity.Review;
import com.allcritics.api.domain.entity.User;
import com.allcritics.api.domain.enums.UserRole;
import com.allcritics.api.dto.conteudo.ContentDTO;
import com.allcritics.api.dto.review.ReviewDTO;
import com.allcritics.api.dto.user.UserDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class UserMapper {
    @Lazy
    private final ContentMapper contentMapper;

    public UserDTO toUserDTO(User user) {
        if (user == null) {
            return null;
        }

        UserDTO.UserDTOBuilder userDTOBuilder = UserDTO.builder()
                .idUser(user.getIdUser())
                .username(user.getUsername())
                .email(user.getEmail())
                .role(Optional.ofNullable(user.getRole()).orElse(UserRole.DEFAULT))
                .accountName(user.getAccountName())
                .biography(user.getBiography())
                .profileImgUrl(user.getProfileImgUrl())
                .coverImgUrl(user.getCoverImgUrl())
                .createDate(user.getCreateDate())
                .updateDate(user.getUpdateDate())
                .reviews(user.getReviews().stream().map(this::toReviewDTO).collect(Collectors.toList()));
                ;
        return userDTOBuilder.build();
    }

    public ReviewDTO toReviewDTO(Review review) {
        if (review == null) {
            return null;
        }
        return ReviewDTO.builder()
                .idReview(review.getIdReview())
                .title(review.getTitle())
                .comment(review.getComment())
                .rate(review.getRate())
                .createdAt(review.getCreatedAt())
                .updatedAt(review.getUpdatedAt())
                .content(contentMapper.toContentDTO(review.getContent()))
                .build();
    }


}
