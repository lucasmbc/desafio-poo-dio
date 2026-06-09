package br.com.dio.desafio.exception;

public class ProgressaoInvalidaException extends RuntimeException {
    public ProgressaoInvalidaException(String mensagem) {
        super(mensagem);
    }
}
