package application.repositories;

import domain.contas.Conta;
import java.util.List;

/**
 * Define o contrato para consulta de contas bancárias
 * vinculadas a um cliente.
 */
public interface ContaConsultaRepository {

    /**
     * Busca as contas vinculadas a um cliente,
     * seja como titular ou cotitular.
     *
     * @param cpf CPF do cliente.
     * @return lista de contas associadas ao CPF informado.
     */
    List<Conta> buscarPorCpfCliente(String cpf);
}