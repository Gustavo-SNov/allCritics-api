package com.allcritics.api.pattern.mapper;

import com.allcritics.api.domain.entity.Like;
import com.allcritics.api.domain.entity.Review;
import com.allcritics.api.domain.entity.User;
import com.allcritics.api.dto.like.LikeDTO;
import com.allcritics.api.dto.like.LikeRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class LikeMapper {

    public Like toLike(LikeRequest likeRequest, User user, Review review) {
        if (likeRequest == null || user == null || review == null) {
            return null;
        }

        return Like.builder()
                .user(user)
                .review(review)
                .build();
    }

    public LikeDTO toLikeDTO(Like like) {
        if (like == null) {
            return null;
        }

        return LikeDTO.builder()
                .idLike(like.getIdLike())
                .idUser(like.getUser().getIdUser())
                .idReview(like.getReview().getIdReview())
                .build();
    }
}
