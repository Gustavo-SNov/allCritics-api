package com.projeto_web.AllCritics.validacao;

public class ReviewExceptionHandler extends ValidacaoException{

    public ReviewExceptionHandler(String mensagem) {
        super(mensagem);
    }

    public ReviewExceptionHandler(String mensagem, Throwable cause) {
        super(mensagem, cause);
    }
}
