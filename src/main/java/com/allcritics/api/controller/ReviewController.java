package com.allcritics.api.controller;

import com.allcritics.api.dto.review.ReviewCreateDTO;
import com.allcritics.api.dto.review.ReviewDTO;
import com.allcritics.api.dto.review.ReviewFilter;
import com.allcritics.api.service.ReviewService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/review")
public class ReviewController {

    private final ReviewService reviewService;

    @Autowired
    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    @GetMapping(value = "/{idReview}")
    public ResponseEntity<ReviewDTO> getReviewById(@PathVariable Long idReview) {
        ReviewDTO review = reviewService.getReview(idReview);
        return ResponseEntity.ok().body(review);
    }

    @GetMapping
    public ResponseEntity<Page<ReviewDTO>> getAllReviews(ReviewFilter reviewFilter, Pageable pageable) {
        Page<ReviewDTO> reviews = reviewService.getAllReviews(reviewFilter, pageable);
        return ResponseEntity.ok().body(reviews);
    }

    @PostMapping
    public ResponseEntity<ReviewDTO> createReview(@Valid @RequestBody ReviewCreateDTO reviewCreateDTO, Authentication authentication) {
        String username = authentication.getName();
        ReviewDTO reviewDTO = reviewService.createReview(reviewCreateDTO, username);
        return ResponseEntity.ok().body(reviewDTO);
    }

    @PutMapping(value = "/{idReview}")
    public ResponseEntity<ReviewDTO> updateReview(@PathVariable Long idReview, @Valid @RequestBody ReviewDTO reviewDTO, Authentication authentication ){
        String username = authentication.getName();
        ReviewDTO review = reviewService.updateReview(idReview,reviewDTO, username);
        return ResponseEntity.ok().body(review);
    }

    @DeleteMapping(value = "/{idReview}")
    public ResponseEntity<Void> deleteReview(@PathVariable Long idReview){
        reviewService.deleteReviewById(idReview);
        return ResponseEntity.noContent().build();
    }
}
