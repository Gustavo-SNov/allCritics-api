package com.allcritics.api.service;

import com.allcritics.api.domain.entity.Content;
import com.allcritics.api.dto.conteudo.ContentDTO;
import com.allcritics.api.exception.ResourceNotFoundException;
import com.allcritics.api.pattern.mapper.ContentMapper;
import com.allcritics.api.repository.ContentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ContentService {

    private final ContentRepository contentRepository;
    private final ContentMapper contentMapper;

    @Autowired
    private ContentService(ContentRepository contentRepository, ContentMapper contentMapper) {
        this.contentRepository = contentRepository;
        this.contentMapper = contentMapper;
    }

    public List<ContentDTO> getAllContents() {
        List<Content> contents = contentRepository.findAll();
        if (contents.isEmpty()) {
            System.out.println("No contents found");
            return List.of();
        }
        return contents.stream().map(contentMapper::toContentDTO).collect(Collectors.toList());
    }

    public ContentDTO getContentById(Long idContent) {
        Content content = contentRepository.findById(idContent).orElseThrow(() -> new ResourceNotFoundException("Content not found."));
        return contentMapper.toContentDTO(content);
    }

    public void deleteContentById(Long idContent) {
        Content content = contentRepository.findById(idContent).orElseThrow(() -> new ResourceNotFoundException("Content not found."));
        contentRepository.delete(content);
    }
}
