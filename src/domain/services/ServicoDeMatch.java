package domain.services;

import domain.entities.Item;

public class ServicoDeMatch {
    public boolean podeSugerirTroca(Item itemOrigem, Item itemDestino) {
        if (itemOrigem == null || itemDestino == null) return false;
        if (!itemOrigem.isDisponivel() || !itemDestino.isDisponivel()) return false;
        return !itemOrigem.getProprietario().equals(itemDestino.getProprietario());
    }
}
