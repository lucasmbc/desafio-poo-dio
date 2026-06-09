package br.com.dio.desafio.service;

import br.com.dio.desafio.dominio.Bootcamp;
import br.com.dio.desafio.dominio.Dev;

public class InscricaoService {
    private final Dev dev;

    public InscricaoService(Dev dev) {
        this.dev = dev;
    }

    public void inscrever(Bootcamp bootcamp) {

        dev.getConteudosInscritos()
                .addAll(
                        bootcamp.getConteudos()
                );

        bootcamp.adicionarDev(dev);
    }

    public Dev getDev() {
        return dev;
    }
}
