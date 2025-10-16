package com.allcritics.api.service;

import com.allcritics.api.domain.entity.Like;
import com.allcritics.api.domain.entity.Review;
import com.allcritics.api.domain.entity.User;
import com.allcritics.api.dto.like.LikeDTO;
import com.allcritics.api.dto.like.LikeRequest;
import com.allcritics.api.exception.BadRequestException;
import com.allcritics.api.exception.ResourceNotFoundException;
import com.allcritics.api.pattern.mapper.LikeMapper;
import com.allcritics.api.repository.LikeRepository;
import com.allcritics.api.repository.ReviewRepository;
import com.allcritics.api.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
public class LikeService {

    private final LikeRepository likeRepository;
    private final LikeMapper likeMapper;
    @Lazy
    private final UserRepository userRepository;

    @Lazy
    private final ReviewRepository reviewRepository;

    @Transactional
    public LikeDTO createLike(LikeRequest request, Authentication authentication) {
        String username = authentication.getName();
        User user = userRepository.findByUsername(username).orElseThrow(() -> new ResourceNotFoundException("User not found"));
        Review review = reviewRepository.findById(request.idReview()).orElseThrow(() -> new ResourceNotFoundException("Review not found."));

        if (!request.idUser().equals(user.getIdUser())) {
            throw new BadRequestException("Operation not allowed: You do not have permission to like this review");
        }

        Like like = likeRepository.save(likeMapper.toLike(request,user,review));

        return likeMapper.toLikeDTO(like);
    }

    @Transactional
    public void deleteLike(Long likeId, Authentication authentication) {
        String username = authentication.getName();
        User user = userRepository.findByUsername(username).orElseThrow(() -> new ResourceNotFoundException("User not found"));
        Like like = likeRepository.findById(likeId).orElseThrow(() -> new ResourceNotFoundException("Like not found: User do not liked this review"));

        if (!like.getUser().getIdUser().equals(user.getIdUser())) {
            throw new BadRequestException("Operation not allowed: You do not have permission to unlike this review");
        }
        likeRepository.delete(like);
    }
}
