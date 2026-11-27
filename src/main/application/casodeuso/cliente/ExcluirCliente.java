
package application.casodeuso.cliente;

import application.validacao.ValidadorCliente;
import application.repositories.ClienteConsultaRepository;
import application.repositories.ClienteExclusaoRepository;
import domain.pessoas.Cliente;

/**
 * Caso de uso responsável pela exclusão de clientes
 * cadastrados no sistema bancário.
 */
public class ExcluirCliente {

    private final ClienteConsultaRepository clienteRepo;
    private final ClienteExclusaoRepository exclusaoRepo;

    /**
     * Inicializa o caso de uso com os contratos de consulta e exclusão.
     *
     * @param clienteRepo contrato para consulta de clientes.
     * @param exclusaoRepo contrato para exclusão de clientes.
     */
    public ExcluirCliente(
            ClienteConsultaRepository clienteRepo,
            ClienteExclusaoRepository exclusaoRepo) {

        this.clienteRepo = clienteRepo;
        this.exclusaoRepo = exclusaoRepo;
    }

    /**
     * Valida o CPF, verifica a existência do cliente
     * e solicita sua exclusão do repositório.
     *
     * @param cpf CPF do cliente a ser excluído.
     * @throws IllegalArgumentException se o CPF não for informado
     * ou se o cliente não for encontrado.
     */
    public void executar(String cpf) {

        ValidadorCliente.validarCpf(cpf);

        Cliente cliente = clienteRepo.buscarPorCpf(cpf);

        if (cliente == null) {
            throw new IllegalArgumentException("Cliente não encontrado.");
        }

        exclusaoRepo.excluir(cpf);
    }
}
