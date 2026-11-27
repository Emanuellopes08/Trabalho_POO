package application.repositories;

/**
 * Define o contrato para exclusão de clientes.
 */
public interface ClienteExclusaoRepository {

    /**
     * Remove um cliente pelo CPF.
     *
     * @param cpf CPF do cliente a ser excluído.
     */
    void excluir(String cpf);
}