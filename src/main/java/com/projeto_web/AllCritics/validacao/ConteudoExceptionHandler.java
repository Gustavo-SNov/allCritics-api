package com.projeto_web.AllCritics.validacao;

public class ConteudoExceptionHandler extends ValidacaoException {
    public ConteudoExceptionHandler(String mensagem) {
        super(mensagem);
    }

    public ConteudoExceptionHandler(String mensagem, Throwable cause) {
        super(mensagem, cause);
    }
}
