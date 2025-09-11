package com.allcritics.api.service;

import com.allcritics.api.domain.entity.Category;
import com.allcritics.api.domain.entity.Content;
import com.allcritics.api.dto.category.CategoryDTO;
import com.allcritics.api.dto.category.CategoryFilter;
import com.allcritics.api.pattern.mapper.CategoryMapper;
import com.allcritics.api.repository.CategoryRepository;
import com.allcritics.api.repository.ContentRepository;
import com.allcritics.api.repository.specification.CategorySpecification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;
    private final ContentRepository contentRepository;
    private final CategoryMapper categoryMapper;
    private final CategorySpecification categorySpecification;

    @Autowired
    public CategoryService(CategoryRepository categoryRepository, ContentRepository contentRepository, CategoryMapper categoryMapper, CategorySpecification categorySpecification) {
        this.categoryRepository = categoryRepository;
        this.contentRepository = contentRepository;
        this.categoryMapper = categoryMapper;
        this.categorySpecification = categorySpecification;
    }

    public List<CategoryDTO> getAllCategories(CategoryFilter categoryFilter) {
//        List<Category> categories = categoryRepository.findAll();
//        List<Content> contents = contentRepository.findAll();
//        Set<Category> categoriesWithContents = contents.stream().flatMap(content -> content.getCategories().stream()).collect(Collectors.toSet());
//
//        List<Category> filteredCategories = categories.stream().filter(categoriesWithContents::contains).sorted(Comparator.comparing(Category::getIdCategory)).toList();
        Specification<Category> specification = categorySpecification.getFilterToCategory(categoryFilter);
        List<Category> categories = categoryRepository.findAll(specification);
        return categories.stream().map(categoryMapper::toCategoryDTO).collect(Collectors.toList());
    }

}
