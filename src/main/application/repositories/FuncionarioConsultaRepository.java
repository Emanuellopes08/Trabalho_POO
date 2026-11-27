
package application.repositories;

import domain.pessoas.Funcionario;

/**
 * Define o contrato para consulta de funcionários
 * pelo código de identificação.
 */
public interface FuncionarioConsultaRepository {

    /**
     * Busca um funcionário pelo seu código.
     *
     * @param codigo código identificador do funcionário.
     * @return funcionário encontrado ou null caso não exista.
     */
    Funcionario buscarPorCodigo(int codigo);
}
