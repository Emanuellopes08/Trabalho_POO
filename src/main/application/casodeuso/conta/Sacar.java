package application.casodeuso.conta;

import application.validacao.ValidadorValor;
import domain.contas.Conta;
import application.repositories.ContaRepository;
import domain.contas.SaldoInsuficienteException;

/**
 * Caso de uso responsável pela realização de saques
 * em contas bancárias.
 */
public class Sacar{

    private final ContaRepository contaRepo;

    /**
     * Inicializa o caso de uso com o repositório de contas.
     *
     * @param contaRepo repositório responsável pelo acesso
     * aos dados das contas bancárias.
     */
    public Sacar(ContaRepository contaRepo) {
        this.contaRepo = contaRepo;
    }

    /**
     * Valida o valor informado, consulta a conta bancária,
     * verifica a disponibilidade de saldo, realiza o saque
     * e solicita a atualização da conta no repositório.
     *
     * @param id identificador da conta bancária.
     * @param valor valor a ser sacado.
     * @throws IllegalArgumentException se o valor for inválido
     * ou a conta não for encontrada.
     * @throws SaldoInsuficienteException se o saldo for insuficiente.
     */
    public void executar(int id, double valor) {
        ValidadorValor.validarValor(valor);
        Conta conta = contaRepo.buscarPorId(id);

        if (conta == null) {
            throw new IllegalArgumentException("Conta não encontrada.");
        }

        conta.efetuarSaque(valor);
        contaRepo.atualizar(conta);
    }
}
