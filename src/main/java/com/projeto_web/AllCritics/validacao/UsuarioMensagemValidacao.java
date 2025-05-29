package com.projeto_web.AllCritics.validacao;

public enum UsuarioMensagemValidacao {
    ERRO_USUARIO_VALIDACAO_EMAIL("Email inválido ou não cadastrado."),
    ERRO_USUARIO_VALIDACAO_SENHA("Senha inválida."),
    ERRO_USUARIO_NAO_ENCONTRADO("Usuário não foi encontrado."),
    ERRO_USUARIO_NAO_ENCONTRADO_AO_EXCLUIR("Nenhum usuário encontrado ao tentar excluir."),
    ERRO_USUARIO_AINDA_EXISTE("Usuário não foi deletado."),
    ERRO_USUARIO_NOME_INVALIDO("Nome do usuário deve ser fornecido e deve conter mais de 3 caracteres."),
    ERRO_USUARIO_EMAIL_JA_CADASTRADO("Email já cadastrado."),
    ERRO_USUARIO_EMAIL_SENHA_OBRIGATORIOS("Email e senha são obrigatorios."),
    ERRO_USUARIO_NAO_FOI_CADASTRADO("Usuário não foi cadastrado corretamente."),
    ERRO_USUARIO_LOGIN_BODY_VAZIO("Login body está vazio");

    private String mensagem;

    UsuarioMensagemValidacao(String mensagem) {
        this.mensagem = mensagem;
    }
    public String getMensagem() {
        return mensagem;
    }
}
