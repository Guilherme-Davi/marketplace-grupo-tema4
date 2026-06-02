package domain.entities;

import domain.enums.EstadoItem;

public class Livro extends Item {
    private final String autor;

    public Livro(String titulo, EstadoItem estado, Usuario proprietario, String autor) {
        super(titulo, estado, proprietario);
        if (autor == null || autor.isBlank()) throw new IllegalArgumentException("Autor do livro é obrigatório");
        this.autor = autor;
    }

    @Override public String descricao() { return "Livro: " + getTitulo() + " - Autor: " + autor; }
    public String getAutor() { return autor; }
}
