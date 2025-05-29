package com.projeto_web.AllCritics.dto;

import com.projeto_web.AllCritics.dominio.enums.TipoConteudo;

public class FiltroConteudoDTO {

    private Boolean isFilme = false;
    private Boolean isSerie = false;
    private Boolean isJogo = false;
    private Boolean orderByUltimos = false;
    private Boolean orderByAvaliacao = false;
    private String titulo = "";


    public Boolean getFilme() {
        return isFilme;
    }

    public void setFilme(Boolean filme) {
        isFilme = filme;
    }

    public Boolean getSerie() {
        return isSerie;
    }

    public void setSerie(Boolean serie) {
        isSerie = serie;
    }

    public Boolean getJogo() {
        return isJogo;
    }

    public void setJogo(Boolean jogo) {
        isJogo = jogo;
    }

    public Boolean getOrderByUltimos() {
        return orderByUltimos;
    }

    public void setOrderByUltimos(Boolean orderByUltimos) {
        this.orderByUltimos = orderByUltimos;
    }

    public Boolean getOrderByAvaliacao() {
        return orderByAvaliacao;
    }

    public void setOrderByAvaliacao(Boolean orderByAvaliacao) {
        this.orderByAvaliacao = orderByAvaliacao;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    @Override
    public String toString() {
        return "FiltroConteudoDTO{" +
                "isFilme=" + isFilme +
                ", isSerie=" + isSerie +
                ", isJogo=" + isJogo +
                ", orderByUltimos=" + orderByUltimos +
                ", orderByAvaliacao=" + orderByAvaliacao +
                ", titulo='" + titulo + '\'' +
                '}';
    }
}
