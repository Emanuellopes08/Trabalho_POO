package application.repositories;

import domain.pessoas.Cliente;
import java.util.List;

/**
 * Interface que define o contrato para operações de persistência de clientes.
 * Permite que os casos de uso acessem dados de clientes sem depender da
 * implementação concreta do banco de dados (DIP e ISP).
 */
public interface ClienteRepository {

    /**
     * Salva um novo cliente no repositório.
     *
     * @param cliente Objeto {@link Cliente} a ser persistido.
     */
    void salvar(Cliente cliente);

    /**
     * Busca um cliente cadastrado a partir do seu CPF.
     *
     * @param cpf CPF do cliente a ser consultado.
     * @return O {@link Cliente} correspondente, ou {@code null} caso não seja encontrado.
     */
    Cliente buscarPorCpf(String cpf);

    /**
     * Retorna todos os clientes cadastrados no sistema.
     *
     * @return Lista contendo todos os objetos {@link Cliente}.
     */
    List<Cliente> listarTodos();

    /**
     * Atualiza os dados de um cliente já existente.
     *
     * @param cliente Objeto {@link Cliente} com os dados atualizados.
     */
    void atualizar(Cliente cliente);

    /**
     * Remove um cliente do repositório com base no seu CPF.
     *
     * @param cpf CPF do cliente a ser excluído.
     */
    void excluir(String cpf);
}