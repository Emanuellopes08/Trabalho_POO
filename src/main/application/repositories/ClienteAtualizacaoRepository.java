package application.repositories;

import domain.pessoas.Cliente;

/**
 * Define o contrato para atualização de clientes.
 */
public interface ClienteAtualizacaoRepository {

    /**
     * Atualiza os dados de um cliente existente.
     *
     * @param cliente cliente com os dados atualizados.
     */
    void atualizar(Cliente cliente);
}