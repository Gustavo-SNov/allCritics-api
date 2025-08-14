package com.allcritics.api.event;

import lombok.Getter;
import org.springframework.context.ApplicationEvent;

@Getter
public class ReviewCreatedEvent extends ApplicationEvent {

    private final Long idContent;

    public ReviewCreatedEvent(Object source, Long idContent) {
        super(source);
        this.idContent = idContent;
    }

}
