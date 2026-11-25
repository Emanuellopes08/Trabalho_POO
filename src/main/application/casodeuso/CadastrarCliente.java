package application.casodeuso;

import domain.pessoas.Cliente;
import application.repositories.ClienteRepository;

public class CadastrarCliente {

    private final ClienteRepository clienteRepo;

    public CadastrarCliente(ClienteRepository clienteRepo) {
        this.clienteRepo = clienteRepo;
    }

    private void validarCpf(String cpf) {
        if (cpf == null || cpf.isBlank()) {
            throw new IllegalArgumentException("CPF não informado.");
        }
    }

    public void executar(Cliente cliente) {

        if (cliente == null) {
            throw new IllegalArgumentException("Cliente não informado.");
        }

        String cpf = cliente.getCpf();
        validarCpf(cpf);

        Cliente clienteExistente = clienteRepo.buscarPorCpf(cpf);

        if (clienteExistente != null) {
            throw new IllegalArgumentException("Cliente já cadastrado!");
        }

        clienteRepo.salvar(cliente);
    }
}
