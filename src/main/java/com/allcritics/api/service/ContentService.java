package com.allcritics.api.service;

import com.allcritics.api.domain.entity.Content;
import com.allcritics.api.dto.conteudo.ContentDTO;
import com.allcritics.api.dto.conteudo.ContentFilter;
import com.allcritics.api.exception.ResourceNotFoundException;
import com.allcritics.api.pattern.mapper.ContentMapper;
import com.allcritics.api.repository.ContentRepository;
import com.allcritics.api.repository.specification.ContentSpecification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class ContentService {

    private final ContentRepository contentRepository;
    private final ContentMapper contentMapper;
    private final ContentSpecification contentSpecification;

    @Autowired
    private ContentService(ContentRepository contentRepository, ContentMapper contentMapper, ContentSpecification contentSpecification) {
        this.contentRepository = contentRepository;
        this.contentMapper = contentMapper;
        this.contentSpecification = contentSpecification;
    }

    public Page<ContentDTO> getAllContents(ContentFilter filter, Pageable pageable) {
        Specification<Content> specification = contentSpecification.getFilterToContent(filter);
        Page<Content> contents = contentRepository.findAll(specification, pageable);

        return contents.map(contentMapper::toContentDTO);

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
