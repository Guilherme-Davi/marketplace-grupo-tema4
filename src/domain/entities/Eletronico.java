package domain.entities;

import domain.enums.EstadoItem;

public class Eletronico extends Item {
    private final int garantiaMeses;

    public Eletronico(String titulo, EstadoItem estado, Usuario proprietario, int garantiaMeses) {
        super(titulo, estado, proprietario);
        if (garantiaMeses < 0) throw new IllegalArgumentException("Garantia não pode ser negativa");
        this.garantiaMeses = garantiaMeses;
    }

    @Override public String descricao() { return "Eletrônico: " + getTitulo() + " - Garantia: " + garantiaMeses + " meses"; }
    public int getGarantiaMeses() { return garantiaMeses; }
}
