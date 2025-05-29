package com.projeto_web.AllCritics.dto;

import java.time.LocalDate;

public class ReviewDTO {
    private Long idReview;
    private Long idUsuario;
    private Long idConteudo;
    private Double nota;
    private String comentario;
    private LocalDate dataCriacao;
    private LocalDate dataModificacao;
    private ConteudoDTO conteudo;
    private UsuarioDTO usuario;


    public Long getIdReview() {
        return idReview;
    }

    public void setIdReview(Long idReview) {
        this.idReview = idReview;
    }

    public Long getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Long idUsuario) {
        this.idUsuario = idUsuario;
    }

    public Long getIdConteudo() {
        return idConteudo;
    }

    public void setIdConteudo(Long idConteudo) {
        this.idConteudo = idConteudo;
    }

    public Double getNota() {
        return nota;
    }

    public void setNota(Double nota) {
        this.nota = nota;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
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

    public ConteudoDTO getConteudoDTO() {
        return conteudo;
    }

    public void setConteudoDTO(ConteudoDTO conteudo) {
        this.conteudo = conteudo;
    }

    public UsuarioDTO getUsuario() {
        return usuario;
    }

    public void setUsuario(UsuarioDTO usuario) {
        this.usuario = usuario;
    }

    @Override
    public String toString() {
        return "ReviewDTO{" +
                "idReview=" + idReview +
                ", idUsuario=" + idUsuario +
                ", idConteudo=" + idConteudo +
                ", nota=" + nota +
                ", comentario='" + comentario + '\'' +
                ", dataCriacao=" + dataCriacao +
                ", dataModificacao=" + dataModificacao +
                '}';
    }
}
