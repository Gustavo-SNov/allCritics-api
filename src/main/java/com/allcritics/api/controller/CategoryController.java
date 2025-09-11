package com.allcritics.api.controller;

import com.allcritics.api.dto.category.CategoryDTO;
import com.allcritics.api.dto.category.CategoryFilter;
import com.allcritics.api.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping("/category")
public class CategoryController {

    private final CategoryService categoryService;

    @Autowired
    private CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping
    public ResponseEntity<List<CategoryDTO>> getContents(CategoryFilter categoryFilter) {
        List<CategoryDTO> categories = categoryService.getAllCategories(categoryFilter);
        return ResponseEntity.ok().body(categories);
    }

}
