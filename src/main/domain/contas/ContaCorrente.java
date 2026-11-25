package domain.contas;

import domain.pessoas.Cliente;

/**
 * Conta corrente, usada nas operações bancárias do dia a dia.
 * Possui uma mensalidade fixa (RN6). Depósito e saque seguem exatamente a regra de {@link Conta}.
 */
public class ContaCorrente extends Conta {

    private static final double MENSALIDADE = 20.00;

    /**
     * Cria uma conta corrente com saldo zero.
     *
     * @param cliente titular da conta
     */
    public ContaCorrente(Cliente cliente) {
        super(TipoConta.CORRENTE, cliente);
    }

    /**
     * Retorna o valor da mensalidade da conta corrente.
     *
     * @return o valor da mensalidade da conta corrente
     */
    public double consultarMensalidade() {
        return MENSALIDADE;
    }
}
