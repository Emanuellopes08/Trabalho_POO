package application.casodeuso;
import application.repositories.ClienteRepository;
import application.validacao.ValidadorCpf;
import domain.pessoas.Cliente;

public class ConsultarCliente{


    private final ClienteRepository clienteRepository;

    public ConsultarCliente(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    public Cliente executar(String cpf) {
        ValidadorCpf.validarCpf(cpf);
        Cliente clienteExistente = clienteRepository.buscarPorCpf(cpf);

        if (clienteExistente == null) {
            throw new IllegalArgumentException("Cliente não encontrado!");
        }
        return clienteExistente;
    }
}