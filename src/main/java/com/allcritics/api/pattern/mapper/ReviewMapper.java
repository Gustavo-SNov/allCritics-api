package com.allcritics.api.pattern.mapper;

import com.allcritics.api.domain.entity.Content;
import com.allcritics.api.domain.entity.Review;
import com.allcritics.api.domain.entity.User;
import com.allcritics.api.dto.review.ReviewCreateDTO;
import com.allcritics.api.dto.review.ReviewDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ReviewMapper {

    @Lazy
    private final ContentMapper contentMapper;

    public Review toCreateReview(ReviewCreateDTO dto, User user, Content content) {
        if (dto == null) {
            return null;
        }

        Review.ReviewBuilder builder = Review.builder()
                .title(dto.title())
                .comment(dto.comment())
                .rate(dto.rate())
                .user(user)
                .content(content);
        return builder.build();
    }

    public ReviewDTO toReviewDTO(Review review) {
        if (review == null) {
            return null;
        }
        ReviewDTO.ReviewDTOBuilder builder = ReviewDTO.builder()
                .idReview(review.getIdReview())
                .title(review.getTitle())
                .comment(review.getComment())
                .rate(review.getRate())
                .createdAt(review.getCreatedAt())
                .content(contentMapper.toContentDTO(review.getContent()));

        return builder.build();
    }


}
