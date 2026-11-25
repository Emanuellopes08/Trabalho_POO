package application.casodeuso;

import domain.pessoas.Cliente;
import application.repositories.ClienteRepository;

public class AlterarCliente {

    private final ClienteRepository clienteRepo;

    public AlterarCliente(ClienteRepository clienteRepo) {
        this.clienteRepo = clienteRepo;
    }

    public void executar(Cliente cliente) {

        if (cliente == null) {
            throw new IllegalArgumentException("Cliente não informado.");
        }

        String cpf = cliente.getCpf();
        validarCpf(cpf);

        Cliente clienteExistente = clienteRepo.buscarPorCpf(cpf);

        if (clienteExistente == null) {
            throw new IllegalArgumentException("Cliente não encontrado.");
        }

        clienteRepo.atualizar(cliente);
    }

    private void validarCpf(String cpf) {
        if (cpf == null || cpf.isBlank()) {
            throw new IllegalArgumentException("CPF não informado.");
        }
    }
}
