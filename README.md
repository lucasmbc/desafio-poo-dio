# Bootcamp Java - Programação Orientada a Objetos

Projeto desenvolvido em Java com foco na aplicação dos principais conceitos de Programação Orientada a Objetos (POO), simulando uma plataforma de ensino onde desenvolvedores podem se inscrever em bootcamps, consumir conteúdos e acumular experiência (XP).

Além dos conceitos fundamentais de POO, o projeto foi refatorado seguindo boas práticas de desenvolvimento, incluindo separação de responsabilidades, encapsulamento, imutabilidade e tratamento de exceções customizadas.

---

## Objetivos

Este projeto demonstra a utilização dos pilares da Programação Orientada a Objetos:

- Abstração
- Encapsulamento
- Herança
- Polimorfismo

Além disso, aplica conceitos frequentemente utilizados em projetos Java profissionais:

- Imutabilidade
- Encapsulamento de coleções
- Separação de responsabilidades (SRP)
- Camada de serviços
- Exceções customizadas
- Streams API
- Collections Framework
- Modelagem de domínio
- Boas práticas de orientação a objetos

---

## Estrutura do Projeto

```text
src
└── main
    └── java
        └── br
            └── com
                └── bootcamp
                    ├── domain
                    │   ├── Conteudo.java
                    │   ├── Curso.java
                    │   ├── Mentoria.java
                    │   ├── Bootcamp.java
                    │   └── Dev.java
                    │
                    ├── service
                    │   ├── InscricaoService.java
                    │   └── ProgressaoService.java
                    │
                    ├── exception
                    │   ├── ConteudoNaoEncontradoException.java
                    │   └── ProgressaoInvalidaException.java
                    │
                    └── Main.java
```

---

## Arquitetura

O projeto foi organizado em camadas simples para separar responsabilidades:

### Domain

Responsável por representar as entidades e regras básicas do domínio.

```text
Conteudo
Curso
Mentoria
Bootcamp
Dev
```

### Service

Responsável por executar regras de negócio e orquestrar operações entre entidades.

```text
InscricaoService
ProgressaoService
```

### Exception

Responsável pelo tratamento de erros de negócio através de exceções customizadas.

```text
ConteudoNaoEncontradoException
ProgressaoInvalidaException
```

---

## Diagrama de Classes

```text
                 +----------------+
                 |   Conteudo     |
                 +----------------+
                 | titulo         |
                 | descricao      |
                 +----------------+
                 | calcularXp()   |
                 +----------------+
                        ▲
          ┌─────────────┴─────────────┐
          │                           │
          │                           │
+-------------------+      +-------------------+
|      Curso        |      |     Mentoria      |
+-------------------+      +-------------------+
| cargaHoraria      |      | data              |
+-------------------+      +-------------------+
| calcularXp()      |      | calcularXp()      |
+-------------------+      +-------------------+

                 +----------------+
                 |    Bootcamp    |
                 +----------------+
                 | nome           |
                 | descricao      |
                 | dataInicial    |
                 | dataFinal      |
                 +----------------+
                 | conteudos      |
                 | devsInscritos  |
                 +----------------+

                 +----------------+
                 |      Dev       |
                 +----------------+
                 | nome           |
                 +----------------+
                 | inscritos      |
                 | concluidos     |
                 +----------------+

        +--------------------------+
        |    InscricaoService      |
        +--------------------------+
        | inscrever()              |
        +--------------------------+

        +--------------------------+
        |    ProgressaoService     |
        +--------------------------+
        | progredir()              |
        +--------------------------+
```

---

# Entidades

## Conteudo

Classe abstrata que representa qualquer conteúdo disponível no sistema.

### Atributos

| Campo | Tipo |
|---------|---------|
| titulo | String |
| descricao | String |

### Método principal

```java
public abstract double calcularXp();
```

---

## Curso

Representa um curso disponível dentro de um bootcamp.

### Atributos

| Campo | Tipo |
|---------|---------|
| cargaHoraria | int |

### Regra de XP

```java
XP_PADRAO * cargaHoraria
```

Exemplo:

```text
Curso de 8 horas

XP = 10 * 8
XP = 80
```

---

## Mentoria

Representa uma sessão de mentoria.

### Atributos

| Campo | Tipo |
|---------|---------|
| data | LocalDate |

### Regra de XP

```java
XP_PADRAO + BONUS_XP
```

Exemplo:

```text
XP = 10 + 20
XP = 30
```

---

## Bootcamp

Agrupa conteúdos e desenvolvedores inscritos.

### Responsabilidades

- Armazenar conteúdos
- Armazenar desenvolvedores inscritos
- Definir período do bootcamp

### Métodos principais

```java
adicionarConteudo(Conteudo conteudo)
```

```java
getConteudos()
```

```java
getDevsInscritos()
```

---

## Dev

Representa um aluno da plataforma.

