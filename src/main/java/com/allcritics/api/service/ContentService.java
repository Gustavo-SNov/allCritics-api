package com.allcritics.api.service;

import com.allcritics.api.domain.entity.Content;
import com.allcritics.api.domain.enums.ContentType;
import com.allcritics.api.dto.conteudo.ContentDTO;
import com.allcritics.api.dto.conteudo.ContentFilter;
import com.allcritics.api.dto.conteudo.ContentFilterType;
import com.allcritics.api.exception.AllCriticsExceptionHandler;
import com.allcritics.api.exception.ResourceNotFoundException;
import com.allcritics.api.pattern.mapper.ContentMapper;
import com.allcritics.api.repository.ContentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
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

    public List<ContentDTO> getAllContents(ContentFilter filter) {
        List<Content> contents = contentRepository.findAll();
        if (contents.isEmpty()) {
            System.out.println("No contents found");
            return List.of();
        }
        return getFilteredContents(filter, contents);
    }

    public ContentDTO getContentById(Long idContent) {
        Content content = contentRepository.findById(idContent).orElseThrow(() -> new ResourceNotFoundException("Content not found."));
        return contentMapper.toContentDTO(content);
    }

    public void deleteContentById(Long idContent) {
        Content content = contentRepository.findById(idContent).orElseThrow(() -> new ResourceNotFoundException("Content not found."));
        contentRepository.delete(content);
    }

    public List<ContentDTO> getFilteredContents(ContentFilter filter, List<Content> contents) {
        ContentFilterType contentFilter = Optional.ofNullable(filter.getContentFilter()).orElse(ContentFilterType.ALL);
        switch (contentFilter){
            case ALL:
                return contents.stream().map(contentMapper::toContentDTO).collect(Collectors.toList());
            case SERIE:
                return contents.stream().filter(content -> content.getContentType().equals(ContentType.SERIE)).map(contentMapper::toContentDTO).collect(Collectors.toList());
            case GAME:
                return contents.stream().filter(content -> content.getContentType().equals(ContentType.GAME)).map(contentMapper::toContentDTO).collect(Collectors.toList());
            case MOVIE:
                return contents.stream().filter(content -> content.getContentType().equals(ContentType.MOVIE)).map(contentMapper::toContentDTO).collect(Collectors.toList());
            case BOOK:
                return contents.stream().filter(content -> content.getContentType().equals(ContentType.BOOK)).map(contentMapper::toContentDTO).collect(Collectors.toList());
            case SPLICE:
                List<ContentDTO> splicdeContent = new ArrayList<>();
                splicdeContent.add(contentMapper.toSplicdedContentDTO(contents));
                return splicdeContent;

            default:
                return List.of();
        }
    }


}
