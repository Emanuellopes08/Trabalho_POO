
package application.casodeuso.conta;

import application.repositories.ClienteConsultaRepository;
import application.repositories.ContaConsultaRepository;
import application.validacao.ValidadorCliente;
import domain.contas.Conta;

import java.util.List;

/**
 * Caso de uso responsável pela consulta das contas
 * bancárias vinculadas a um cliente.
 */
public class ConsultarContasCliente {

    private final ClienteConsultaRepository clienteRepo;
    private final ContaConsultaRepository contaRepo;

    /**
     * Inicializa o caso de uso com os contratos
     * de consulta de clientes e contas bancárias.
     *
     * @param clienteRepo contrato de consulta de clientes.
     * @param contaRepo contrato de consulta de contas bancárias.
     */
    public ConsultarContasCliente(
            ClienteConsultaRepository clienteRepo,
            ContaConsultaRepository contaRepo) {

        this.clienteRepo = clienteRepo;
        this.contaRepo = contaRepo;
    }

    /**
     * Consulta as contas bancárias vinculadas ao cliente
     * a partir do CPF informado.
     *
     * @param cpf CPF do cliente a ser consultado.
     * @return lista de contas bancárias vinculadas ao cliente.
     * @throws IllegalArgumentException se o CPF não for informado,
     * o cliente não for encontrado ou não possuir contas bancárias.
     */
    public List<Conta> executar(String cpf) {

        ValidadorCliente.validarCpf(cpf);

        if (clienteRepo.buscarPorCpf(cpf) == null) {
            throw new IllegalArgumentException("Cliente não encontrado.");
        }

        List<Conta> contas = contaRepo.buscarPorCpfCliente(cpf);

        if (contas.isEmpty()) {
            throw new IllegalArgumentException("Cliente não possui conta bancária.");
        }

        return contas;
    }
}
