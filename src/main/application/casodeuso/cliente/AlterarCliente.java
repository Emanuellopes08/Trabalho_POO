package application.casodeuso.cliente;

import application.validacao.ValidadorCliente;
import domain.pessoas.Cliente;
import application.repositories.ClienteRepository;

/**
 * Caso de uso responsável pela alteração dos dados
 * de clientes cadastrados no sistema bancário.
 */
public class AlterarCliente{

    private final ClienteRepository clienteRepo;

    /**
     * Inicializa o caso de uso com o repositório de clientes.
     *
     * @param clienteRepo repositório responsável pelo acesso
     * aos dados dos clientes.
     */
    public AlterarCliente(ClienteRepository clienteRepo) {
        this.clienteRepo = clienteRepo;
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

        clienteRepo.atualizar(cliente);
    }
}
