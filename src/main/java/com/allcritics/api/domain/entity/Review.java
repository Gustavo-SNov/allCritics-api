package com.allcritics.api.domain.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "REVIEW")
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_review")
    private Long idReview;

    @ManyToOne(fetch = FetchType.LAZY) // Define um relacionamento Muitos-para-Um (muitos reviews para um usuário)
    @JoinColumn(name = "id_user", nullable = false) // Especifica a coluna da chave estrangeira
    private User user;

    @ManyToOne(fetch = FetchType.LAZY) // Define um relacionamento Muitos-para-Um (muitos reviews para uma mídia)
    @JoinColumn(name = "id_content", nullable = false)
    private Content content;

    @Column(name = "title", nullable = false)
    private String title;

    @Lob
    @Column(name = "comment")
    private String comment;

    @Column(name = "rate")
    private Double rate;

    @CreationTimestamp
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;



}
