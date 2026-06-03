package br.com.dio.desafio.dominio;

import java.time.LocalDate;

public class Mentoria extends Conteudo{

    private static final double BONUS_XP = 20.0;

    private final LocalDate data;

    public Mentoria(
            String titulo,
            String descricao,
            LocalDate data
    ) {
        super(titulo, descricao);

        if (data == null) {
            throw new IllegalArgumentException("Data é obrigatória.");
        }

        this.data = data;
    }

    @Override
    public double calcularXp() {
        return XP_PADRAO + BONUS_XP;
    }

    public LocalDate getData() {
        return data;
    }

    @Override
    public String toString() {
        return "Mentoria{" +
                super.toString() +
                ", data=" + data +
                '}';
    }
}
