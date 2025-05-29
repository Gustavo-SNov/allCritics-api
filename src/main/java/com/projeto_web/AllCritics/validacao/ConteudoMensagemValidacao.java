package com.projeto_web.AllCritics.validacao;

public enum ConteudoMensagemValidacao {
    ERRO_CONTEUDO_NAO_ENCONTRADO("Conteudo não foi encontrado."),
    ERRO_CONTEUDO_CALCULO("Erro ao atualizar a nota do conteúdo"),
    ERRO_CONTEUDO_NAO_FOI_CADASTRADO("Erro ao atualizar o conteúdo");

    private String mensagem;

    ConteudoMensagemValidacao(String mensagem) {
        this.mensagem = mensagem;
    }

    public String getMensagem() {
        return mensagem;
    }
}
