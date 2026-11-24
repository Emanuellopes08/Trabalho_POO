package application.casodeuso;
import application.repositories.ClienteRepository;
import domain.pessoas.Cliente;

public class ConsultarCliente {


    private final ClienteRepository clienteRepository;

    public ConsultarCliente(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }


    private void validarCpf(String cpf) {
        if (cpf == null || cpf.isBlank()) {
            throw new IllegalArgumentException("CPF não informado.");
        }
    }

    public Cliente executar(String cpf) {
        validarCpf(cpf);
        Cliente clienteExistente = clienteRepository.buscarPorCpf(cpf);

        if (clienteExistente == null) {
            throw new IllegalArgumentException("Cliente não encontrado!");
        }
        return clienteExistente;
    }
}