package com.projeto_web.AllCritics.dominio;

import jakarta.persistence.*;

@Entity
public class Filme extends Conteudo{

    @Column(name = "duracao")
    private Long duracao;
    @Column(name = "diretor")
    private String diretor;
    @Column(name = "roteirista")
    private String roteirista;


    public Long getDuracao() {
        return duracao;
    }

    public void setDuracao(Long duracao) {
        this.duracao = duracao;
    }

    public String getDiretor() {
        return diretor;
    }

    public void setDiretor(String diretor) {
        this.diretor = diretor;
    }

    public String getRoteirista() {
        return roteirista;
    }

    public void setRoteirista(String roteirista) {
        this.roteirista = roteirista;
    }

    @Override
    public String toString() {
        return "Filme{" +
                "idConteudo=" + this.getIdConteudo() +
                ", titulo='" + this.getTitulo() + '\'' +
                ", descricao='" +this.getDescricao() + '\'' +
                ", tipoConteudo='" + this.getTipoConteudo() + '\'' +
                ", urlImagem='" + this.getUrlImagem() + '\'' +
                ", dataLancamento=" + this.getDataLancamento() +
                ", dataCriacao=" + this.getDataCriacao() +
                ", dataModificacao=" + this.getDataModificacao() +
                ", duracao=" + duracao +
                ", diretor='" + diretor + '\'' +
                ", roteirista='" + roteirista + '\'' +
                '}';
    }
}
