package com.allcritics.api.pattern.listener;

import com.allcritics.api.event.ReviewCreatedEvent;
import com.allcritics.api.repository.ContentRepository;
import com.allcritics.api.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.event.TransactionalEventListener;


@Component
@RequiredArgsConstructor
@Slf4j
public class RatingUpdateListener {

    private final ReviewRepository reviewRepository;
    private final ContentRepository contentRepository;

    @Async
    @TransactionalEventListener
    //@EventListener(ReviewCreatedEvent.class)
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void handleReviewCreation(ReviewCreatedEvent event) {
        log.info("Event: ReviewCreatedEvent is Processing in background to {}", event.getIdContent());

        Long idContent = event.getIdContent();

        Double newAverageRate = reviewRepository.calculateAverageRatingByContentId(idContent);

        contentRepository.findById(idContent).ifPresent(content -> {
            content.setAverageRating(newAverageRate);
            contentRepository.save(content);
           log.info("Event: ReviewCreatedEvent has been processed.\n New average rating is {} to content {} ", newAverageRate, idContent);
        });
    }
}
