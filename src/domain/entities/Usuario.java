package domain.entities;

import domain.valueobjects.Credito;
import java.util.*;

public class Usuario {
    private final UUID id;
    private final String nome;
    private Credito credito;
    private final List<Item> itens;

    public Usuario(String nome) {
        if (nome == null || nome.isBlank()) throw new IllegalArgumentException("Nome do usuário é obrigatório");
        this.id = UUID.randomUUID();
        this.nome = nome;
        this.credito = new Credito(0);
        this.itens = new ArrayList<>();
    }

    public void adicionarItem(Item item) {
        if (item == null) throw new IllegalArgumentException("Item não pode ser nulo");
        if (!item.getProprietario().equals(this)) throw new IllegalArgumentException("Item pertence a outro usuário");
        itens.add(item);
    }

    public void adicionarCreditos(int quantidade) { credito = credito.adicionar(quantidade); }
    public void removerCreditos(int quantidade) { credito = credito.remover(quantidade); }
    public UUID getId() { return id; }
    public String getNome() { return nome; }
    public Credito getCredito() { return credito; }
    public List<Item> getItens() { return Collections.unmodifiableList(itens); }

    public Item buscarItemPorIndice(int indice) {
        if (indice < 0 || indice >= itens.size()) throw new IllegalArgumentException("Item não encontrado");
        return itens.get(indice);
    }

    @Override public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Usuario usuario)) return false;
        return Objects.equals(id, usuario.id);
    }
    @Override public int hashCode() { return Objects.hash(id); }
}
