package domain.entities;

import domain.enums.StatusProposta;
import java.util.*;

public class PropostaTroca {
    private final UUID id;
    private final Item itemOferecido;
    private final Item itemDesejado;
    private StatusProposta status;

    public PropostaTroca(Item itemOferecido, Item itemDesejado) {
        if (itemOferecido == null || itemDesejado == null) throw new IllegalArgumentException("Itens da proposta são obrigatórios");
        if (itemOferecido.equals(itemDesejado)) throw new IllegalArgumentException("Não é possível trocar o item por ele mesmo");
        if (itemOferecido.getProprietario().equals(itemDesejado.getProprietario())) throw new IllegalArgumentException("Não pode trocar com você mesmo");
        if (!itemOferecido.isDisponivel() || !itemDesejado.isDisponivel()) throw new IllegalStateException("Itens precisam estar disponíveis para troca");
        this.id = UUID.randomUUID();
        this.itemOferecido = itemOferecido;
        this.itemDesejado = itemDesejado;
        this.status = StatusProposta.PENDENTE;
    }

    public void aceitar() {
        validarPropostaPendente();
        if (!itemOferecido.isDisponivel() || !itemDesejado.isDisponivel()) throw new IllegalStateException("Item indisponível");
        itemOferecido.marcarIndisponivel();
        itemDesejado.marcarIndisponivel();
        status = StatusProposta.ACEITA;
    }

    public void recusar() {
        validarPropostaPendente();
        status = StatusProposta.RECUSADA;
    }

    private void validarPropostaPendente() {
        if (status != StatusProposta.PENDENTE) throw new IllegalStateException("Proposta já finalizada");
    }

    public UUID getId() { return id; }
    public Item getItemOferecido() { return itemOferecido; }
    public Item getItemDesejado() { return itemDesejado; }
    public StatusProposta getStatus() { return status; }
    public String resumo() { return itemOferecido.getTitulo() + " por " + itemDesejado.getTitulo() + " - Status: " + status; }

    @Override public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PropostaTroca that)) return false;
        return Objects.equals(id, that.id);
    }
    @Override public int hashCode() { return Objects.hash(id); }
}
