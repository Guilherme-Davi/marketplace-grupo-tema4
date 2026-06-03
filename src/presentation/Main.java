package presentation;

import application.CriarPropostaTrocaUseCase;
import domain.entities.*;
import domain.enums.EstadoItem;
import domain.services.ServicoDeMatch;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    private static final Scanner scanner = new Scanner(System.in);
    private static final List<Usuario> usuarios = new ArrayList<>();
    private static final List<PropostaTroca> propostas = new ArrayList<>();
    private static final CriarPropostaTrocaUseCase criarProposta =
            new CriarPropostaTrocaUseCase(new ServicoDeMatch());

    public static void main(String[] args) {
        int opcao;

        do {
            mostrarMenu();
            opcao = lerInteiro("Escolha uma opção: ");

            try {
                switch (opcao) {
                    case 1 -> cadastrarUsuario();
                    case 2 -> cadastrarLivro();
                    case 3 -> cadastrarEletronico();
                    case 4 -> listarUsuarios();
                    case 5 -> listarItens();
                    case 6 -> criarProposta();
                    case 7 -> aceitarProposta();
                    case 8 -> recusarProposta();
                    case 9 -> listarPropostas();
                    case 0 -> System.out.println("Encerrando o sistema...");
                    default -> System.out.println("Opção inválida.");
                }
            } catch (RuntimeException e) {
                System.out.println("Erro: " + e.getMessage());
            }

            System.out.println();

        } while (opcao != 0);
    }

    private static void mostrarMenu() {
        System.out.println("==================================");
        System.out.println(" MARKETPLACE DE ECONOMIA CIRCULAR");
        System.out.println("==================================");
        System.out.println("1 - Cadastrar usuário");
        System.out.println("2 - Cadastrar livro");
        System.out.println("3 - Cadastrar eletrônico");
        System.out.println("4 - Listar usuários");
        System.out.println("5 - Listar itens");
        System.out.println("6 - Criar proposta de troca");
        System.out.println("7 - Aceitar proposta");
        System.out.println("8 - Recusar proposta");
        System.out.println("9 - Listar propostas");
        System.out.println("0 - Sair");
        System.out.println("==================================");
    }

    private static void cadastrarUsuario() {
        String nome = lerTexto("Nome do usuário: ");
        Usuario usuario = new Usuario(nome);
        usuarios.add(usuario);

        System.out.println("Usuário cadastrado com sucesso.");
    }

    private static void cadastrarLivro() {
        Usuario usuario = escolherUsuario("Escolha o dono do livro:");

        String titulo = lerTexto("Título do livro: ");
        String autor = lerTexto("Autor do livro: ");
        EstadoItem estado = escolherEstado();

        Livro livro = new Livro(titulo, estado, usuario, autor);
        usuario.adicionarItem(livro);

        System.out.println("Livro cadastrado com sucesso.");
    }

    private static void cadastrarEletronico() {
        Usuario usuario = escolherUsuario("Escolha o dono do eletrônico:");

        String titulo = lerTexto("Nome do eletrônico: ");
        int garantia = lerInteiro("Garantia em meses: ");
        EstadoItem estado = escolherEstado();

        Eletronico eletronico = new Eletronico(titulo, estado, usuario, garantia);
        usuario.adicionarItem(eletronico);

        System.out.println("Eletrônico cadastrado com sucesso.");
    }

    private static void listarUsuarios() {
        if (usuarios.isEmpty()) {
            System.out.println("Nenhum usuário cadastrado.");
            return;
        }

        for (int i = 0; i < usuarios.size(); i++) {
            Usuario usuario = usuarios.get(i);
            System.out.println(i + " - " + usuario.getNome() + " | Créditos: " + usuario.getCredito());
        }
    }

    private static void listarItens() {
        if (usuarios.isEmpty()) {
            System.out.println("Nenhum usuário cadastrado.");
            return;
        }

        for (Usuario usuario : usuarios) {
            System.out.println("Usuário: " + usuario.getNome());

            if (usuario.getItens().isEmpty()) {
                System.out.println("  Nenhum item cadastrado.");
            } else {
                for (int i = 0; i < usuario.getItens().size(); i++) {
                    Item item = usuario.getItens().get(i);
                    System.out.println("  " + i + " - " + item.descricao()
                            + " | Estado: " + item.getEstado()
                            + " | Disponível: " + item.isDisponivel());
                }
            }
        }
    }

    private static void criarProposta() {
        Usuario usuarioOfertante = escolherUsuario("Escolha o usuário que vai oferecer o item:");
        Item itemOferecido = escolherItem(usuarioOfertante, "Escolha o item oferecido:");

        Usuario usuarioReceptor = escolherUsuario("Escolha o usuário dono do item desejado:");
        Item itemDesejado = escolherItem(usuarioReceptor, "Escolha o item desejado:");

        PropostaTroca proposta = criarProposta.executar(itemOferecido, itemDesejado);
        propostas.add(proposta);

        System.out.println("Proposta criada com sucesso.");
    }

    private static void aceitarProposta() {
        PropostaTroca proposta = escolherProposta();
        proposta.aceitar();

        System.out.println("Proposta aceita com sucesso.");
    }

    private static void recusarProposta() {
        PropostaTroca proposta = escolherProposta();
        proposta.recusar();

        System.out.println("Proposta recusada com sucesso.");
    }

    private static void listarPropostas() {
        if (propostas.isEmpty()) {
            System.out.println("Nenhuma proposta cadastrada.");
            return;
        }

        for (int i = 0; i < propostas.size(); i++) {
            PropostaTroca proposta = propostas.get(i);
            System.out.println(i + " - "
                    + proposta.getItemOferecido().getTitulo()
                    + " por "
                    + proposta.getItemDesejado().getTitulo()
                    + " | Status: "
                    + proposta.getStatus());
        }
    }

    private static Usuario escolherUsuario(String mensagem) {
        if (usuarios.isEmpty()) {
            throw new IllegalStateException("Cadastre um usuário primeiro.");
        }

        System.out.println(mensagem);
        listarUsuarios();

        int indice = lerInteiro("Número do usuário: ");

        if (indice < 0 || indice >= usuarios.size()) {
            throw new IllegalArgumentException("Usuário não encontrado.");
        }

        return usuarios.get(indice);
    }

    private static Item escolherItem(Usuario usuario, String mensagem) {
        if (usuario.getItens().isEmpty()) {
            throw new IllegalStateException("Este usuário não possui itens cadastrados.");
        }

        System.out.println(mensagem);

        for (int i = 0; i < usuario.getItens().size(); i++) {
            Item item = usuario.getItens().get(i);
            System.out.println(i + " - " + item.descricao() + " | Disponível: " + item.isDisponivel());
        }

        int indice = lerInteiro("Número do item: ");

        return usuario.buscarItemPorIndice(indice);
    }

    private static PropostaTroca escolherProposta() {
        if (propostas.isEmpty()) {
            throw new IllegalStateException("Nenhuma proposta cadastrada.");
        }

        listarPropostas();

        int indice = lerInteiro("Número da proposta: ");

        if (indice < 0 || indice >= propostas.size()) {
            throw new IllegalArgumentException("Proposta não encontrada.");
        }

        return propostas.get(indice);
    }

    private static EstadoItem escolherEstado() {
        System.out.println("Estado do item:");
        System.out.println("1 - NOVO");
        System.out.println("2 - SEMINOVO");
        System.out.println("3 - USADO");

        int opcao = lerInteiro("Escolha: ");

        return switch (opcao) {
            case 1 -> EstadoItem.NOVO;
            case 2 -> EstadoItem.SEMINOVO;
            case 3 -> EstadoItem.USADO;
            default -> throw new IllegalArgumentException("Estado inválido.");
        };
    }

    private static String lerTexto(String mensagem) {
        System.out.print(mensagem);
        return scanner.nextLine();
    }

    private static int lerInteiro(String mensagem) {
        while (true) {
            try {
                System.out.print(mensagem);
                return Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Digite um número válido.");
            }
        }
    }
}