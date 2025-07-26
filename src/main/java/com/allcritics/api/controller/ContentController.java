package com.allcritics.api.controller;

import com.allcritics.api.domain.entity.Content;
import com.allcritics.api.dto.conteudo.ContentDTO;
import com.allcritics.api.service.ContentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/content")
public class ContentController {

    private final ContentService contentService;

    private ContentController(ContentService contentService) {
        this.contentService = contentService;
    }

    @GetMapping
    public ResponseEntity<List<ContentDTO>> getContents() {
        List<ContentDTO> contents = contentService.getAllContents();
        contents.forEach(System.out::println);
        return ResponseEntity.ok().body(contents);
    }
}
