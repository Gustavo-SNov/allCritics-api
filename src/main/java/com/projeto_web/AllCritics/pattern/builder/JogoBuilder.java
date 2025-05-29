package com.projeto_web.AllCritics.pattern.builder;




import com.projeto_web.AllCritics.dominio.Jogo;
import com.projeto_web.AllCritics.dominio.Review;

import java.time.LocalDate;
import java.util.List;

public class JogoBuilder implements ConteudoBuilder<JogoBuilder> {

    private final Jogo jogoDTO;

    private JogoBuilder() {
        this.jogoDTO = new Jogo();
    }

    public static JogoBuilder getInstance() {
        return new JogoBuilder();
    }

    @Override
    public JogoBuilder setIdConteudo(Long idConteudo) {
        this.jogoDTO.setIdConteudo(idConteudo);
        return this;
    }

    @Override
    public JogoBuilder setTitulo(String titulo) {
        this.jogoDTO.setTitulo(titulo);
        return this;
    }

    @Override
    public JogoBuilder setDescricao(String descricao) {
        this.jogoDTO.setDescricao(descricao);
        return this;
    }

    @Override
    public JogoBuilder setTipoConteudo(String tipoConteudo) {
        this.jogoDTO.setTipoConteudo(tipoConteudo);
        return this;
    }

    @Override
    public JogoBuilder setUrlImagem(String urlImagem) {
        this.jogoDTO.setUrlImagem(urlImagem);
        return this;
    }

    @Override
    public JogoBuilder setDataLancamento(LocalDate dataLancamento) {
        this.jogoDTO.setDataLancamento(dataLancamento);
        return this;
    }

    @Override
    public JogoBuilder setClassificacaoEtaria(String classificacaoEtaria) {
        this.jogoDTO.setClassificacaoEtaria(classificacaoEtaria);
        return this;
    }

    @Override
    public JogoBuilder setNota(Double nota) {
        this.jogoDTO.setNota(nota);
        return this;
    }

    @Override
    public JogoBuilder setDataCriacao(LocalDate dataCriacao) {
        this.jogoDTO.setDataCriacao(dataCriacao);
        return this;
    }

    @Override
    public JogoBuilder setDataModificacao(LocalDate dataModificacao) {
        this.jogoDTO.setDataModificacao(dataModificacao);
        return this;
    }

    @Override
    public JogoBuilder setReviews(List<Review> reviews) {
        this.jogoDTO.setReviews(reviews);
        return this;
    }

    public JogoBuilder setPlataforma(String plataforma) {
        this.jogoDTO.setPlataforma(plataforma);
        return this;
    }
    public JogoBuilder setDesenvolvedora(String desenvolvedora) {
        this.jogoDTO.setDesenvolvedora(desenvolvedora);
        return this;
    }
    public JogoBuilder setMultiplayer(Boolean multiplayer) {
        this.jogoDTO.setMultiplayer(multiplayer);
        return this;
    }

    public Jogo build() {
        return this.jogoDTO;
    }
}
