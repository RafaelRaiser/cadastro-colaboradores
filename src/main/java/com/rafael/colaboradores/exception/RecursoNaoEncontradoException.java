package com.rafael.colaboradores.exception;

// HERANCA: esta classe e um tipo de RuntimeException
public class RecursoNaoEncontradoException extends RuntimeException {
    
    public RecursoNaoEncontradoException(String mensagem) {
        super(mensagem);// passa a mensagem para  a classe mae
    }
}
