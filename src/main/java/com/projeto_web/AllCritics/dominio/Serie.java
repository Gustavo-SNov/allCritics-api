package com.projeto_web.AllCritics.dominio;

import jakarta.persistence.*;

@Entity
public class Serie extends Conteudo{

    @Column(name = "num_temporadas")
    private Long numTemporadas;
    @Column(name = "num_episodios")
    private Long numEpisodios;
    @Column(name = "emissora")
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

    @Override
    public String toString() {
        return "Serie{" +
                "idConteudo=" + this.getIdConteudo() +
                ", titulo='" + this.getTitulo() + '\'' +
                ", descricao='" +this.getDescricao() + '\'' +
                ", tipoConteudo='" + this.getTipoConteudo() + '\'' +
                ", urlImagem='" + this.getUrlImagem() + '\'' +
                ", dataLancamento=" + this.getDataLancamento() +
                ", dataCriacao=" + this.getDataCriacao() +
                ", dataModificacao=" + this.getDataModificacao() +
                ", numTemporadas=" + numTemporadas +
                ", numEpisodios=" + numEpisodios +
                ", emissora='" + emissora + '\'' +
                '}';
    }
}
