package application.repositories;

import domain.contas.Conta;
import java.util.List;

/**
 * Interface que define o contrato para operações de persistência de contas bancárias.
 * Garante o desacoplamento entre os casos de uso e a implementação JDBC (ISP e DIP).
 */
public interface ContaRepository {

    /**
     * Salva uma nova conta bancária no repositório.
     *
     * @param conta Objeto {@link Conta} (Corrente, Poupança, Conjunta ou PJ) a ser persistido.
     */
    void salvar(Conta conta);

    /**
     * Busca uma conta bancária pelo seu identificador único.
     *
     * @param id Identificador numérico da conta no banco de dados.
     * @return A {@link Conta} correspondente, ou {@code null} caso não seja encontrada.
     */
    Conta buscarPorId(int id);

    /**
     * Lista todas as contas vinculadas a um cliente (como titular ou cotitular).
     *
     * @param cpf CPF do cliente titular ou cotitular.
     * @return Lista de objetos {@link Conta} associados ao CPF informado.
     */
    List<Conta> buscarPorCpfCliente(String cpf);

    /**
     * Retorna todas as contas cadastradas no sistema.
     *
     * @return Lista contendo todas as instâncias de {@link Conta}.
     */
    List<Conta> listarTodas();

    /**
     * Atualiza os dados de uma conta existente (como saldo após depósitos ou saques).
     *
     * @param conta Objeto {@link Conta} com os dados atualizados.
     */
    void atualizar(Conta conta);

    /**
     * Remove uma conta do repositório a partir do seu identificador.
     *
     * @param id Identificador numérico da conta a ser excluída.
     */
    void excluir(int id);
}