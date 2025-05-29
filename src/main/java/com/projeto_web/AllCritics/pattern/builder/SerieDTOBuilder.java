package com.projeto_web.AllCritics.pattern.builder;


import com.projeto_web.AllCritics.dto.ReviewDTO;
import com.projeto_web.AllCritics.dto.SerieDTO;

import java.time.LocalDate;
import java.util.List;

public class SerieDTOBuilder implements ConteudoDTOBuilder<SerieDTOBuilder> {

    private final SerieDTO serieDTO;

    private SerieDTOBuilder() {
        this.serieDTO = new SerieDTO();
    }

    public static SerieDTOBuilder getInstance() {
        return new SerieDTOBuilder();
    }
    @Override
    public SerieDTOBuilder setIdConteudo(Long idConteudo) {
        this.serieDTO.setIdConteudo(idConteudo);
        return this;
    }

    @Override
    public SerieDTOBuilder setTitulo(String titulo) {
        this.serieDTO.setTitulo(titulo);
        return this;
    }

    @Override
    public SerieDTOBuilder setDescricao(String descricao) {
        this.serieDTO.setDescricao(descricao);
        return this;
    }

    @Override
    public SerieDTOBuilder setTipoConteudo(String tipoConteudo) {
        this.serieDTO.setTipoConteudo(tipoConteudo);
        return this;
    }

    @Override
    public SerieDTOBuilder setUrlImagem(String urlImagem) {
        this.serieDTO.setUrlImagem(urlImagem);
        return this;
    }

    @Override
    public SerieDTOBuilder setDataLancamento(LocalDate dataLancamento) {
        this.serieDTO.setDataLancamento(dataLancamento);
        return this;
    }

    @Override
    public SerieDTOBuilder setClassificacaoEtaria(String classificacaoEtaria) {
        this.serieDTO.setClassificacaoEtaria(classificacaoEtaria);
        return this;
    }

    @Override
    public SerieDTOBuilder setNota(Double nota) {
        this.serieDTO.setNota(nota);
        return this;
    }

    @Override
    public SerieDTOBuilder setDataCriacao(LocalDate dataCriacao) {
        this.serieDTO.setDataCriacao(dataCriacao);
        return this;
    }

    @Override
    public SerieDTOBuilder setDataModificacao(LocalDate dataModificacao) {
        this.serieDTO.setDataModificacao(dataModificacao);
        return this;
    }

    @Override
    public SerieDTOBuilder setReviews(List<ReviewDTO> reviews) {
        this.serieDTO.setReviews(reviews);
        return this;
    }

    public SerieDTOBuilder setNumTemporadas(Long numTemporadas) {
        this.serieDTO.setNumTemporadas(numTemporadas);
        return this;
    }

    public SerieDTOBuilder setNumEpisodios(Long numEpisodios) {
        this.serieDTO.setNumEpisodios(numEpisodios);
        return this;
    }

    public SerieDTOBuilder setEmissora(String emissora) {
        this.serieDTO.setEmissora(emissora);
        return this;
    }

    public SerieDTO build() {
        return this.serieDTO;
    }
}
