// Classe base para os itens negociados
package domain.entities;

import domain.enums.EstadoItem;
import java.util.*;

public abstract class Item {
    private final UUID id;
    private final String titulo;
    private final EstadoItem estado;
    private final Usuario proprietario;
    private boolean disponivel;

    public Item(String titulo, EstadoItem estado, Usuario proprietario) {
        if (titulo == null || titulo.isBlank()) throw new IllegalArgumentException("Título do item é obrigatório");
        if (estado == null) throw new IllegalArgumentException("Estado do item é obrigatório");
        if (proprietario == null) throw new IllegalArgumentException("Proprietário do item é obrigatório");
        this.id = UUID.randomUUID();
        this.titulo = titulo;
        this.estado = estado;
        this.proprietario = proprietario;
        this.disponivel = true;
    }

    public abstract String descricao();

    public void marcarIndisponivel() {
        if (!disponivel) throw new IllegalStateException("Item já está indisponível");
        disponivel = false;
    }

    public UUID getId() { return id; }
    public String getTitulo() { return titulo; }
    public EstadoItem getEstado() { return estado; }
    public Usuario getProprietario() { return proprietario; }
    public boolean isDisponivel() { return disponivel; }

    @Override public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Item item)) return false;
        return Objects.equals(id, item.id);
    }
    @Override public int hashCode() { return Objects.hash(id); }
}
