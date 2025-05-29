package com.projeto_web.AllCritics.dominio;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.projeto_web.AllCritics.convertor.TipoConteudoConvertor;
import com.projeto_web.AllCritics.dominio.enums.TipoConteudo;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.List;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class Conteudo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_conteudo")
    private Long idConteudo;

    @Column(name = "titulo",unique = true, nullable = false)
    private String titulo;

    @Column(name = "descricao", nullable = false)
    private String descricao;

    @Column(name = "tipo", nullable = false)
    private String tipoConteudo;

    @Column(name = "url_imagem", nullable = false)
    private String urlImagem;

    @Column(name = "data_lancamento", nullable = false)
    private LocalDate dataLancamento;

    @Column(name = "classificacao_etaria")
    private String classificacaoEtaria;

    @Column(name="nota")
    private Double nota;

    @Column(name = "data_criacao", nullable = false)
    private LocalDate dataCriacao;

    @Column(name = "data_modificacao")
    private LocalDate dataModificacao;

    @JsonIgnore // Evita carregar as reviews automaticamente
    @OneToMany(mappedBy = "conteudo", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Review> reviews;

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

    public LocalDate getDataLancamento() {
        return dataLancamento;
    }

    public void setDataLancamento(LocalDate dataLancamento) {
        this.dataLancamento = dataLancamento;
    }

    public String getUrlImagem() {
        return urlImagem;
    }

    public void setUrlImagem(String urlImagem) {
        this.urlImagem = urlImagem;
    }

    public String getClassificacaoEtaria() {
        return classificacaoEtaria;
    }

    public void setClassificacaoEtaria(String classificacaoEtaria) {
        this.classificacaoEtaria = classificacaoEtaria;
    }

    public Double getNota() {
        return nota;
    }

    public void setNota(Double nota) {
        this.nota = nota;
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

    public List<Review> getReviews() {
        return reviews;
    }

    public void setReviews(List<Review> reviews) {
        this.reviews = reviews;
    }

    @Override
    public String toString() {
        return "Conteudo{" +
                "idConteudo=" + idConteudo +
                ", titulo='" + titulo + '\'' +
                ", descricao='" + descricao + '\'' +
                ", tipoConteudo='" + tipoConteudo + '\'' +
                ", urlImagem='" + urlImagem + '\'' +
                ", dataLancamento=" + dataLancamento +
                ", dataCriacao=" + dataCriacao +
                ", dataModificacao=" + dataModificacao +
                '}';
    }


}
