package com.projeto_web.AllCritics.pattern.builder;

import com.projeto_web.AllCritics.dominio.Review;
import com.projeto_web.AllCritics.dto.ReviewDTO;

import java.time.LocalDate;
import java.util.List;

public interface ConteudoBuilder<T extends ConteudoBuilder<T>> {
    T setIdConteudo(Long idConteudo);
    T setTitulo(String titulo);
    T setDescricao(String descricao);
    T setTipoConteudo(String tipoConteudo);
    T setUrlImagem(String urlImagem);
    T setDataLancamento(LocalDate dataLancamento);
    T setClassificacaoEtaria(String classificacaoEtaria);
    T setNota(Double nota);
    T setDataCriacao(LocalDate dataCriacao);
    T setDataModificacao(LocalDate dataModificacao);
    T setReviews(List<Review> reviews);

}
