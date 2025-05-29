package com.projeto_web.AllCritics.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

public class ConteudoDTO {


    private Long idConteudo;
    private String titulo;
    private String descricao;
    private String tipoConteudo;
    private String urlImagem;
    private LocalDate dataLancamento;
    private String classificacaoEtaria;
    private LocalDate dataCriacao;
    private LocalDate dataModificacao;
    private Double nota;

    private List<ReviewDTO> reviews;

    public Long getIdConteudo() {
        return idConteudo;
    }

    public void setIdConteudo(Long idConteudo) {
        this.idConteudo = idConteudo;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getTipoConteudo() {
        return tipoConteudo;
    }

    public void setTipoConteudo(String tipoConteudo) {
        this.tipoConteudo = tipoConteudo;
    }

    public String getUrlImagem() {
        return urlImagem;
    }

    public void setUrlImagem(String urlImagem) {
        this.urlImagem = urlImagem;
    }

    public LocalDate getDataLancamento() {
        return dataLancamento;
    }

    public void setDataLancamento(LocalDate dataLancamento) {
        this.dataLancamento = dataLancamento;
    }

    public String getClassificacaoEtaria() {
        return classificacaoEtaria;
    }

    public void setClassificacaoEtaria(String classificacaoEtaria) {
        this.classificacaoEtaria = classificacaoEtaria;
    }

    public LocalDate getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(LocalDate dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

    public LocalDate getDataModificacao() {
        return dataModificacao;
    }

    public void setDataModificacao(LocalDate dataModificacao) {
        this.dataModificacao = dataModificacao;
    }

    public Double getNota() {
        return nota;
    }

    public void setNota(Double nota) {
        this.nota = nota;
    }

    public List<ReviewDTO> getReviews() {
        return reviews;
    }

    public void setReviews(List<ReviewDTO> reviews) {
        this.reviews = reviews;
    }

    @Override
    public String toString() {
        return "ConteudoDTO{" +
                "idConteudo=" + idConteudo +
                ", titulo='" + titulo + '\'' +
                ", descricao='" + descricao + '\'' +
                ", tipoConteudo='" + tipoConteudo + '\'' +
                ", urlImagem='" + urlImagem + '\'' +
                ", dataLancamento=" + dataLancamento +
                ", classificacaoEtaria='" + classificacaoEtaria + '\'' +
                ", dataCriacao=" + dataCriacao +
                ", dataModificacao=" + dataModificacao +
                '}';
    }
}
