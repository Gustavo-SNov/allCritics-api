package com.projeto_web.AllCritics.dominio;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;


@Entity
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_review")
    private Long idReview;

    @Column(name = "id_usuario")
    private Long idUsuario;

    @Column(name = "id_conteudo")
    private Long idConteudo;

    @Column(name = "nota", nullable = false)
    private Double nota;

    @Column(name = "comentario")
    private String comentario;

    @Column(name = "data_criacao")
    private LocalDate dataCriacao;

    @Column(name = "data_modificacao")
    private LocalDate dataModificacao;

    @ManyToOne
    @JoinColumn(name = "id_usuario", nullable = false, insertable=false, updatable=false)
    private Usuario usuario;

    @ManyToOne
    @JoinColumn(name = "id_conteudo",nullable = false, insertable=false, updatable=false)
    private Conteudo conteudo;

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

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Conteudo getConteudo() {
        return conteudo;
    }

    public void setConteudo(Conteudo conteudo) {
        this.conteudo = conteudo;
    }

    @Override
    public String toString() {
        return "Review{" +
                "idReview=" + idReview +
                ", idUsuario=" + idUsuario +
                ", idConteudo=" + idConteudo +
                ", idUsuario=" + idUsuario +
                ", nota=" + nota +
                ", comentario='" + comentario + '\'' +
                ", dataCriacao=" + dataCriacao +
                ", dataModificacao=" + dataModificacao +
                '}';
    }
}
