package Contas;
import Pessoas.Cliente;

/**
 * Conta corrente, usada ns operações bancárias do dia a dia.
 * Possui uma mensalidade fixa (RN6). Depósito e saque seguem exatamente a regra de {@link Conta}.
 */

public class ContaCorrente extends Conta{
    private static final double mensalidade = 20.00;

    /**
     * Cria uma conta corrente com saldo zero.
     * @param cliente titlar da conta
     */
    public ContaCorrente(Cliente cliente){
        super(TipoConta.CORRENTE, cliente);
    }

    /**
     * @return o valor da mensalidade da conta corrente.
     */
    public double consultarMensalidade(){
        return mensalidade;
    }

}
