package Contas;
import Pessoas.Cliente;

public class ContaConjunta extends Conta {

    private Cliente cotitular;

    public ContaConjunta(Cliente titular, Cliente cotitular) {
        super(TipoConta.CONJUNTA, titular);
        this.cotitular = cotitular;
    }

    public Cliente getCotitular() {
        return cotitular;
    }

    @Override
    public String toString() {
        return "Titular: " + getTitular().getNome() + " e Cotitular: " + cotitular.getNome();
    }
}
