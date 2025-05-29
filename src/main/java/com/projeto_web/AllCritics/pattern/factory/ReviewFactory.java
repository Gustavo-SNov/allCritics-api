package com.projeto_web.AllCritics.pattern.factory;

import com.projeto_web.AllCritics.dominio.Review;
import com.projeto_web.AllCritics.dto.ReviewDTO;
import com.projeto_web.AllCritics.pattern.builder.ReviewBuilder;
import com.projeto_web.AllCritics.pattern.builder.ReviewDTOBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.Optional;

@Component
public class ReviewFactory {

    @Lazy
    private final ConteudoFactory conteudoFactory;
    @Lazy
    private final UsuarioFactory usuarioFactory;

    @Autowired
    public ReviewFactory(ConteudoFactory conteudoFactory, UsuarioFactory usuarioFactory) {
        this.conteudoFactory = conteudoFactory;
        this.usuarioFactory = usuarioFactory;
    }

    public Review transformaEmReview(ReviewDTO reviewDTO) {
        return ReviewBuilder.getInstance()
                .setIdReview(reviewDTO.getIdReview())
                .setIdUsuario(reviewDTO.getIdUsuario())
                .setIdConteudo(reviewDTO.getIdConteudo())
                .setNota(reviewDTO.getNota())
                .setComentario(reviewDTO.getComentario())
                .setDataCriacao(Optional.ofNullable(reviewDTO.getDataCriacao()).orElse(LocalDate.now()))
                .setDataModificacao(reviewDTO.getDataModificacao())
                .build();
    }

    public ReviewDTO transformaEmReviewDTO(Review review) {
        return ReviewDTOBuilder.getInstance()
                .setIdReview(review.getIdReview())
                .setIdUsuario(review.getIdUsuario())
                .setIdConteudo(review.getIdConteudo())
                .setNota(review.getNota())
                .setComentario(review.getComentario())
                .setDataCriacao(review.getDataCriacao())
                .setDataModificacao(review.getDataModificacao())
                .setConteudo(conteudoFactory.transformaEmConteudoDTO(review.getConteudo()))
                .setUsuario(usuarioFactory.transformaEmUsuarioDTO(review.getUsuario()))
                .build();
    }
}
