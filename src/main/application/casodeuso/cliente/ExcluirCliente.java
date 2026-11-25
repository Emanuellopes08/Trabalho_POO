package application.casodeuso.cliente;

import application.validacao.ValidadorCliente;
import domain.pessoas.*;
import application.repositories.ClienteRepository;


public class ExcluirCliente{
    private final ClienteRepository clienteRepo;

    public ExcluirCliente(ClienteRepository clienteRepo){
        this.clienteRepo = clienteRepo;
    }

    public void executar(String cpf) {
        ValidadorCliente.validarCpf(cpf);
        Cliente cliente = clienteRepo.buscarPorCpf(cpf);

        if (cliente == null){
            throw new IllegalArgumentException("Cliente não encontrado.");
        }
        clienteRepo.excluir(cpf);
    }

}
