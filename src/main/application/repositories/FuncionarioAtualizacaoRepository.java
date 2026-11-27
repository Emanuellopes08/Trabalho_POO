package application.repositories;

import domain.pessoas.Funcionario;

/**
 * Define o contrato para atualização de funcionários
 * e gerentes cadastrados no sistema.
 */
public interface FuncionarioAtualizacaoRepository {

    /**
     * Atualiza os dados de um funcionário existente.
     *
     * @param funcionario funcionário com os dados atualizados.
     */
    void atualizar(Funcionario funcionario);
}