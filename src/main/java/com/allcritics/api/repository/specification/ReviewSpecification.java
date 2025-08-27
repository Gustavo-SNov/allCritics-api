package com.allcritics.api.repository.specification;

import com.allcritics.api.domain.entity.Review;
import com.allcritics.api.dto.review.ReviewFilter;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;


@Component
public class ReviewSpecification {

    public Specification<Review> getFilterToReviews(ReviewFilter filter) {
        return (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<Predicate>();

            if (filter.idContent() != null) {
                predicates.add(criteriaBuilder.equal(root.get("content").get("idContent"), filter.idContent()));
            }

            if (filter.idUser() != null) {
                predicates.add(criteriaBuilder.equal(root.get("user").get("idUser"), filter.idUser()));
            }

            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };
    }
}
