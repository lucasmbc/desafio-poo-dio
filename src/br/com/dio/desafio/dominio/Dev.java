package br.com.dio.desafio.dominio;

import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Set;

public class Dev {

    private final String nome;

    private final Set<Conteudo> conteudosInscritos = new LinkedHashSet<>();
    private final Set<Conteudo> conteudosConcluidos = new LinkedHashSet<>();

    public Dev(String nome) {
        if(nome == null || nome.isBlank()) {
            throw new IllegalArgumentException("Nome é obrigatório.");
        }

        this.nome = nome;
    }

    public void inscreverBootcamp(Bootcamp bootcamp){

        Objects.requireNonNull(
                bootcamp,
                "Bootcamp não pode ser nulo."
        );

        this.conteudosInscritos.addAll(bootcamp.getConteudos());

        bootcamp.adicionarDev(this);
    }

    public boolean progredir() {
        var proximoConteudo = conteudosInscritos.stream().findFirst();

        if(proximoConteudo.isEmpty()) {
            return false;
        }

        Conteudo conteudo = proximoConteudo.get();

        conteudosInscritos.remove(conteudo);
        conteudosConcluidos.add(conteudo);

        return true;
    }

    public double calcularTotalXp() {
        return this.conteudosConcluidos
                .stream()
                .mapToDouble(Conteudo::calcularXp)
                .sum();
    }

    public String getNome() {
        return nome;
    }

    public Set<Conteudo> getConteudosInscritos() {
        return Collections.unmodifiableSet(conteudosInscritos);
    }

    public Set<Conteudo> getConteudosConcluidos() {
        return Collections.unmodifiableSet(conteudosConcluidos);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Dev dev = (Dev) o;
        return Objects.equals(nome, dev.nome) && Objects.equals(conteudosInscritos, dev.conteudosInscritos) && Objects.equals(conteudosConcluidos, dev.conteudosConcluidos);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nome, conteudosInscritos, conteudosConcluidos);
    }

    @Override
    public String toString() {
        return "Dev{" +
                "nome='" + nome + '\'' +
                ", conteúdos inscritos=" + conteudosInscritos.size() +
                ", conteúdos concluídos=" + conteudosConcluidos.size() +
                ", xp=" + calcularTotalXp() +
                '}';
    }
}
