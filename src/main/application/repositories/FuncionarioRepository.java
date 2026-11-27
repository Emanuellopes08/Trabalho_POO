
package application.repositories;

import domain.pessoas.Funcionario;
import java.util.List;

/**
 * Reúne os contratos de persistência de funcionários e gerentes.
 *
 * Estende as interfaces específicas de consulta, cadastro,
 * atualização e exclusão, mantendo as operações adicionais
 * disponíveis para a implementação geral.
 */
public interface FuncionarioRepository extends
        FuncionarioConsultaRepository,
        FuncionarioCadastroRepository,
        FuncionarioAtualizacaoRepository,
        FuncionarioExclusaoRepository {

    /**
     * Busca um funcionário pelo nome de usuário.
     *
     * @param usuario nome de usuário do funcionário.
     * @return funcionário encontrado ou null caso não exista.
     */
    Funcionario buscarPorUsuario(String usuario);

    /**
     * Retorna todos os funcionários cadastrados.
     *
     * @return lista de funcionários.
     */
    List<Funcionario> listarTodos();
}
