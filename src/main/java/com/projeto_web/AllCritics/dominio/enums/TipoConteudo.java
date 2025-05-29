package com.projeto_web.AllCritics.dominio.enums;

public enum TipoConteudo {
    FILME("FILME"),SERIE("SERIE"),JOGO("JOGO"),ALL("ALL");

    private final String tipo;

    TipoConteudo(String tipo) {
        this.tipo = tipo;
    }

    private String getTipo() {
        return tipo;
    }

    public static TipoConteudo fromString(String valor) {
        for (TipoConteudo t : TipoConteudo.values()) {
            if (t.tipo.equalsIgnoreCase(valor) || t.name().equalsIgnoreCase(valor)) {
                return t;
            }
        }
        throw new IllegalArgumentException("Nenhum enum TipoConteudo corresponde a: " + valor);
    }
}
