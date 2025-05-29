package com.projeto_web.AllCritics.pattern.builder;

import com.projeto_web.AllCritics.dto.FilmeDTO;
import com.projeto_web.AllCritics.dto.ReviewDTO;

import java.time.LocalDate;
import java.util.List;

public class FilmeDTOBuilder implements ConteudoDTOBuilder<FilmeDTOBuilder> {

    private final FilmeDTO filmeDTO;

    private FilmeDTOBuilder() {
        this.filmeDTO = new FilmeDTO();
    }

    public static FilmeDTOBuilder getInstance() {
        return new FilmeDTOBuilder();
    }

    @Override
    public FilmeDTOBuilder setIdConteudo(Long idConteudo) {
        this.filmeDTO.setIdConteudo(idConteudo);
        return this;
    }

    @Override
    public FilmeDTOBuilder setTitulo(String titulo) {
        this.filmeDTO.setTitulo(titulo);
        return this;
    }

    @Override
    public FilmeDTOBuilder setDescricao(String descricao) {
        this.filmeDTO.setDescricao(descricao);
        return this;
    }

    @Override
    public FilmeDTOBuilder setTipoConteudo(String tipoConteudo) {
        this.filmeDTO.setTipoConteudo(tipoConteudo);
        return this;
    }

    @Override
    public FilmeDTOBuilder setUrlImagem(String urlImagem) {
        this.filmeDTO.setUrlImagem(urlImagem);
        return this;
    }

    @Override
    public FilmeDTOBuilder setDataLancamento(LocalDate dataLancamento) {
        this.filmeDTO.setDataLancamento(dataLancamento);
        return this;
    }

    @Override
    public FilmeDTOBuilder setClassificacaoEtaria(String classificacaoEtaria) {
        this.filmeDTO.setClassificacaoEtaria(classificacaoEtaria);
        return this;
    }

    @Override
    public FilmeDTOBuilder setNota(Double nota) {
        this.filmeDTO.setNota(nota);
        return this;
    }

    @Override
    public FilmeDTOBuilder setDataCriacao(LocalDate dataCriacao) {
        this.filmeDTO.setDataCriacao(dataCriacao);
        return this;
    }

    @Override
    public FilmeDTOBuilder setDataModificacao(LocalDate dataModificacao) {
        this.filmeDTO.setDataModificacao(dataModificacao);
        return this;
    }

    @Override
    public FilmeDTOBuilder setReviews(List<ReviewDTO> reviews) {
        this.filmeDTO.setReviews(reviews);
        return this;
    }

    public FilmeDTOBuilder setDuracao(Long duracao) {
        this.filmeDTO.setDuracao(duracao);
        return this;
    }

    public FilmeDTOBuilder setDiretor(String diretor) {
        this.filmeDTO.setDiretor(diretor);
        return this;
    }

    public FilmeDTOBuilder setRoteirista(String roteirista) {
        this.filmeDTO.setRoteirista(roteirista);
        return this;
    }

    public FilmeDTO build() {
        return this.filmeDTO;
    }
}
