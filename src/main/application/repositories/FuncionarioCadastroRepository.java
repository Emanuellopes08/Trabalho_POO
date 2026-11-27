package application.repositories;

import domain.pessoas.Funcionario;

/**
 * Define o contrato para cadastro de funcionários
 * e gerentes no sistema.
 */
public interface FuncionarioCadastroRepository {

    /**
     * Salva um novo funcionário ou gerente no repositório.
     *
     * @param funcionario funcionário a ser cadastrado.
     */
    void salvar(Funcionario funcionario);
}