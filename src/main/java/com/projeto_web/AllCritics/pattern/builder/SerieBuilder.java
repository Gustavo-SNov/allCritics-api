package com.projeto_web.AllCritics.pattern.builder;

import com.projeto_web.AllCritics.dominio.Review;
import com.projeto_web.AllCritics.dominio.Serie;


import java.time.LocalDate;
import java.util.List;

public class SerieBuilder implements ConteudoBuilder<SerieBuilder> {

    private final Serie serie;

    private SerieBuilder() {
        this.serie = new Serie();
    }

    public static SerieBuilder getInstance() {
        return new SerieBuilder();
    }
    @Override
    public SerieBuilder setIdConteudo(Long idConteudo) {
        this.serie.setIdConteudo(idConteudo);
        return this;
    }

    @Override
    public SerieBuilder setTitulo(String titulo) {
        this.serie.setTitulo(titulo);
        return this;
    }

    @Override
    public SerieBuilder setDescricao(String descricao) {
        this.serie.setDescricao(descricao);
        return this;
    }

    @Override
    public SerieBuilder setTipoConteudo(String tipoConteudo) {
        this.serie.setTipoConteudo(tipoConteudo);
        return this;
    }

    @Override
    public SerieBuilder setUrlImagem(String urlImagem) {
        this.serie.setUrlImagem(urlImagem);
        return this;
    }

    @Override
    public SerieBuilder setDataLancamento(LocalDate dataLancamento) {
        this.serie.setDataLancamento(dataLancamento);
        return this;
    }

    @Override
    public SerieBuilder setClassificacaoEtaria(String classificacaoEtaria) {
        this.serie.setClassificacaoEtaria(classificacaoEtaria);
        return this;
    }

    @Override
    public SerieBuilder setNota(Double nota) {
        this.serie.setNota(nota);
        return this;
    }

    @Override
    public SerieBuilder setDataCriacao(LocalDate dataCriacao) {
        this.serie.setDataCriacao(dataCriacao);
        return this;
    }

    @Override
    public SerieBuilder setDataModificacao(LocalDate dataModificacao) {
        this.serie.setDataModificacao(dataModificacao);
        return this;
    }

    @Override
    public SerieBuilder setReviews(List<Review> reviews) {
        this.serie.setReviews(reviews);
        return this;
    }

    public SerieBuilder setNumTemporadas(Long numTemporadas) {
        this.serie.setNumTemporadas(numTemporadas);
        return this;
    }

    public SerieBuilder setNumEpisodios(Long numEpisodios) {
        this.serie.setNumEpisodios(numEpisodios);
        return this;
    }

    public SerieBuilder setEmissora(String emissora) {
        this.serie.setEmissora(emissora);
        return this;
    }

    public Serie build() {
        return this.serie;
    }
}
