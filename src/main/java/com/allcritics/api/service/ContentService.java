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

//    public List<ContentDTO> getFilteredContents(ContentFilter filter, List<Content> contents) {
//        ContentFilterType contentFilter = Optional.ofNullable(filter.getContentFilter()).orElse(ContentFilterType.ALL);
//        switch (contentFilter){
//            case ALL:
//                return contents.stream().map(contentMapper::toContentDTO).collect(Collectors.toList());
//            case SERIE:
//                return contents.stream().filter(content -> content.getContentType().equals(ContentType.SERIE)).map(contentMapper::toContentDTO).collect(Collectors.toList());
//            case GAME:
//                return contents.stream().filter(content -> content.getContentType().equals(ContentType.GAME)).map(contentMapper::toContentDTO).collect(Collectors.toList());
//            case MOVIE:
//                return contents.stream().filter(content -> content.getContentType().equals(ContentType.MOVIE)).map(contentMapper::toContentDTO).collect(Collectors.toList());
//            case BOOK:
//                return contents.stream().filter(content -> content.getContentType().equals(ContentType.BOOK)).map(contentMapper::toContentDTO).collect(Collectors.toList());
//            case SPLICE:
//                List<ContentDTO> splicedContent = new ArrayList<>();
//                splicedContent.add(contentMapper.toSplicdedContentDTO(contents));
//                return splicedContent;
//
//            default:
//                return List.of();
//        }
//    }


}
