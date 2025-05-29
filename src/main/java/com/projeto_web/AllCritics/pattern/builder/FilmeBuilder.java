package com.projeto_web.AllCritics.pattern.builder;

import com.projeto_web.AllCritics.dominio.Filme;

import com.projeto_web.AllCritics.dominio.Review;

import java.time.LocalDate;
import java.util.List;

public class FilmeBuilder implements ConteudoBuilder<FilmeBuilder> {
    private final Filme filme;

    private FilmeBuilder() {
        this.filme = new Filme();
    }

    public static FilmeBuilder getInstance() {
        return new FilmeBuilder();
    }

    @Override
    public FilmeBuilder setIdConteudo(Long idConteudo) {
        this.filme.setIdConteudo(idConteudo);
        return this;
    }

    @Override
    public FilmeBuilder setTitulo(String titulo) {
        this.filme.setTitulo(titulo);
        return this;
    }

    @Override
    public FilmeBuilder setDescricao(String descricao) {
        this.filme.setDescricao(descricao);
        return this;
    }

    @Override
    public FilmeBuilder setTipoConteudo(String tipoConteudo) {
        this.filme.setTipoConteudo(tipoConteudo);
        return this;
    }

    @Override
    public FilmeBuilder setUrlImagem(String urlImagem) {
        this.filme.setUrlImagem(urlImagem);
        return this;
    }

    @Override
    public FilmeBuilder setDataLancamento(LocalDate dataLancamento) {
        this.filme.setDataLancamento(dataLancamento);
        return this;
    }

    @Override
    public FilmeBuilder setClassificacaoEtaria(String classificacaoEtaria) {
        this.filme.setClassificacaoEtaria(classificacaoEtaria);
        return this;
    }

    @Override
    public FilmeBuilder setNota(Double nota) {
        this.filme.setNota(nota);
        return this;
    }

    @Override
    public FilmeBuilder setDataCriacao(LocalDate dataCriacao) {
        this.filme.setDataCriacao(dataCriacao);
        return this;
    }

    @Override
    public FilmeBuilder setDataModificacao(LocalDate dataModificacao) {
        this.filme.setDataModificacao(dataModificacao);
        return this;
    }

    @Override
    public FilmeBuilder setReviews(List<Review> reviews) {
        this.filme.setReviews(reviews);
        return this;
    }

    public FilmeBuilder setDuracao(Long duracao) {
        this.filme.setDuracao(duracao);
        return this;
    }

    public FilmeBuilder setDiretor(String diretor) {
        this.filme.setDiretor(diretor);
        return this;
    }

    public FilmeBuilder setRoteirista(String roteirista) {
        this.filme.setRoteirista(roteirista);
        return this;
    }

    public Filme build() {
        return this.filme;
    }
}
