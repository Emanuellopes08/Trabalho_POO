package application.casodeuso.cliente;

import application.validacao.ValidadorCliente;
import domain.pessoas.Cliente;
import application.repositories.ClienteRepository;

public class CadastrarCliente{

    private final ClienteRepository clienteRepo;

    public CadastrarCliente(ClienteRepository clienteRepo) {
        this.clienteRepo = clienteRepo;
    }

    public void executar(Cliente cliente, String senha) {

        String cpf = cliente.getCpf();
        ValidadorCliente.validarCpf(cpf);
        ValidadorCliente.validarSenha(senha);

        Cliente clienteExistente = clienteRepo.buscarPorCpf(cpf);

        if (clienteExistente != null) {
            throw new IllegalArgumentException("Cliente já cadastrado!");
        }
        cliente.setSenha(senha);
        clienteRepo.salvar(cliente);
    }
}
