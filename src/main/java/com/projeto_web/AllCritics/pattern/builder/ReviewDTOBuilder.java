package com.projeto_web.AllCritics.pattern.builder;



import com.projeto_web.AllCritics.dto.ConteudoDTO;
import com.projeto_web.AllCritics.dto.ReviewDTO;
import com.projeto_web.AllCritics.dto.UsuarioDTO;

import java.time.LocalDate;

public class ReviewDTOBuilder {

    private final ReviewDTO review;

    private ReviewDTOBuilder() {
        review = new ReviewDTO();
    }

    public static ReviewDTOBuilder getInstance() {
        return new ReviewDTOBuilder();
    }

    public ReviewDTOBuilder setIdReview(Long idReview) {
        this.review.setIdReview(idReview);
        return this;
    }

    public ReviewDTOBuilder setIdUsuario(Long idUsuario) {
        this.review.setIdUsuario(idUsuario);
        return this;
    }

    public ReviewDTOBuilder setIdConteudo(Long idConteudo) {
        this.review.setIdConteudo(idConteudo);
        return this;
    }

    public ReviewDTOBuilder setNota(Double nota) {
        this.review.setNota(nota);
        return this;
    }

    public ReviewDTOBuilder setComentario(String comentario) {
        this.review.setComentario(comentario);
        return this;
    }

    public ReviewDTOBuilder setDataCriacao(LocalDate dataCriacao) {
        this.review.setDataCriacao(dataCriacao);
        return this;
    }

    public ReviewDTOBuilder setDataModificacao(LocalDate dataModificacao) {
        this.review.setDataModificacao(dataModificacao);
        return this;
    }

    public ReviewDTOBuilder setConteudo(ConteudoDTO conteudo) {
        this.review.setConteudoDTO(conteudo);
        return this;
    }
    public ReviewDTOBuilder setUsuario(UsuarioDTO usuario) {
        this.review.setUsuario(usuario);
        return this;
    }

    public ReviewDTO build() {
        return this.review;
    }
}
