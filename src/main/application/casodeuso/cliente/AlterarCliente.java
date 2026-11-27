
package application.casodeuso.cliente;

import application.validacao.ValidadorCliente;
import application.repositories.ClienteConsultaRepository;
import application.repositories.ClienteAtualizacaoRepository;
import domain.pessoas.Cliente;

/**
 * Caso de uso responsável pela alteração dos dados
 * de clientes cadastrados no sistema bancário.
 */
public class AlterarCliente {

    private final ClienteConsultaRepository clienteRepo;
    private final ClienteAtualizacaoRepository atualizacaoRepo;

    /**
     * Inicializa o caso de uso com os contratos de consulta
     * e atualização de clientes.
     *
     * @param clienteRepo contrato para consulta de clientes.
     * @param atualizacaoRepo contrato para atualização de clientes.
     */
    public AlterarCliente(
            ClienteConsultaRepository clienteRepo,
            ClienteAtualizacaoRepository atualizacaoRepo) {

        this.clienteRepo = clienteRepo;
        this.atualizacaoRepo = atualizacaoRepo;
    }

    /**
     * Valida o cliente e seu CPF, verifica sua existência
     * e solicita a atualização dos seus dados.
     *
     * @param cliente cliente com os dados atualizados.
     * @throws IllegalArgumentException se o cliente for nulo,
     * o CPF não for informado ou o cliente não for encontrado.
     */
    public void executar(Cliente cliente) {

        if (cliente == null) {
            throw new IllegalArgumentException("Cliente não informado.");
        }

        String cpf = cliente.getCpf();
        ValidadorCliente.validarCpf(cpf);

        Cliente clienteExistente = clienteRepo.buscarPorCpf(cpf);

        if (clienteExistente == null) {
            throw new IllegalArgumentException("Cliente não encontrado.");
        }

        atualizacaoRepo.atualizar(cliente);
    }
}
