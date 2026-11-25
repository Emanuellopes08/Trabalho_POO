package application.casodeuso.conta;

import application.repositories.ClienteRepository;
import application.repositories.ContaRepository;
import application.validacao.ValidadorCliente;
import domain.contas.Conta;

import java.util.List;

/**
 * Caso de uso responsável pela consulta das contas
 * bancárias vinculadas a um cliente.
 */
public class ConsultarContasCliente {
    private final ClienteRepository clienteRepo;
    private final ContaRepository contaRepo;

    /**
     * Inicializa o caso de uso com os repositórios
     * de clientes e contas bancárias.
     *
     * @param clienteRepo repositório de clientes.
     * @param contaRepo repositório de contas bancárias.
     */
    public ConsultarContasCliente(ClienteRepository clienteRepo, ContaRepository contaRepo) {
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