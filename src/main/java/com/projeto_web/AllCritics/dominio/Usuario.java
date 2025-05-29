package com.projeto_web.AllCritics.dominio;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Entity
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_usuario")
    private Long idUsuario;

    @Column(name = "nome", nullable = false)
    private String nome;
    @Column(name = "nome_identificador", nullable = false)
    private String nomeIdentificador;
    @Column(name = "biografia")
    private String biografia;
    @Column(name = "urlFotoPerfil")
    private String urlFotoPerfil;
    @Column(name = "data_criacao")
    private LocalDate dataCriacao;
    @Column(name = "data_modificacao")
    private LocalDate dataModificacao;

    @OneToOne(mappedBy = "usuario", cascade = CascadeType.ALL, orphanRemoval = true)
    private Login login;

    @JsonIgnore // Evita carregar as reviews automaticamente
    @OneToMany(mappedBy = "usuario", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Review> reviews;

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

    public Login getLogin() {
        return login;
    }

    public void setLogin(Login login) {
        this.login = login;
    }

    public List<Review> getReviews() {
        return reviews;
    }

    public void setReviews(List<Review> reviews) {
        this.reviews = reviews;
    }

    @Override
    public String toString() {
        return "Usuario{" +
                "idUsuario=" + idUsuario +
                ", nome='" + nome + '\'' +
                ", nomeIdentificador='" + nomeIdentificador + '\'' +
                ", biografia='" + biografia + '\'' +
                ", urlFotoPerfil='" + urlFotoPerfil + '\'' +
                ", dataCriacao=" + dataCriacao +
                ", dataModificacao=" + dataModificacao +
                ", login=" + login +
                ", reviews=" + reviews +
                '}';
    }
}
