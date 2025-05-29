package com.projeto_web.AllCritics.dto;

import lombok.Getter;
import lombok.Setter;


public class FilmeDTO extends ConteudoDTO{

    private Long duracao;
    private String diretor;
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
}
