package application.repositories;

import domain.pessoas.Funcionario;
import java.util.List;

/**
 * Interface que define o contrato para operações de persistência de funcionários e gerentes.
 * Garante o desacoplamento entre os casos de uso e a implementação JDBC (ISP e DIP).
 */
public interface FuncionarioRepository {

    /**
     * Salva um novo funcionário (ou gerente) no repositório.
     *
     * @param funcionario Objeto {@link Funcionario} a ser persistido.
     */
    void salvar(Funcionario funcionario);

    /**
     * Busca um funcionário cadastrado a partir do seu código identificador.
     *
     * @param codigo Código único do funcionário no banco de dados.
     * @return O {@link Funcionario} correspondente, ou {@code null} caso não seja encontrado.
     */
    Funcionario buscarPorCodigo(int codigo);

    /**
     * Busca um funcionário pelo seu nome de usuário (utilizado na autenticação).
     *
     * @param usuario Nome de usuário único do funcionário.
     * @return O {@link Funcionario} correspondente, ou {@code null} caso não seja encontrado.
     */
    Funcionario buscarPorUsuario(String usuario);

    /**
     * Retorna todos os funcionários cadastrados no sistema.
     *
     * @return Lista contendo todos os objetos {@link Funcionario}.
     */
    List<Funcionario> listarTodos();

    /**
     * Atualiza os dados de um funcionário já existente.
     *
     * @param funcionario Objeto {@link Funcionario} com os dados atualizados.
     */
    void atualizar(Funcionario funcionario);

    /**
     * Remove um funcionário do repositório com base no seu código.
     *
     * @param codigo Código único do funcionário a ser excluído.
     */
    void excluir(int codigo);
}