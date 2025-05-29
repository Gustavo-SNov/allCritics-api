package com.projeto_web.AllCritics.dominio;

import jakarta.persistence.*;

@Entity
public class Jogo extends Conteudo{

    @Column(name = "plataforma")
    private String plataforma;
    @Column(name = "desenvolvedora")
    private String desenvolvedora;
    @Column(name = "multiplayer")
    private Boolean multiplayer;

    public String getPlataforma() {
        return plataforma;
    }

    public void setPlataforma(String plataforma) {
        this.plataforma = plataforma;
    }

    public String getDesenvolvedora() {
        return desenvolvedora;
    }

    public void setDesenvolvedora(String desenvolvedora) {
        this.desenvolvedora = desenvolvedora;
    }

    public Boolean getMultiplayer() {
        return multiplayer;
    }

    public void setMultiplayer(Boolean multiplayer) {
        this.multiplayer = multiplayer;
    }

    @Override
    public String toString() {
        return "Jogo{" +
                "idConteudo=" + this.getIdConteudo() +
                ", titulo='" + this.getTitulo() + '\'' +
                ", descricao='" +this.getDescricao() + '\'' +
                ", tipoConteudo='" + this.getTipoConteudo() + '\'' +
                ", urlImagem='" + this.getUrlImagem() + '\'' +
                ", dataLancamento=" + this.getDataLancamento() +
                ", dataCriacao=" + this.getDataCriacao() +
                ", dataModificacao=" + this.getDataModificacao() +
                ", plataforma='" + plataforma + '\'' +
                ", desenvolvedora='" + desenvolvedora + '\'' +
                ", multiplayer=" + multiplayer +
                '}';
    }
}
