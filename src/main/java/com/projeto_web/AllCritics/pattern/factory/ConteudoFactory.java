package com.projeto_web.AllCritics.pattern.factory;

import com.projeto_web.AllCritics.dominio.Conteudo;
import com.projeto_web.AllCritics.dominio.Filme;
import com.projeto_web.AllCritics.dominio.Jogo;
import com.projeto_web.AllCritics.dominio.Serie;
import com.projeto_web.AllCritics.dto.ConteudoDTO;
import com.projeto_web.AllCritics.dto.FilmeDTO;
import com.projeto_web.AllCritics.dto.JogoDTO;
import com.projeto_web.AllCritics.dto.SerieDTO;
import com.projeto_web.AllCritics.pattern.builder.FilmeDTOBuilder;
import com.projeto_web.AllCritics.pattern.builder.JogoDTOBuilder;
import com.projeto_web.AllCritics.pattern.builder.SerieDTOBuilder;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class ConteudoFactory {

    public Conteudo transformaEmConteudo(ConteudoDTO conteudoDTO) {

        if (conteudoDTO instanceof FilmeDTO filmeDTO) {
            FilmeDTOBuilder.getInstance()
                    .setIdConteudo(filmeDTO.getIdConteudo())
                    .setTitulo(filmeDTO.getTitulo())
                    .setDescricao(filmeDTO.getDescricao())
                    .setTipoConteudo(filmeDTO.getTipoConteudo())
                    .setUrlImagem(filmeDTO.getUrlImagem())
                    .setDataLancamento(filmeDTO.getDataLancamento())
                    .setClassificacaoEtaria(filmeDTO.getClassificacaoEtaria())
                    .setNota(filmeDTO.getNota())
                    .setDataCriacao(filmeDTO.getDataCriacao())
                    .setDataModificacao(filmeDTO.getDataModificacao())
                    .setDuracao(filmeDTO.getDuracao()).
                    setRoteirista(filmeDTO.getRoteirista())
                    .setDiretor(filmeDTO.getDiretor())
                    .build();
        } else if (conteudoDTO instanceof JogoDTO jogoDTO) {
            JogoDTOBuilder.getInstance()
                    .setIdConteudo(jogoDTO.getIdConteudo())
                    .setTitulo(jogoDTO.getTitulo())
                    .setDescricao(jogoDTO.getDescricao())
                    .setTipoConteudo(jogoDTO.getTipoConteudo())
                    .setUrlImagem(jogoDTO.getUrlImagem())
                    .setDataLancamento(jogoDTO.getDataLancamento())
                    .setClassificacaoEtaria(jogoDTO.getClassificacaoEtaria())
                    .setNota(jogoDTO.getNota())
                    .setDataCriacao(jogoDTO.getDataCriacao())
                    .setDataModificacao(jogoDTO.getDataModificacao())
                    .setReviews(jogoDTO.getReviews())
                    .setMultiplayer(jogoDTO.getMultiplayer()).setPlataforma(jogoDTO.getPlataforma()).setDesenvolvedora(jogoDTO.getDesenvolvedora())
                    .build();

        } else if (conteudoDTO instanceof SerieDTO serieDTO) {
            SerieDTOBuilder.getInstance()
                    .setIdConteudo(serieDTO.getIdConteudo())
                    .setTitulo(serieDTO.getTitulo())
                    .setDescricao(serieDTO.getDescricao())
                    .setTipoConteudo(serieDTO.getTipoConteudo())
                    .setUrlImagem(serieDTO.getUrlImagem())
                    .setDataLancamento(serieDTO.getDataLancamento())
                    .setClassificacaoEtaria(serieDTO.getClassificacaoEtaria())
                    .setNota(serieDTO.getNota())
                    .setDataCriacao(serieDTO.getDataCriacao())
                    .setDataModificacao(serieDTO.getDataModificacao()).setReviews(serieDTO.getReviews())
                    .setNumEpisodios(serieDTO.getNumEpisodios()).setNumTemporadas(serieDTO.getNumTemporadas()).setEmissora(serieDTO.getEmissora())
                    .build();

        }


        return null;
    }

    public ConteudoDTO transformaEmConteudoDTO(Conteudo conteudo) {
        if (conteudo == null){
            return null;
        }
        if (conteudo instanceof Filme filme) {
            return FilmeDTOBuilder.getInstance()
                    .setIdConteudo(filme.getIdConteudo())
                    .setTitulo(filme.getTitulo())
                    .setDescricao(filme.getDescricao())
                    .setTipoConteudo(filme.getTipoConteudo())
                    .setUrlImagem(filme.getUrlImagem())
                    .setDataLancamento(filme.getDataLancamento())
                    .setClassificacaoEtaria(filme.getClassificacaoEtaria())
                    .setNota(filme.getNota())
                    .setDataCriacao(filme.getDataCriacao())
                    .setDataModificacao(filme.getDataModificacao())
                    .setDuracao(filme.getDuracao()).
                    setRoteirista(filme.getRoteirista())
                    .setDiretor(filme.getDiretor())
                    .build();
        } else if (conteudo instanceof Jogo jogo) {
            return JogoDTOBuilder.getInstance()
                    .setIdConteudo(jogo.getIdConteudo())
                    .setTitulo(jogo.getTitulo())
                    .setDescricao(jogo.getDescricao())
                    .setTipoConteudo(jogo.getTipoConteudo())
                    .setUrlImagem(jogo.getUrlImagem())
                    .setDataLancamento(jogo.getDataLancamento())
                    .setClassificacaoEtaria(jogo.getClassificacaoEtaria())
                    .setNota(jogo.getNota())
                    .setDataCriacao(jogo.getDataCriacao())
                    .setDataModificacao(jogo.getDataModificacao())
                    .setMultiplayer(jogo.getMultiplayer()).setPlataforma(jogo.getPlataforma()).setDesenvolvedora(jogo.getDesenvolvedora())
                    .build();
        } else if (conteudo instanceof Serie serie) {
            return SerieDTOBuilder.getInstance()
                    .setIdConteudo(serie.getIdConteudo())
                    .setTitulo(serie.getTitulo())
                    .setDescricao(serie.getDescricao())
                    .setTipoConteudo(serie.getTipoConteudo())
                    .setUrlImagem(serie.getUrlImagem())
                    .setDataLancamento(serie.getDataLancamento())
                    .setClassificacaoEtaria(serie.getClassificacaoEtaria())
                    .setNota(serie.getNota())
                    .setDataCriacao(serie.getDataCriacao())
                    .setDataModificacao(serie.getDataModificacao())
                    .setNumEpisodios(serie.getNumEpisodios()).setNumTemporadas(serie.getNumTemporadas()).setEmissora(serie.getEmissora())
                    .build();

        }

        return null;
    }
}
