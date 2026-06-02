package domain.valueobjects;

import java.util.Objects;

public final class Credito {
    private final int valor;

    public Credito(int valor) {
        if (valor < 0) throw new IllegalArgumentException("Crédito não pode ser negativo");
        this.valor = valor;
    }

    public int getValor() { return valor; }

    public Credito adicionar(int quantidade) {
        if (quantidade < 0) throw new IllegalArgumentException("Quantidade para adicionar não pode ser negativa");
        return new Credito(valor + quantidade);
    }

    public Credito remover(int quantidade) {
        if (quantidade < 0) throw new IllegalArgumentException("Quantidade para remover não pode ser negativa");
        if (valor - quantidade < 0) throw new IllegalArgumentException("Saldo insuficiente");
        return new Credito(valor - quantidade);
    }

    @Override public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Credito credito)) return false;
        return valor == credito.valor;
    }
    @Override public int hashCode() { return Objects.hash(valor); }
    @Override public String toString() { return valor + " créditos"; }
}
