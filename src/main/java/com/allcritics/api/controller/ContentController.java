package com.allcritics.api.controller;

import com.allcritics.api.dto.conteudo.ContentDTO;
import com.allcritics.api.dto.conteudo.ContentFilter;
import com.allcritics.api.service.ContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/content")
public class ContentController {

    private final ContentService contentService;

    @Autowired
    private ContentController(ContentService contentService) {
        this.contentService = contentService;
    }

    @GetMapping
    public ResponseEntity<Page<ContentDTO>> getContents(ContentFilter filter, Pageable pageable) {
        System.out.println("Pageable: " + pageable.toString());
        Page<ContentDTO> contents = contentService.getAllContents(filter,pageable);
        return ResponseEntity.ok().body(contents);
    }

    @GetMapping(value = "/{idContent}")
    public ResponseEntity<ContentDTO> getContentById(@PathVariable Long idContent) {
        ContentDTO contentDTO = contentService.getContentById(idContent);
        return ResponseEntity.ok().body(contentDTO);
    }

    @DeleteMapping(value = "/{idContent}")
    public ResponseEntity<Void> deleteContentById(@PathVariable Long idContent) {
        contentService.deleteContentById(idContent);
        return ResponseEntity.noContent().build();
    }

}
