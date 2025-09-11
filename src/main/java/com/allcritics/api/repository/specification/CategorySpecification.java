package com.allcritics.api.repository.specification;

import com.allcritics.api.domain.entity.Category;
import com.allcritics.api.dto.category.CategoryFilter;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class CategorySpecification {

    public Specification<Category> getFilterToCategory(CategoryFilter categoryFilter) {
        return (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<Predicate>();

            if (categoryFilter.contentType() != null) {
                Join<Category, Object> contentJoin = root.join("contents");
                predicates.add(criteriaBuilder.equal(contentJoin.get("contentType"), categoryFilter.contentType()));
                query.distinct(true);
            }

            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };
    }
}
