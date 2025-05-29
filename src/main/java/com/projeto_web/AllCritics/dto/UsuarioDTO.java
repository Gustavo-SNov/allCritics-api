package com.projeto_web.AllCritics.dto;


import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;


public class UsuarioDTO {

    private Long idUsuario;

    private String nome;

    private String nomeIdentificador;

    private String biografia;

    private String urlFotoPerfil;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    private LocalDate dataCriacao;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    private LocalDate dataModificacao;

    private List<ReviewDTO> reviews;

    private List<ConteudoDTO> conteudos;

    private LoginDTO login;

    public Long getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Long idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getNomeIdentificador() {
        return nomeIdentificador;
    }

    public void setNomeIdentificador(String nomeIdentificador) {
        this.nomeIdentificador = nomeIdentificador;
    }

    public String getBiografia() {
        return biografia;
    }

    public void setBiografia(String biografia) {
        this.biografia = biografia;
    }

    public String getUrlFotoPerfil() {
        return urlFotoPerfil;
    }

    public void setUrlFotoPerfil(String urlFotoPerfil) {
        this.urlFotoPerfil = urlFotoPerfil;
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

    public LoginDTO getLogin() {
        return login;
    }

    public void setLogin(LoginDTO login) {
        this.login = login;
    }

    public List<ReviewDTO> getReviews() {
        return reviews;
    }

    public void setReviews(List<ReviewDTO> reviews) {
        this.reviews = reviews;
    }

    public List<ConteudoDTO> getConteudos() {
        return conteudos;
    }

    public void setConteudos(List<ConteudoDTO> conteudos) {
        this.conteudos = conteudos;
    }

    @Override
    public String toString() {
        return "UsuarioDTO{" +
                "idUsuario=" + idUsuario +
                ", nome='" + nome + '\'' +
                ", nomeIdentificador='" + nomeIdentificador + '\'' +
                ", biografia='" + biografia + '\'' +
                ", urlFotoPerfil='" + urlFotoPerfil + '\'' +
                ", dataCriacao=" + dataCriacao +
                ", dataModificacao=" + dataModificacao +
                ", login=" + login +
                '}';
    }
}
