package com.allcritics.api.domain.entity;

import com.allcritics.api.domain.enums.ContentType;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import jakarta.persistence.Entity;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;


import java.time.LocalDate;
import java.util.Set;

@Data // Gera @Getter, @Setter, @ToString, @EqualsAndHashCode e @RequiredArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "content")
@Inheritance(strategy = InheritanceType.JOINED)
@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.EXISTING_PROPERTY, // Usa uma propriedade existente como identificador
        property = "contentType", // O nome da propriedade no JSON que define o tipo
        visible = true // Garante que a propriedade 'contentType' ainda seja incluída no JSON
)
@JsonSubTypes({
        @JsonSubTypes.Type(value = Movie.class, name = "MOVIE"),
        @JsonSubTypes.Type(value = Game.class, name = "GAME"),
        @JsonSubTypes.Type(value = Serie.class, name = "SERIE")
})
public abstract class Content {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_content")
    private Long idContent;


    @Column(name = "title", unique = true, nullable = false)
    private String title;

    @Enumerated(EnumType.STRING)
    @Column(name = "content_type", nullable = false)
    private ContentType contentType;

    @Lob
    private String description;

    @Column(name = "image_url", unique = true, nullable = false)
    private String imageURL;

    @Column(name = "release_date", nullable = false)
    private LocalDate releaseDate;

    @Column(name = "average_rating", precision = 4)
    private Double averageRating;

    @Column(name = "created_at", nullable = false)
    private LocalDate createDate;

    @Column(name = "updated_at")
    private LocalDate updateDate;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "content_category", joinColumns = @JoinColumn(name = "id_content"), inverseJoinColumns = @JoinColumn(name = "id_category"))
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Set<Category> categories;

}
