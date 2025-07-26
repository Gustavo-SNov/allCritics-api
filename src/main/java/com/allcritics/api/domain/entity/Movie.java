package com.allcritics.api.domain.entity;

import jakarta.persistence.*;
import lombok.*;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "movie")
@PrimaryKeyJoinColumn(name = "idContent")
public class Movie extends Content {


    @Column(name = "director", nullable = false)
    private String director;

    @Column(name = "duration", nullable = false)
    private Integer duration;

    @Override
    public String toString() {
        return "Movie{" +
                "director='" + director + '\'' +
                ", duration=" + duration +
                '}';
    }
}
