package com.projeto_web.AllCritics.validacao;

public enum ReviewMensagemValidacao {
    ERRO_REVIEW_NAO_ENCONTRADO("Review não foi encontrado"),
    ERRO_REVIEW_NAO_FOI_EXCLUIDO("Review não foi excluído."),
    ERRO_REVIEW_NAO_FOI_CADASTRADO("Review não foi cadastrado.");


    private String mensagem;
    ReviewMensagemValidacao(String mensagem) {
        this.mensagem = mensagem;
    }
    public String getMensagem() {
        return this.mensagem;
    }
}
