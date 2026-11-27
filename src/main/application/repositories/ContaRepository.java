package application.repositories;

import domain.contas.Conta;
import java.util.List;

/**
 * Reúne os contratos de persistência de contas bancárias.
 *
 * Estende as interfaces específicas de cadastro, consulta
 * e movimentação de contas.
 */
public interface ContaRepository extends
        ContaCadastroRepository,
        ContaConsultaRepository,
        ContaMovimentacaoRepository {

    /**
     * Retorna todas as contas cadastradas no sistema.
     *
     * @return lista contendo todas as contas.
     */
    List<Conta> listarTodas();

    /**
     * Remove uma conta pelo seu identificador.
     *
     * @param id identificador da conta a ser excluída.
     */
    void excluir(int id);
}