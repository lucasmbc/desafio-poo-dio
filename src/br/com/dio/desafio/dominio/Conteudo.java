package br.com.dio.desafio.dominio;

public abstract class Conteudo {

    protected static final double XP_PADRAO = 10d;

    private final String titulo;
    private final String descricao;

    protected Conteudo(String titulo, String descricao) {
        validarTexto(titulo, "Título");
        validarTexto(descricao, "Descrição");

        this.titulo = titulo;
        this.descricao = descricao;
    }

    public abstract double calcularXp();

    public String getTitulo() {
        return titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    private void validarTexto(String valor, String campo) {
        if (valor == null || valor.isBlank()) {
            throw new IllegalArgumentException(campo + " é obrigatório.");
        }
    }

    @Override
    public String toString() {
        return "titulo='" + titulo + '\'' +
                ", descricao='" + descricao + '\'';
    }

}
