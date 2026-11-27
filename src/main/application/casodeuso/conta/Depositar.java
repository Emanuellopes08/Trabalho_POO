
package application.casodeuso.conta;

import application.validacao.ValidadorValor;
import application.repositories.ContaMovimentacaoRepository;
import domain.contas.Conta;

/**
 * Caso de uso responsável pela realização de depósitos
 * em contas bancárias.
 */
public class Depositar {

    private final ContaMovimentacaoRepository contaRepo;

    /**
     * Inicializa o caso de uso com o contrato de movimentação de contas.
     *
     * @param contaRepo contrato responsável pela consulta e atualização
     * das contas bancárias.
     */
    public Depositar(ContaMovimentacaoRepository contaRepo) {
        this.contaRepo = contaRepo;
    }

    /**
     * Valida o valor informado, consulta a conta bancária,
     * realiza o depósito e solicita a atualização da conta
     * no repositório.
     *
     * @param id identificador da conta bancária.
     * @param valor valor a ser depositado.
     * @throws IllegalArgumentException se o valor for inválido
     * ou se a conta não for encontrada.
     */
    public void executar(int id, double valor) {

        ValidadorValor.validarValor(valor);

        Conta conta = contaRepo.buscarPorId(id);

        if (conta == null) {
            throw new IllegalArgumentException("Conta não encontrada.");
        }

        conta.efetuarDeposito(valor);

        contaRepo.atualizar(conta);
    }
}
