# POO PBL - Tema 4: Marketplace de Economia Circular

## Descrição

Este projeto implementa o núcleo de domínio de um marketplace de economia circular, no qual usuários cadastram itens que não usam mais e podem propor trocas baseadas em match de interesses e sistema de créditos.

O projeto foi desenvolvido para a atividade **Projeto Prático Integrador: Engenharia de Software e Orientação a Objetos**, utilizando Orientação a Objetos, DDD, testes automatizados com JUnit e CI/CD com GitHub Actions.

## Tema escolhido

**Tema 4 - Marketplace de Economia Circular (Troca de Objetos e Livros)**

## Tecnologias

- Java 17
- Maven
- JUnit 5
- GitHub Actions

## Estrutura do projeto

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

## Conceitos de DDD aplicados

### Entidades

- `Usuario`
- `Item`
- `Livro`
- `Eletronico`
- `PropostaTroca`

### Value Object

- `Credito`

A classe `Credito` é imutável e não possui identidade própria. Ela representa apenas um valor dentro do domínio.

### Aggregate Root

- `Usuario`

O usuário é tratado como raiz de agregado porque controla seus próprios itens cadastrados e sua carteira de créditos.

### Serviço de Domínio

- `ServicoDeMatch`

Responsável por validar se dois itens podem gerar uma proposta de troca.

### Camada de Aplicação

- `CriarPropostaTrocaUseCase`

Representa o caso de uso para criar uma proposta de troca respeitando as regras de domínio.

## Regras de negócio implementadas

- Usuário não pode ser criado sem nome.
- Item não pode ser criado sem título, estado ou proprietário.
- Livro não pode ser criado sem autor.
- Eletrônico não pode ter garantia negativa.
- Crédito não pode ser negativo.
- Não é permitido remover créditos acima do saldo disponível.
- Usuário só pode cadastrar item que pertence a ele.
- Usuário não pode propor troca com o próprio item.
- Proposta de troca começa com status `PENDENTE`.
- Proposta aceita muda para `ACEITA`.
- Proposta recusada muda para `RECUSADA`.
- Proposta finalizada não pode ser alterada.
- Ao aceitar uma proposta, os dois itens ficam indisponíveis.

## Como executar os testes

```bash
mvn clean test
```

## Como executar a demonstração

```bash
mvn clean compile
java -cp target/classes presentation.Main
```

Ou execute a classe `presentation.Main` pela IDE.

## Fluxo de trabalho sugerido

```bash
feature/itens
feature/usuario-creditos
feature/propostas
```

Cada integrante deve fazer commits separados, preferencialmente seguindo:

```txt
test: cria teste da regra
feat: implementa regra
refactor: melhora código
```
