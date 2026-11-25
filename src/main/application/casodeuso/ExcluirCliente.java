package application.casodeuso;

import domain.pessoas.*;
import application.repositories.ClienteRepository;


public class ExcluirCliente extends CasoUso{
    private final ClienteRepository clienteRepo;

    public ExcluirCliente(ClienteRepository clienteRepo){
        this.clienteRepo = clienteRepo;
    }

    public void executar(String cpf) {
        validarCpf(cpf);
        Cliente cliente = clienteRepo.buscarPorCpf(cpf);

        if (cliente == null){
            throw new IllegalArgumentException("Cliente não encontrado.");
        }
        clienteRepo.excluir(cpf);
    }

}
