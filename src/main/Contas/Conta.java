package Contas;
import Pessoas.Cliente;

/**
 * Representa uma conta bancária genérica.
 * <p>
 * Define o comportamento comum a todas as modalidades (corrente, poupança,
 * conjunta e PJ): saldo inicial zero, depósito e saque. As regras de depósito e
 * saque ficam aqui, no domínio, e não nas telas. Os métodos {@link #efetuarDeposito(double)}
 * e {@link #efetuarSaque(double)} são {@code final} para que nenhuma subclasse
 * mude essas regras e quebre o Princípio da Substituição de Liskov (LSP).
 */
public abstract class Conta {
    private final TipoConta tipoConta;
    private final Cliente titular;
    private double saldo = 0;

    /**
     * Cria uma conta com saldo zero.
     *
     * @param tipoConta modalidade da conta (não pode ser nula)
     * @param titular   cliente titular da conta (não pode ser nulo)
     * @throws IllegalArgumentException se o tipo ou o titular forem nulos
     */

    }
    public Conta(TipoConta tipoConta, Cliente titular) {
        if (tipoConta == null) {
            throw new IllegalArgumentException("O tipo da conta é obrigatório.");
        }
        if (titular == null) {
            throw new IllegalArgumentException("O titular da conta é obrigatório.");
        }
        this.tipoConta = tipoConta;
        this.titular = titular;
    }
/**
 * @return a modalidade da conta
 */
public TipoConta getTipoConta() {
    return tipoConta;
}

/**
 * @return o cliente titular da conta
 */
public Cliente getTitular() {
    return titular;
}

/**
 * @return o saldo atual da conta
 */
public double getSaldo() {
    return saldo;
}

/**
 * Deposita um valor na conta (RN2).
 * @param valor valor a depositar; deve ser um número maior que zero
 * @throws IllegalArgumentException se o valor for menor ou igual a zero, NaN ou infinito
 */
public final void efetuarDeposito(double valor) {
    validarValor(valor, "depósito");
    this.saldo += valor;
}

/**
 * Saca um valor da conta (RN3).
 * @param valor valor a sacar; deve ser maior que zero e não pode passar do saldo
 * @throws IllegalArgumentException   se o valor for menor ou igual a zero, NaN ou infinito
 * @throws SaldoInsuficienteException se o valor for maior que o saldo disponível
 */
public final void efetuarSaque(double valor) {
    validarValor(valor, "saque");
    if (valor > saldo) {
        throw new SaldoInsuficienteException("Saldo insuficiente para o saque.");
    }
    this.saldo -= valor;
}

/**
 * Valida um valor monetário de operação.
 * @param valor     valor informado
 * @param operacao  nome da operação, usado na mensagem de erro
 * @throws IllegalArgumentException se o valor não for um número finito maior que zero
 */
private void validarValor(double valor, String operacao) {
    if (Double.isNaN(valor) || Double.isInfinite(valor) || valor <= 0) {
        throw new IllegalArgumentException("O valor do " + operacao + " deve ser maior que zero.");
    }
}

/**
 * @return descrição resumida da conta (tipo, titular e saldo)
 */
@Override
public String toString() {
    return tipoConta + " - Titular: " + titular.getNome() + " - Saldo: R$ " + String.format("%.2f", saldo);
    }
}