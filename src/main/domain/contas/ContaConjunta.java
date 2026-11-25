package domain.contas;

import domain.pessoas.Cliente;

/**
 * Conta compartilhada entre um titular e um cotitular, que usam o mesmo saldo (RN5).
 * Depósito e saque seguem exatamente a regra de {@link Conta}.
 */
public class ContaConjunta extends Conta {

    private final Cliente cotitular;

    /**
     * Cria uma conta conjunta com saldo zero.
     *
     * @param titular   titular principal
     * @param cotitular segundo titular; deve ser diferente do titular
     * @throws IllegalArgumentException se o cotitular for nulo ou for a mesma pessoa do titular
     */
    public ContaConjunta(Cliente titular, Cliente cotitular) {
        super(TipoConta.CONJUNTA, titular);
        if (cotitular == null) {
            throw new IllegalArgumentException("O cotitular é obrigatório.");
        }
        if (cotitular == titular || cotitular.getCpf().equals(titular.getCpf())) {
            throw new IllegalArgumentException("O cotitular deve ser diferente do titular.");
        }
        this.cotitular = cotitular;
    }

    /**
     * Retorna o cotitular da conta.
     *
     * @return o cotitular da conta
     */
    public Cliente getCotitular() {
        return cotitular;
    }

    /**
     * Retorna descrição resumida da conta, incluindo o cotitular.
     *
     * @return descrição resumida da conta, incluindo o cotitular
     */
    @Override
    public String toString() {
        return super.toString() + " - Cotitular: " + cotitular.getNome();
    }
}