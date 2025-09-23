package com.allcritics.api.service;

import com.allcritics.api.domain.entity.Content;
import com.allcritics.api.domain.entity.Review;
import com.allcritics.api.domain.entity.User;
import com.allcritics.api.dto.consumptionHistory.ConsumptionHistoryCreateDTO;
import com.allcritics.api.dto.review.ReviewCreateDTO;
import com.allcritics.api.dto.review.ReviewDTO;
import com.allcritics.api.dto.review.ReviewFilter;
import com.allcritics.api.event.ReviewCreatedEvent;
import com.allcritics.api.exception.BadRequestException;
import com.allcritics.api.exception.ResourceNotFoundException;
import com.allcritics.api.pattern.mapper.ReviewMapper;
import com.allcritics.api.repository.ContentRepository;
import com.allcritics.api.repository.ReviewRepository;
import com.allcritics.api.repository.UserRepository;
import com.allcritics.api.repository.specification.ReviewSpecification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ReviewService {

    private final ReviewRepository reviewRepository;
    private final UserRepository userRepository;
    private final ContentRepository contentRepository;
    private final ReviewMapper reviewMapper;
    private final ApplicationEventPublisher eventPublisher;
    private final ReviewSpecification reviewSpecification;
    private final AuthService authService;
    private final ConsumptionHistoryService consumptionHistoryService;

    @Autowired
    public ReviewService(
            ReviewRepository reviewRepository,
            @Lazy UserRepository userRepository,
            @Lazy ContentRepository contentRepository,
            ReviewMapper reviewMapper,
            ApplicationEventPublisher eventPublisher,
            ReviewSpecification reviewSpecification,
            @Lazy AuthService authService,
            @Lazy ConsumptionHistoryService consumptionHistoryService
    ) {
        this.reviewRepository = reviewRepository;
        this.userRepository = userRepository;
        this.contentRepository = contentRepository;
        this.reviewMapper = reviewMapper;
        this.eventPublisher = eventPublisher;
        this.reviewSpecification = reviewSpecification;
        this.authService = authService;
        this.consumptionHistoryService = consumptionHistoryService;
    }

    public ReviewDTO getReview(Long idReview) {
        Review review = reviewRepository.findById(idReview).orElseThrow(() -> new ResourceNotFoundException("Review not found."));

        return reviewMapper.toReviewDTO(review);
    }

    public Page<ReviewDTO> getAllReviews(ReviewFilter filter, Pageable pageable) {
        Specification<Review> spec =  reviewSpecification.getFilterToReviews(filter);
        Page<Review> reviewsPage = reviewRepository.findAll(spec, pageable);

        return reviewsPage.map(reviewMapper::toReviewDTO);
    }

    @Transactional
    public ReviewDTO createReview(ReviewCreateDTO reviewCreate, String username) {

        // 1. Validar Usuário
        User user = userRepository.findByUsername(username).orElse(null);
        if (user == null) {
            throw new ResourceNotFoundException("User not found");
        }
        // 2. Validar Conteúdo
        Content content = contentRepository.findById(reviewCreate.idContent()).orElse(null);
        if (content == null) {
            throw new ResourceNotFoundException("Content not found");
        }

        Review review = reviewMapper.toCreateReview(reviewCreate, user, content);
        reviewRepository.save(review);

        ConsumptionHistoryCreateDTO historyDto = new ConsumptionHistoryCreateDTO(
                review.getContent().getIdContent(),
                review.getUser().getIdUser()
        );

        consumptionHistoryService.upsertHistory(historyDto);

        // Atualiza a nota do conteúdo
        eventPublisher.publishEvent(new ReviewCreatedEvent(this, review.getContent().getIdContent()));

        return reviewMapper.toReviewDTO(review);
    }

    @Transactional
    public ReviewDTO updateReview(Long idReview, ReviewDTO reviewDTO, String username) {
        Review review = reviewRepository.findById(idReview).orElseThrow(() -> new ResourceNotFoundException("Review not found."));

        // 1. Validar Usuário
        User user = authService.validatePermission(reviewDTO.getUser().getIdUser(), username);

        // 2. Validar Conteúdo
        Content content = contentRepository.findById(reviewDTO.getContent().getIdContent()).orElseThrow(() -> new ResourceNotFoundException("Content not found"));
        if (!reviewDTO.getContent().getIdContent().equals(content.getIdContent())) {
            throw new BadRequestException("Content can't be changed in review");
        }

        // 3. Alterar review
        if (reviewDTO.getTitle() != null && !reviewDTO.getTitle().isEmpty()) {
            review.setTitle(reviewDTO.getTitle());
        }

        if (reviewDTO.getComment() != null && !reviewDTO.getComment().isEmpty()) {
            review.setComment(reviewDTO.getComment());
        }

        if (reviewDTO.getRate() != null) {
            review.setRate(reviewDTO.getRate());
        }

        Review reviewUpdated = reviewRepository.save(review);

        return reviewMapper.toReviewDTO(reviewUpdated);
    }

    @Transactional
    public void deleteReviewById(Long reviewId) {
        Review review = reviewRepository.findById(reviewId).orElseThrow(() -> new ResourceNotFoundException("Review not found to delete."));
        reviewRepository.delete(review);

        // Atualiza a nota do conteúdo
        eventPublisher.publishEvent(new ReviewCreatedEvent(this, review.getContent().getIdContent()));
    }

}
