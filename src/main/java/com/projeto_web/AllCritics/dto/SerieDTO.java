package com.projeto_web.AllCritics.dto;

import lombok.Getter;
import lombok.Setter;


public class SerieDTO extends ConteudoDTO {
    private Long numTemporadas;
    private Long numEpisodios;
    private String emissora;

    public Long getNumTemporadas() {
        return numTemporadas;
    }

    public void setNumTemporadas(Long numTemporadas) {
        this.numTemporadas = numTemporadas;
    }

    public Long getNumEpisodios() {
        return numEpisodios;
    }

    public void setNumEpisodios(Long numEpisodios) {
        this.numEpisodios = numEpisodios;
    }

    public String getEmissora() {
        return emissora;
    }

    public void setEmissora(String emissora) {
        this.emissora = emissora;
    }
}
