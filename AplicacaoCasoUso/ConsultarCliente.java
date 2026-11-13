package AplicacaoCasoUso;

public class ConsultarCliente {

    /*
    private final ClienteRepository clienteRepository;

    public ConsultarCliente(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }
    Esperando a Criação da classe*/

    private void validarCpf(String cpf) {
        if (cpf == null || cpf.isBlank()) {
            throw new IllegalArgumentException("CPF não informado.");
        }
    }

}