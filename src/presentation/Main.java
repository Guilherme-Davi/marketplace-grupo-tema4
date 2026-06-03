package presentation;

import domain.entities.Eletronico;
import domain.entities.Livro;
import domain.entities.PropostaTroca;
import domain.entities.Usuario;
import domain.enums.EstadoItem;

public class Main {

    public static void main(String[] args) {

        Usuario guilherme = new Usuario("Guilherme");
        Usuario artur = new Usuario("Artur");

        Livro livro = new Livro(
                "Clean Code",
                EstadoItem.USADO,
                guilherme,
                "Robert C. Martin"
        );

        Eletronico fone = new Eletronico(
                "Fone Bluetooth",
                EstadoItem.SEMINOVO,
                artur,
                12
        );

        PropostaTroca proposta = new PropostaTroca(livro, fone);

        System.out.println("=== Marketplace de Economia Circular ===");
        System.out.println();

        System.out.println("Usuário 1: " + guilherme.getNome());
        System.out.println("Usuário 2: " + artur.getNome());

        System.out.println();
        System.out.println("Livro cadastrado: " + livro.getTitulo());
        System.out.println("Eletrônico cadastrado: " + fone.getTitulo());

        System.out.println();
        System.out.println("Status inicial da proposta: " + proposta.getStatus());

        proposta.aceitar();

        System.out.println("Status após aceite: " + proposta.getStatus());
        System.out.println("Livro disponível: " + livro.isDisponivel());
        System.out.println("Eletrônico disponível: " + fone.isDisponivel());
    }
}
