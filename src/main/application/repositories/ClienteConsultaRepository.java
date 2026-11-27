package application.repositories;

import domain.pessoas.Cliente;

/**
 * Define o contrato para consulta de clientes.
 *
 * Permite que os casos de uso dependam somente
 * da operação de busca de clientes pelo CPF.
 */
public interface ClienteConsultaRepository {

    /**
     * Busca um cliente pelo CPF.
     *
     * @param cpf CPF do cliente.
     * @return Cliente encontrado ou null caso não exista.
     */
    Cliente buscarPorCpf(String cpf);
}