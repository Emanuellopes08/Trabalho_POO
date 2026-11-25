package application.casodeuso.cliente;
import application.repositories.ClienteRepository;
import application.validacao.ValidadorCliente;
import domain.pessoas.Cliente;

public class ConsultarCliente{


    private final ClienteRepository clienteRepository;

    public ConsultarCliente(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    public Cliente executar(String cpf) {
        ValidadorCliente.validarCpf(cpf);
        Cliente clienteExistente = clienteRepository.buscarPorCpf(cpf);

        if (clienteExistente == null) {
            throw new IllegalArgumentException("Cliente não encontrado!");
        }
        return clienteExistente;
    }
}