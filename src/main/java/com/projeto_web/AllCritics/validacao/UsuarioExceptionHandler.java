package com.projeto_web.AllCritics.validacao;

public class UsuarioExceptionHandler extends ValidacaoException{

    public UsuarioExceptionHandler(String mensagem) {
        super(mensagem);
    }

    public UsuarioExceptionHandler(String mensagem, Throwable cause) {
        super(mensagem, cause);
    }
}
