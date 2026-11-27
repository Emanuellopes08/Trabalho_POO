package application.repositories;

import domain.pessoas.Cliente;
import java.util.List;

/**
 * Reúne os contratos de persistência de clientes.
 *
 * Estende as interfaces específicas de consulta, cadastro,
 * atualização e exclusão, permitindo que a implementação
 * JDBC disponibilize todas essas operações.
 */
public interface ClienteRepository extends
        ClienteConsultaRepository,
        ClienteCadastroRepository,
        ClienteAtualizacaoRepository,
        ClienteExclusaoRepository {

    /**
     * Retorna todos os clientes cadastrados.
     *
     * @return lista de clientes.
     */
    List<Cliente> listarTodos();
}