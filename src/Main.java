import br.com.dio.desafio.dominio.Bootcamp;
import br.com.dio.desafio.dominio.Curso;
import br.com.dio.desafio.dominio.Dev;
import br.com.dio.desafio.dominio.Mentoria;
import br.com.dio.desafio.service.InscricaoService;
import br.com.dio.desafio.service.ProgressaoService;

import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {
        Curso curso1 = new Curso("curso java", "descrição curso java", 8);

        Curso curso2 = new Curso("curso js", "descrição curso js", 4);

        Mentoria mentoria = new Mentoria("mentoria de java", "descrição mentoria java", LocalDate.now());

        /*System.out.println(curso1);
        System.out.println(curso2);
        System.out.println(mentoria);*/

        Bootcamp bootcamp = new Bootcamp("Bootcamp Java Developer", "Descrição Bootcamp Java Developer");
        bootcamp.adicionarConteudo(curso1);
        bootcamp.adicionarConteudo(curso2);
        bootcamp.adicionarConteudo(mentoria);

        Dev devCamila = new Dev("Camila");
        InscricaoService inscricaoCamila = new InscricaoService(devCamila);
        inscricaoCamila.inscrever(bootcamp);
        System.out.println("Conteúdos Inscritos Camila:" + devCamila.getConteudosInscritos());
        ProgressaoService progressaoCamila = new ProgressaoService(inscricaoCamila);
        progressaoCamila.progredir();
        progressaoCamila.progredir();
        System.out.println("-");
        System.out.println("Conteúdos Inscritos Camila:" + devCamila.getConteudosInscritos());
        System.out.println("Conteúdos Concluídos Camila:" + devCamila.getConteudosConcluidos());
        System.out.println("XP:" + devCamila.calcularTotalXp());
        System.out.println(devCamila);

        System.out.println("-------");

        Dev devJoao = new Dev("Joao");
        InscricaoService inscricaoJoao = new InscricaoService(devJoao);
        inscricaoJoao.inscrever(bootcamp);
        System.out.println("Conteúdos Inscritos João:" + devJoao.getConteudosInscritos());
        ProgressaoService progressaoJoao = new ProgressaoService(inscricaoJoao);
        progressaoJoao.progredir();
        progressaoJoao.progredir();
        progressaoJoao.progredir();
        System.out.println("-");
        System.out.println("Conteúdos Inscritos João:" + devJoao.getConteudosInscritos());
        System.out.println("Conteúdos Concluidos João:" + devJoao.getConteudosConcluidos());
        System.out.println("XP:" + devJoao.calcularTotalXp());

    }

}
