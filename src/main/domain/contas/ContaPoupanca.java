package domain.contas;

import domain.pessoas.Cliente;

/**
 * Conta poupança, que rende 0,5% sobre o saldo (RN4).
 * Depósito e saque seguem exatamente a regra de {@link Conta}.
 */
public class ContaPoupanca extends Conta {

    private static final double TAXA_RENDIMENTO = 0.5;

    /**
     * Cria uma conta poupança com saldo zero.
     *
     * @param cliente titular da conta
     */
    public ContaPoupanca(Cliente cliente) {
        super(TipoConta.POUPANCA, cliente);
    }

    /**
     * Calcula o rendimento sobre o saldo atual e o deposita na conta.
     * Se o saldo for zero, não há rendimento e nada é feito (sem lançar erro).
     */
    public void aplicarRendimento() {
        double rendimento = getSaldo() * TAXA_RENDIMENTO / 100;
        if (rendimento > 0) {
            efetuarDeposito(rendimento);
        }
    }

    /**
     * Retorna a taxa de rendimento em porcentagem (0,5).
     *
     * @return a taxa de rendimento em porcentagem (0,5)
     */
    public double consultarRendimento() {
        return TAXA_RENDIMENTO;
    }
}