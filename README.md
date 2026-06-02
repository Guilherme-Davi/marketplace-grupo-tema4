# Marketplace de Economia Circular - Tema 4

## Descrição

Este projeto representa o núcleo de domínio de um marketplace de economia circular. A proposta é permitir que usuários cadastrem itens que não utilizam mais, como livros e eletrônicos, e realizem trocas com outros usuários por meio de propostas de negociação.

O foco principal do sistema está na implementação das regras de negócio utilizando Programação Orientada a Objetos, Domain-Driven Design (DDD), testes automatizados com JUnit e integração contínua com GitHub Actions.

## Tema Escolhido

**Tema 4 – Marketplace de Economia Circular (Troca de Objetos e Livros)**

## Tecnologias Utilizadas

* Java 17
* Maven
* JUnit 5
* GitHub Actions

## Estrutura do Projeto

```txt
.github/
└── workflows/
    └── ci.yml

src/
├── domain/
│   ├── entities/
│   ├── enums/
│   ├── services/
│   └── valueobjects/
├── application/
├── infrastructure/
└── presentation/

tests/
├── application/
└── domain/
```

## Modelagem do Domínio

### Entidades

* Usuario
* Item
* Livro
* Eletronico
* PropostaTroca

### Value Object

* Credito

A classe `Credito` representa o saldo de créditos do usuário. Por ser imutável, qualquer alteração de valor gera uma nova instância do objeto.

### Aggregate Root

* Usuario

O usuário foi definido como raiz do agregado por ser responsável pelo gerenciamento dos itens cadastrados e dos créditos associados à sua conta.

### Serviço de Domínio

* ServicoDeMatch

Responsável por verificar se dois itens podem participar de uma proposta de troca.

### Caso de Uso

* CriarPropostaTrocaUseCase

Responsável pela criação de propostas de troca após a validação das regras de negócio.

## Regras de Negócio Implementadas

* Usuário não pode ser criado sem nome.
* Item não pode ser criado sem título, estado ou proprietário.
* Livro deve possuir autor informado.
* Eletrônico não pode possuir garantia negativa.
* Crédito não pode possuir valor negativo.
* Não é permitido remover créditos acima do saldo disponível.
* Usuário só pode cadastrar itens de sua propriedade.
* Usuário não pode criar proposta utilizando item próprio.
* Toda proposta inicia com status PENDENTE.
* Uma proposta pode ser ACEITA ou RECUSADA.
* Propostas finalizadas não podem ser alteradas.
* Ao aceitar uma proposta, os itens envolvidos tornam-se indisponíveis.

## Como Executar os Testes

```bash
mvn clean test
```

## Como Executar o Projeto

```bash
mvn clean compile
java -cp target/classes presentation.Main
```

Também é possível executar a classe `presentation.Main` diretamente pela IDE.

## Organização do Desenvolvimento

O desenvolvimento foi dividido em branches para facilitar a colaboração entre os membros do grupo:

* feature/usuario-creditos
* feature/itens
* feature/propostas

Cada branch ficou responsável por uma parte específica do domínio, com commits separados para testes, implementação e ajustes de código.
