package com.projeto_web.AllCritics.pattern.builder;

import com.projeto_web.AllCritics.dto.JogoDTO;
import com.projeto_web.AllCritics.dto.ReviewDTO;

import java.time.LocalDate;
import java.util.List;

public class JogoDTOBuilder implements ConteudoDTOBuilder<JogoDTOBuilder> {
    
    private final JogoDTO jogoDTO;
    
    private JogoDTOBuilder() {
        this.jogoDTO = new JogoDTO();
    }
    
    public static JogoDTOBuilder getInstance() {
        return new JogoDTOBuilder();
    }

    @Override
    public JogoDTOBuilder setIdConteudo(Long idConteudo) {
        this.jogoDTO.setIdConteudo(idConteudo);
        return this;
    }

    @Override
    public JogoDTOBuilder setTitulo(String titulo) {
        this.jogoDTO.setTitulo(titulo);
        return this;
    }

    @Override
    public JogoDTOBuilder setDescricao(String descricao) {
        this.jogoDTO.setDescricao(descricao);
        return this;
    }

    @Override
    public JogoDTOBuilder setTipoConteudo(String tipoConteudo) {
        this.jogoDTO.setTipoConteudo(tipoConteudo);
        return this;
    }

    @Override
    public JogoDTOBuilder setUrlImagem(String urlImagem) {
        this.jogoDTO.setUrlImagem(urlImagem);
        return this;
    }

    @Override
    public JogoDTOBuilder setDataLancamento(LocalDate dataLancamento) {
        this.jogoDTO.setDataLancamento(dataLancamento);
        return this;
    }

    @Override
    public JogoDTOBuilder setClassificacaoEtaria(String classificacaoEtaria) {
        this.jogoDTO.setClassificacaoEtaria(classificacaoEtaria);
        return this;
    }

    @Override
    public JogoDTOBuilder setNota(Double nota) {
        this.jogoDTO.setNota(nota);
        return this;
    }

    @Override
    public JogoDTOBuilder setDataCriacao(LocalDate dataCriacao) {
        this.jogoDTO.setDataCriacao(dataCriacao);
        return this;
    }

    @Override
    public JogoDTOBuilder setDataModificacao(LocalDate dataModificacao) {
        this.jogoDTO.setDataModificacao(dataModificacao);
        return this;
    }

    @Override
    public JogoDTOBuilder setReviews(List<ReviewDTO> reviews) {
        this.jogoDTO.setReviews(reviews);
        return this;
    }

    public JogoDTOBuilder setPlataforma(String plataforma) {
        this.jogoDTO.setPlataforma(plataforma);
        return this;
    }
    public JogoDTOBuilder setDesenvolvedora(String desenvolvedora) {
        this.jogoDTO.setDesenvolvedora(desenvolvedora);
        return this;
    }
    public JogoDTOBuilder setMultiplayer(Boolean multiplayer) {
        this.jogoDTO.setMultiplayer(multiplayer);
        return this;
    }

    public JogoDTO build() {
        return this.jogoDTO;
    }
}
