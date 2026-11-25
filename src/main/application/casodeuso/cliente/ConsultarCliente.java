package application.casodeuso.cliente;
import application.repositories.ClienteRepository;
import application.validacao.ValidadorCliente;
import domain.pessoas.Cliente;

/**
 * Caso de uso responsável pela consulta de clientes
 * por meio do CPF.
 */
public class ConsultarCliente{


    private final ClienteRepository clienteRepository;

    /**
     * Inicializa o caso de uso com o repositório de clientes.
     *
     * @param clienteRepository repositório responsável pelo
     * acesso aos dados dos clientes.
     */
    public ConsultarCliente(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    /**
     * Consulta um cliente cadastrado a partir do CPF informado.
     *
     * @param cpf CPF do cliente a ser consultado.
     * @return cliente encontrado no repositório.
     * @throws IllegalArgumentException se o CPF não for informado
     * ou se o cliente não for encontrado.
     */
    public Cliente executar(String cpf) {
        ValidadorCliente.validarCpf(cpf);
        Cliente clienteExistente = clienteRepository.buscarPorCpf(cpf);

        if (clienteExistente == null) {
            throw new IllegalArgumentException("Cliente não encontrado!");
        }
        return clienteExistente;
    }
}