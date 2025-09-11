package com.allcritics.api.controller;

import com.allcritics.api.dto.conteudo.ContentDTO;
import com.allcritics.api.dto.conteudo.ContentFilter;
import com.allcritics.api.dto.conteudo.ContentTypeFilter;
import com.allcritics.api.service.ContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/content/game")
public class GameController {
    private final ContentService contentService;

    @Autowired
    private GameController(ContentService contentService) {
        this.contentService = contentService;
    }

    @GetMapping
    public ResponseEntity<Page<ContentDTO>> getGames(Pageable pageable) {
        ContentFilter filter = new ContentFilter();
        filter.setContentType(ContentTypeFilter.GAME);
        Page<ContentDTO> contents = contentService.getAllContents(filter,pageable);
        return ResponseEntity.ok().body(contents);
    }
}
