
package application.casodeuso.autenticacao;

import application.repositories.ClienteConsultaRepository;
import application.repositories.ContaConsultaRepository;
import application.validacao.ValidadorCliente;
import domain.pessoas.Cliente;

/**
 * Caso de uso responsável pela autenticação dos clientes
 * no sistema bancário.
 */
public class AutenticarCliente {

    private final ClienteConsultaRepository clienteRepo;
    private final ContaConsultaRepository contaRepo;

    /**
     * Inicializa o caso de uso com os contratos
     * de consulta de clientes e contas bancárias.
     *
     * @param clienteRepo contrato de consulta de clientes.
     * @param contaRepo contrato de consulta de contas bancárias.
     */
    public AutenticarCliente(
            ClienteConsultaRepository clienteRepo,
            ContaConsultaRepository contaRepo) {

        this.clienteRepo = clienteRepo;
        this.contaRepo = contaRepo;
    }

    /**
     * Autentica o cliente por meio do CPF e da senha,
     * verificando se possui pelo menos uma conta bancária.
     *
     * @param cpf CPF do cliente.
     * @param senha senha de acesso do cliente.
     * @return cliente autenticado.
     */
    public Cliente executar(String cpf, String senha) {

        ValidadorCliente.validarCpf(cpf);

        if (senha == null || senha.isBlank()) {
            throw new IllegalArgumentException("Senha não informada.");
        }

        Cliente cliente = clienteRepo.buscarPorCpf(cpf);

        if (cliente == null || !senha.equals(cliente.getSenha())) {
            throw new IllegalArgumentException("CPF ou senha inválidos.");
        }

        if (contaRepo.buscarPorCpfCliente(cpf).isEmpty()) {
            throw new IllegalArgumentException("Nenhuma conta encontrada para este cliente.");
        }

        return cliente;
    }
}
