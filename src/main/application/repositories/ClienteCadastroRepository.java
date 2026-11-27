package application.repositories;

import domain.pessoas.Cliente;

/**
 * Define o contrato para cadastro de clientes.
 */
public interface ClienteCadastroRepository {

    /**
     * Salva um novo cliente no repositório.
     *
     * @param cliente cliente a ser cadastrado.
     */
    void salvar(Cliente cliente);
}