### Responsabilidades

- Possuir conteúdos inscritos
- Possuir conteúdos concluídos
- Calcular XP acumulado

### Método principal

```java
calcularTotalXp()
```

---

# Camada de Serviços

## InscricaoService

Responsável por realizar a inscrição de um desenvolvedor em um bootcamp.

### Método

```java
public void inscrever(
        Dev dev,
        Bootcamp bootcamp
)
```

### Fluxo

1. Obtém todos os conteúdos do bootcamp.
2. Adiciona os conteúdos ao desenvolvedor.
3. Registra o desenvolvedor como inscrito no bootcamp.

---

## ProgressaoService

Responsável pela progressão do desenvolvedor.

### Método

```java
public void progredir(
        Dev dev
)
```

### Fluxo

1. Obtém o próximo conteúdo disponível.
2. Remove da lista de conteúdos inscritos.
3. Adiciona na lista de conteúdos concluídos.
4. Atualiza o XP acumulado.

---

# Exceções Customizadas

## ProgressaoInvalidaException

Lançada quando um desenvolvedor tenta progredir sem possuir conteúdos pendentes.

Exemplo:

```java
throw new ProgressaoInvalidaException(
    "Não existem conteúdos pendentes."
);
```

---

## ConteudoNaoEncontradoException

Pode ser utilizada em futuras implementações quando um conteúdo não for encontrado durante operações do sistema.

Exemplo:

```java
throw new ConteudoNaoEncontradoException(
    "Conteúdo não encontrado."
);
```

---

# Fluxo de Utilização

## Criando conteúdos

```java
Curso java = new Curso(
        "Java Básico",
        "Fundamentos da linguagem",
        8
);

Curso spring = new Curso(
        "Spring Boot",
        "APIs REST",
        10
);

Mentoria mentoria = new Mentoria(
        "Mentoria Java",
        "Tira dúvidas",
        LocalDate.now()
);
```

---

## Criando um bootcamp

```java
Bootcamp bootcamp = new Bootcamp(
        "Bootcamp Java Backend",
        "Formação completa"
);
```

---

## Adicionando conteúdos

```java
bootcamp.adicionarConteudo(java);
bootcamp.adicionarConteudo(spring);
bootcamp.adicionarConteudo(mentoria);
```

---

## Criando um desenvolvedor

```java
Dev lucas = new Dev("Lucas");
```

---

## Realizando inscrição

```java
InscricaoService inscricaoService =
        new InscricaoService();

inscricaoService.inscrever(
        lucas,
        bootcamp
);
```

---

## Progredindo nos conteúdos

```java
ProgressaoService progressaoService =
        new ProgressaoService();

progressaoService.progredir(lucas);
progressaoService.progredir(lucas);
```

---

## Consultando XP

```java
System.out.println(
        lucas.calcularTotalXp()
);
```

---

# Melhorias Aplicadas

## Estrutura em Camadas

Separação das responsabilidades entre:

- Entidades
- Serviços
- Exceções

---

## Imutabilidade

Uso de atributos `final` sempre que possível.

Exemplo:

```java
private final String nome;
```

---

## Encapsulamento

Coleções protegidas através de:

```java
Collections.unmodifiableSet(...)
```

---

## Validações

Validação de:

- Nome
- Descrição
- Carga horária
- Datas obrigatórias

---

## Tratamento de Erros

Substituição de mensagens em console por exceções customizadas.

Antes:

```java
System.err.println(
    "Você não está matriculado."
);
```

Depois:

```java
throw new ProgressaoInvalidaException(
    "Não existem conteúdos pendentes."
);
```

---

## Streams API

Cálculo de XP utilizando Stream API.

```java
return conteudosConcluidos
        .stream()
        .mapToDouble(Conteudo::calcularXp)
        .sum();
```

---

## Boas Práticas de POO

- Classes imutáveis
- Responsabilidade única (SRP)
- Baixo acoplamento
- Alta coesão
- Encapsulamento
- Herança
- Polimorfismo

---

# Tecnologias Utilizadas

- Java 21+
- Java Collections Framework
- Java Stream API
- Java Time API

---

# Conceitos Demonstrados

- Classes e Objetos
- Herança
- Polimorfismo
- Classes Abstratas
- Encapsulamento
- Imutabilidade
- Streams
- Collections
- Optional
- Exceções Customizadas
- Equals e HashCode
- Separação de Responsabilidades

---

# Possíveis Evoluções

O projeto pode ser expandido futuramente com:

- Persistência com JPA/Hibernate
- API REST com Spring Boot
- DTOs
- Banco de dados PostgreSQL
- Testes unitários com JUnit 5
- Testcontainers
- Docker
- Spring Security e JWT
- Arquitetura Hexagonal
- Domain Driven Design (DDD)

---

# Autor

**Lucas Bezerra**

LinkedIn:

www.linkedin.com/in/lucasbezrr