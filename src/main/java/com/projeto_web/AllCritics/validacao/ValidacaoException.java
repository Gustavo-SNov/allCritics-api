package com.projeto_web.AllCritics.validacao;

public class ValidacaoException extends RuntimeException {

    public ValidacaoException(String mensagem) {
        super(mensagem);
    }

    public ValidacaoException(String mensagem, Throwable cause) {
        super(mensagem, cause);
    }
}
