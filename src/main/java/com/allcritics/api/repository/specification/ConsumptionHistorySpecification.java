package com.allcritics.api.repository.specification;

import com.allcritics.api.domain.entity.ConsumptionHistory;
import com.allcritics.api.domain.entity.Review;
import com.allcritics.api.dto.consumptionHistory.ConsumptionHistoryFilter;
import jakarta.persistence.criteria.*;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Component
public class ConsumptionHistorySpecification {

    public Specification<ConsumptionHistory> getFilterToConsumptionHistory(ConsumptionHistoryFilter filter) {
        return (root, query, criteriaBuilder) -> {
            query.distinct(true);

            List<Predicate> predicates = new ArrayList<>();

            if (filter == null) {
                return criteriaBuilder.conjunction();
            }

            LocalDateTime startDate = filter.startDate();
            LocalDateTime endDate = filter.endDate();
            Long idContent = filter.idContent();
            String idUser = filter.idUser();

            Expression<LocalDateTime> consumptionDateExp = root.<LocalDateTime>get("consumptionDate");

            Subquery<LocalDateTime> maxReviewDateSub = query.subquery(LocalDateTime.class);
            Root<Review> reviewSubRoot = maxReviewDateSub.from(Review.class);
            maxReviewDateSub.select(criteriaBuilder.greatest(reviewSubRoot.<LocalDateTime>get("updatedAt")));
            maxReviewDateSub.where(
                    criteriaBuilder.equal(reviewSubRoot.get("content"), root.get("content")),
                    criteriaBuilder.equal(reviewSubRoot.get("user"), root.get("user"))
            );

            Subquery<Long> reviewCountSub = query.subquery(Long.class);
            Root<Review> reviewCountRoot = reviewCountSub.from(Review.class);
            reviewCountSub.select(criteriaBuilder.count(reviewCountRoot));
            reviewCountSub.where(
                    criteriaBuilder.equal(reviewCountRoot.get("content"), root.get("content")),
                    criteriaBuilder.equal(reviewCountRoot.get("user"), root.get("user"))
            );

            Predicate reviewExists = criteriaBuilder.gt(reviewCountSub, 0L);

            if (startDate != null) {
                Predicate pNoReviewAndConsAfterStart =
                        criteriaBuilder.and(
                                criteriaBuilder.not(reviewExists),
                                criteriaBuilder.greaterThanOrEqualTo(consumptionDateExp, startDate)
                        );

                Predicate pReviewAndReviewIsLaterAndReviewAfterStart =
                        criteriaBuilder.and(
                                reviewExists,
                                criteriaBuilder.greaterThanOrEqualTo(maxReviewDateSub, consumptionDateExp),
                                criteriaBuilder.greaterThanOrEqualTo(maxReviewDateSub, startDate)
                        );

                Predicate pReviewAndConsIsLaterAndConsAfterStart =
                        criteriaBuilder.and(
                                reviewExists,
                                criteriaBuilder.lessThan(maxReviewDateSub, consumptionDateExp),
                                criteriaBuilder.greaterThanOrEqualTo(consumptionDateExp, startDate)
                        );

                predicates.add(criteriaBuilder.or(
                        pNoReviewAndConsAfterStart,
                        pReviewAndReviewIsLaterAndReviewAfterStart,
                        pReviewAndConsIsLaterAndConsAfterStart
                ));
            }

            if (endDate != null) {
                Predicate pNoReviewAndConsBeforeEnd =
                        criteriaBuilder.and(
                                criteriaBuilder.not(reviewExists),
                                criteriaBuilder.lessThanOrEqualTo(consumptionDateExp, endDate)
                        );

                Predicate pReviewAndReviewIsLaterAndReviewBeforeEnd =
                        criteriaBuilder.and(
                                reviewExists,
                                criteriaBuilder.greaterThanOrEqualTo(maxReviewDateSub, consumptionDateExp),
                                criteriaBuilder.lessThanOrEqualTo(maxReviewDateSub, endDate)
                        );

                Predicate pReviewAndConsIsLaterAndConsBeforeEnd =
                        criteriaBuilder.and(
                                reviewExists,
                                criteriaBuilder.lessThan(maxReviewDateSub, consumptionDateExp),
                                criteriaBuilder.lessThanOrEqualTo(consumptionDateExp, endDate)
                        );

                predicates.add(criteriaBuilder.or(
                        pNoReviewAndConsBeforeEnd,
                        pReviewAndReviewIsLaterAndReviewBeforeEnd,
                        pReviewAndConsIsLaterAndConsBeforeEnd
                ));
            }

            if (idContent != null) {
                Join<Object, Object> contentJoin = root.join("content");
                predicates.add(criteriaBuilder.equal(contentJoin.get("idContent"), idContent));
            }

            if (idUser != null && !idUser.isBlank()) {
                Join<Object, Object> userJoin = root.join("user");
                predicates.add(criteriaBuilder.equal(userJoin.get("idUser"), idUser));
            }

            return predicates.isEmpty() ? criteriaBuilder.conjunction() : criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };
    }
}
