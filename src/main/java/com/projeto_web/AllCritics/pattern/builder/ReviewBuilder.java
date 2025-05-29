package com.projeto_web.AllCritics.pattern.builder;

import com.projeto_web.AllCritics.dominio.Review;

import java.time.LocalDate;


public class ReviewBuilder {

    private final Review review;

    private ReviewBuilder() {
        review = new Review();
    }

    public static ReviewBuilder getInstance() {
        return new ReviewBuilder();
    }

    public ReviewBuilder setIdReview(Long idReview) {
        this.review.setIdReview(idReview);
        return this;
    }

    public ReviewBuilder setIdUsuario(Long idUsuario) {
        this.review.setIdUsuario(idUsuario);
        return this;
    }

    public ReviewBuilder setIdConteudo(Long idConteudo) {
        this.review.setIdConteudo(idConteudo);
        return this;
    }

    public ReviewBuilder setNota(Double nota) {
        this.review.setNota(nota);
        return this;
    }

    public ReviewBuilder setComentario(String comentario) {
        this.review.setComentario(comentario);
        return this;
    }

    public ReviewBuilder setDataCriacao(LocalDate dataCriacao) {
        this.review.setDataCriacao(dataCriacao);
        return this;
    }

    public ReviewBuilder setDataModificacao(LocalDate dataModificacao) {
        this.review.setDataModificacao(dataModificacao);
        return this;
    }

    public Review build() {
        return this.review;
    }
}
