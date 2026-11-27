package application.repositories;

/**
 * Define o contrato para exclusão de funcionários
 * e gerentes cadastrados no sistema.
 */
public interface FuncionarioExclusaoRepository {

    /**
     * Remove um funcionário pelo seu código identificador.
     *
     * @param codigo código do funcionário a ser excluído.
     */
    void excluir(int codigo);
}