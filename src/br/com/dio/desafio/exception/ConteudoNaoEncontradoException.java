package br.com.dio.desafio.exception;

public class ConteudoNaoEncontradoException extends RuntimeException {
    public ConteudoNaoEncontradoException(String mensagem) {
        super(mensagem);
    }
}
