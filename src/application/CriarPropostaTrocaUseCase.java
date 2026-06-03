package application;

import domain.entities.Item;
import domain.entities.PropostaTroca;
import domain.services.ServicoDeMatch;

public class CriarPropostaTrocaUseCase {
    private final ServicoDeMatch servicoDeMatch;

    public CriarPropostaTrocaUseCase(ServicoDeMatch servicoDeMatch) {
        if (servicoDeMatch == null) throw new IllegalArgumentException("Serviço de match é obrigatório");
        this.servicoDeMatch = servicoDeMatch;
    }

    public PropostaTroca executar(Item itemOferecido, Item itemDesejado) {
        if (!servicoDeMatch.podeSugerirTroca(itemOferecido, itemDesejado)) throw new IllegalArgumentException("Troca não permitida");
        return new PropostaTroca(itemOferecido, itemDesejado);
    }
}
