package application.casodeuso.cliente;

import application.validacao.ValidadorCliente;
import domain.pessoas.Cliente;
import application.repositories.ClienteRepository;

/**
 * Caso de uso responsável pelo cadastro de clientes
 * no sistema bancário.
 */
public class CadastrarCliente{
    private final ClienteRepository clienteRepo;

    /**
     * Inicializa o caso de uso com o repositório de clientes.
     *
     * @param clienteRepo repositório responsável pelo acesso
     * aos dados dos clientes.
     */
    public CadastrarCliente(ClienteRepository clienteRepo) {
        this.clienteRepo = clienteRepo;
    }

    /**
     * Valida o CPF e a senha, verifica se o cliente já está
     * cadastrado e realiza seu cadastro.
     *
     * @param cliente cliente a ser cadastrado.
     * @param senha senha de acesso do cliente.
     * @throws IllegalArgumentException se o CPF ou a senha
     * forem inválidos ou se o cliente já estiver cadastrado.
     */
    public void executar(Cliente cliente, String senha) {

        String cpf = cliente.getCpf();
        ValidadorCliente.validarCpf(cpf);
        ValidadorCliente.validarSenha(senha);

        Cliente clienteExistente = clienteRepo.buscarPorCpf(cpf);

        if (clienteExistente != null) {
            throw new IllegalArgumentException("Cliente já cadastrado!");
        }
        cliente.setSenha(senha);
        clienteRepo.salvar(cliente);
    }
}
