package br.com.dio.desafio.service;

import br.com.dio.desafio.dominio.Conteudo;
import br.com.dio.desafio.exception.ProgressaoInvalidaException;

public class ProgressaoService {
    private final InscricaoService inscricaoService;

    public ProgressaoService(InscricaoService inscricaoService) {
        this.inscricaoService = inscricaoService;
    }

    public void progredir() {

        Conteudo conteudo =
                inscricaoService.getDev().getConteudosInscritos()
                        .stream()
                        .findFirst()
                        .orElseThrow(() -> new ProgressaoInvalidaException("Não existem conteúdos pendentes.")
                        );

        inscricaoService.getDev().getConteudosInscritos()
                .remove(conteudo);

        inscricaoService.getDev().getConteudosConcluidos()
                .add(conteudo);

    }
}
