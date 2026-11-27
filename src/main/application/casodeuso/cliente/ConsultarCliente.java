
package application.casodeuso.cliente;

import application.repositories.ClienteConsultaRepository;
import application.validacao.ValidadorCliente;
import domain.pessoas.Cliente;

/**
 * Caso de uso responsável pela consulta de clientes
 * por meio do CPF.
 */
public class ConsultarCliente {

    private final ClienteConsultaRepository clienteRepository;

    /**
     * Inicializa o caso de uso com o contrato de consulta de clientes.
     *
     * @param clienteRepository contrato responsável pela consulta.
     */
    public ConsultarCliente(ClienteConsultaRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    /**
     * Consulta um cliente cadastrado a partir do CPF informado.
     *
     * @param cpf CPF do cliente a ser consultado.
     * @return cliente encontrado no repositório.
     * @throws IllegalArgumentException se o CPF não for informado
     * ou se o cliente não for encontrado.
     */
    public Cliente executar(String cpf) {

        ValidadorCliente.validarCpf(cpf);

        Cliente clienteExistente = clienteRepository.buscarPorCpf(cpf);

        if (clienteExistente == null) {
            throw new IllegalArgumentException("Cliente não encontrado!");
        }

        return clienteExistente;
    }
}
