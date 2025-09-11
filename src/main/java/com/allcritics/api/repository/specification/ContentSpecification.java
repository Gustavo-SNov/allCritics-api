package com.allcritics.api.repository.specification;

import com.allcritics.api.domain.entity.Category;
import com.allcritics.api.domain.entity.Content;
import com.allcritics.api.dto.conteudo.ContentFilter;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ContentSpecification {
    public Specification<Content> getFilterToContent(ContentFilter filter) {
        return (root, query, criteriaBuilder) -> {

            List<Predicate> predicates = new ArrayList<Predicate>();

            if (filter.getContentType() != null) {
                predicates.add(criteriaBuilder.equal(root.get("contentType"), filter.getContentType()));
            }

            if (filter.getCategory() != null && !filter.getCategory().isBlank()) {
                // Cria um JOIN entre Content e Category para poder filtrar pelo nome da categoria
                Join<Content, Category> categoryJoin = root.join("categories");
                predicates.add(criteriaBuilder.equal(categoryJoin.get("name"), filter.getCategory()));
            }

            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };
    }
}
