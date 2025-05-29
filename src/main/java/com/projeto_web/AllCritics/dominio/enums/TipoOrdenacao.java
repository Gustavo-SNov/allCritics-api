package com.projeto_web.AllCritics.dominio.enums;

public enum TipoOrdenacao {
    ORDER_BY_ULTIMOS("ORDER_BY_ULTIMOS"),
    ORDER_BY_AVALIADOS("ORDER_BY_AVALIADOS"),
    NO_ORDER("NO_ORDER");

    private String mensagem;

    TipoOrdenacao(String mensagem) {
        this.mensagem = mensagem;
    }

    public String getMensagem() {
        return mensagem;
    }

}
