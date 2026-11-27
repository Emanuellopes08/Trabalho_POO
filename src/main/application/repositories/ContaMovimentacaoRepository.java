package application.repositories;

import domain.contas.Conta;

/**
 * Define o contrato para operações de movimentação
 * de contas bancárias.
 */
public interface ContaMovimentacaoRepository {

    /**
     * Busca uma conta pelo seu identificador.
     *
     * @param id identificador da conta.
     * @return conta encontrada ou null caso não exista.
     */
    Conta buscarPorId(int id);

    /**
     * Atualiza os dados de uma conta existente.
     *
     * @param conta conta com os dados atualizados.
     */
    void atualizar(Conta conta);
}