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
     * Valida os dados do cliente, verifica sua existência
     * no repositório e atualiza seus dados.
     *
     * @param cliente cliente com os dados atualizados.
     * @throws IllegalArgumentException se o cliente for nulo,
     * o CPF ou a senha forem inválidos ou o cliente
     * não for encontrado.
     */
    public void executar(Cliente cliente) {

        ValidadorCliente.validarCliente(cliente);

        String cpf = cliente.getCpf();

        Cliente clienteExistente = clienteRepo.buscarPorCpf(cpf);

        if (clienteExistente == null) {
            throw new IllegalArgumentException("Cliente não encontrado.");
        }

        clienteRepo.atualizar(cliente);
    }
}
