# Bootcamp Java - Desafio de Programação Orientada a Objetos

Projeto desenvolvido em Java com foco na aplicação dos principais conceitos de Programação Orientada a Objetos (POO), simulando uma plataforma de cursos online onde desenvolvedores podem se inscrever em bootcamps, consumir conteúdos e acumular experiência (XP).

## Objetivos

Este projeto demonstra a utilização dos pilares da Programação Orientada a Objetos:

- Abstração
- Encapsulamento
- Herança
- Polimorfismo

Além disso, aplica boas práticas de desenvolvimento Java, como:

- Imutabilidade de objetos
- Encapsulamento de coleções
- Validação de regras de negócio
- Uso de Streams
- Implementação adequada de `equals()` e `hashCode()`
- Modelagem orientada ao domínio

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
                 | progredir()    |
                 | calcularXp()   |
                 +----------------+
```

---

## Estrutura do Projeto

```text
src
└── main
    └── java
        └── br
            └── com
                └── bootcamp
                    └── domain
                        ├── Conteudo.java
                        ├── Curso.java
                        ├── Mentoria.java
                        ├── Bootcamp.java
                        ├── Dev.java
                        └── Main.java
```

---

## Classes do Projeto

### Conteudo

Classe abstrata responsável por representar qualquer conteúdo disponível no bootcamp.

#### Atributos

| Campo | Tipo |
|---------|---------|
| titulo | String |
| descricao | String |

#### Métodos

```java
public abstract double calcularXp();
```

---

### Curso

Representa um curso disponível na plataforma.

#### Atributos

| Campo | Tipo |
|---------|---------|
| cargaHoraria | int |

#### Regra de XP

```java
XP_PADRAO * cargaHoraria
```

Exemplo:

```text
Curso com 8 horas

XP = 10 * 8
XP = 80
```

---

### Mentoria

Representa uma sessão de mentoria.

#### Atributos

| Campo | Tipo |
|---------|---------|
| data | LocalDate |

#### Regra de XP

```java
XP_PADRAO + BONUS_XP
```

Exemplo:

```text
XP = 10 + 20
XP = 30
```

---

### Bootcamp

Agrupa conteúdos e desenvolvedores inscritos.

#### Responsabilidades

- Armazenar conteúdos
- Armazenar desenvolvedores inscritos
- Definir período de duração do bootcamp

#### Métodos principais

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

### Dev

Representa um aluno da plataforma.

#### Responsabilidades

- Inscrever-se em bootcamps
- Progredir nos conteúdos
- Calcular XP acumulado

#### Métodos principais

```java
inscreverBootcamp(Bootcamp bootcamp)
```

```java
progredir()
```

```java
calcularTotalXp()
```

---

## Fluxo de Funcionamento

### 1. Criar conteúdos

```java
Curso java = new Curso(
    "Java Básico",
    "Fundamentos da linguagem",
    8
);

Mentoria mentoria = new Mentoria(
    "Mentoria Java",
    "Sessão de dúvidas",
    LocalDate.now()
);
```

### 2. Criar um bootcamp

```java
Bootcamp bootcamp = new Bootcamp(
    "Bootcamp Java",
    "Formação Backend"
);
```

### 3. Adicionar conteúdos

```java
bootcamp.adicionarConteudo(java);
bootcamp.adicionarConteudo(mentoria);
```

### 4. Inscrever um desenvolvedor

```java
Dev lucas = new Dev("Lucas");

lucas.inscreverBootcamp(bootcamp);
```

### 5. Progredir nos estudos

```java
lucas.progredir();
lucas.progredir();
```

### 6. Consultar XP

```java
System.out.println(
    lucas.calcularTotalXp()
);
```

---

## Exemplo Completo

```java
public class Main {

    public static void main(String[] args) {

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

        Bootcamp bootcamp = new Bootcamp(
                "Bootcamp Java Backend",
                "Formação completa"
        );

        bootcamp.adicionarConteudo(java);
        bootcamp.adicionarConteudo(spring);
        bootcamp.adicionarConteudo(mentoria);

        Dev lucas = new Dev("Lucas");

        lucas.inscreverBootcamp(bootcamp);

        lucas.progredir();
        lucas.progredir();

        System.out.println(lucas);

        System.out.println(
                "XP Total: " + lucas.calcularTotalXp()
        );
    }
}
```

---

## Melhorias Aplicadas

Em relação à versão original do desafio, foram realizadas as seguintes melhorias:

### Imutabilidade

- Uso de atributos `final`
- Remoção de setters desnecessários
- Objetos criados sempre em estado válido

### Encapsulamento

- Coleções protegidas com `Collections.unmodifiableSet()`
- Remoção de setters para coleções

### Validações

- Nome obrigatório
- Descrição obrigatória
- Carga horária positiva
- Datas válidas

### Boas práticas

- Uso de Streams
- Constantes para valores fixos
- Implementação adequada de `equals()` e `hashCode()`
- Evita exposição indevida do estado interno dos objetos

### Modelagem de Domínio

- Métodos específicos para regras de negócio
- Redução do acoplamento
- Maior legibilidade e manutenção

---

## Tecnologias Utilizadas

- Java 11 (ou superior)
- Java Collections Framework
- Java Stream API
- Java Time API

---

## Conceitos Demonstrados

- Classes e Objetos
- Herança
- Polimorfismo
- Classes Abstratas
- Encapsulamento
- Imutabilidade
- Collections
- Streams
- Optional
- Sobrescrita de métodos
- Equals e HashCode

---

## Autor

Desenvolvido por Lucas Bezerra.

LinkedIn:

www.linkedin.com/in/lucasbezrr
