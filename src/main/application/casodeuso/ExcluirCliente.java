package application.casodeuso;

import domain.pessoas.*;
import application.repositories.ClienteRepository;


public class ExcluirCliente {
    private final ClienteRepository clienteRepo;

    public ExcluirCliente(ClienteRepository clienteRepo){
        this.clienteRepo = clienteRepo;
    }

    private void validarCpf(String cpf) {
        if (cpf == null || cpf.isBlank()) {
            throw new IllegalArgumentException("CPF não informado.");
        }
    }

    public void executar(String cpf) {
        validarCpf(cpf);
        Cliente cliente = clienteRepo.buscarPorCpf(cpf);

        if (cliente == null){
            throw new IllegalArgumentException("Cliente não encontrado.");
        }
        ClienteRepository.excluir(cpf);
    }

}
