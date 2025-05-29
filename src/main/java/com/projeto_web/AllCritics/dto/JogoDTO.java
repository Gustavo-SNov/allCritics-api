package com.projeto_web.AllCritics.dto;

import lombok.Getter;
import lombok.Setter;


public class JogoDTO extends ConteudoDTO {
    private String plataforma;
    private String desenvolvedora;
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
}
