package com.allcritics.api.pattern.mapper;

import com.allcritics.api.domain.entity.Category;
import com.allcritics.api.dto.category.CategoryDTO;
import org.springframework.stereotype.Component;

@Component
public class CategoryMapper {

    public CategoryDTO toCategoryDTO(Category category) {
        if (category == null) {
            return null;
        }

        return CategoryDTO.builder()
                .idCategory(category.getIdCategory())
                .name(category.getName())
                .build();
    }

}
