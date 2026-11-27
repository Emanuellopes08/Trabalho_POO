
package application.casodeuso.conta;

import application.validacao.ValidadorValor;
import application.repositories.ContaMovimentacaoRepository;
import domain.contas.Conta;

/**
 * Caso de uso responsável pela realização de saques
 * em contas bancárias.
 */
public class Sacar {

    private final ContaMovimentacaoRepository contaRepo;

    /**
     * Inicializa o caso de uso com o contrato de movimentação de contas.
     *
     * @param contaRepo contrato responsável pela consulta e atualização
     * dos dados das contas bancárias.
     */
    public Sacar(ContaMovimentacaoRepository contaRepo) {
        this.contaRepo = contaRepo;
    }

    /**
     * Valida o valor informado, consulta a conta bancária,
     * verifica a disponibilidade de saldo, realiza o saque
     * e solicita a atualização da conta no repositório.
     *
     * @param id identificador da conta bancária.
     * @param valor valor a ser sacado.
     * @throws IllegalArgumentException se o valor for inválido,
     * a conta não for encontrada ou o saldo for insuficiente.
     */
    public void executar(int id, double valor) {

        ValidadorValor.validarValor(valor);

        Conta conta = contaRepo.buscarPorId(id);

        if (conta == null) {
            throw new IllegalArgumentException("Conta não encontrada.");
        }

        if (valor > conta.getSaldo()) {
            throw new IllegalArgumentException("Saldo insuficiente.");
        }

        conta.efetuarSaque(valor);

        contaRepo.atualizar(conta);
    }
}
