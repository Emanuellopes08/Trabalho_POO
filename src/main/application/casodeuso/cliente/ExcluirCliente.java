package application.casodeuso.cliente;

import application.validacao.ValidadorCliente;
import domain.pessoas.Cliente;
import application.repositories.ClienteRepository;

/**
 * Caso de uso responsável pela exclusão de clientes
 * cadastrados no sistema bancário.
 */
public class ExcluirCliente{
    private final ClienteRepository clienteRepo;

    /**
     * Inicializa o caso de uso com o repositório de clientes.
     *
     * @param clienteRepo repositório responsável pelo acesso
     * aos dados dos clientes.
     */
    public ExcluirCliente(ClienteRepository clienteRepo){
        this.clienteRepo = clienteRepo;
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

        if (cliente == null){
            throw new IllegalArgumentException("Cliente não encontrado.");
        }
        clienteRepo.excluir(cpf);
    }

}
