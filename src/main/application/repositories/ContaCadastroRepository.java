package application.repositories;

import domain.contas.Conta;

/**
 * Define o contrato para cadastro de contas bancárias.
 */
public interface ContaCadastroRepository {

    /**
     * Salva uma nova conta bancária no repositório.
     *
     * @param conta conta bancária a ser persistida.
     */
    void salvar(Conta conta);
}