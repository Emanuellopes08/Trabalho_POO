package application.casodeuso.autenticacao;

import application.repositories.ClienteRepository;
import application.repositories.ContaRepository;
import application.validacao.ValidadorCliente;
import domain.pessoas.Cliente;

public class AutenticarCliente {

    private final ClienteRepository clienteRepo;
    private final ContaRepository contaRepo;

    public AutenticarCliente(ClienteRepository clienteRepo, ContaRepository contaRepo) {
        this.clienteRepo = clienteRepo;
        this.contaRepo = contaRepo;
    }

    public Cliente executar(String cpf, String senha) {
        ValidadorCliente.validarCpf(cpf);

        if (senha == null || senha.isBlank()) {
            throw new IllegalArgumentException("Senha não informada.");
        }

        Cliente cliente = clienteRepo.buscarPorCpf(cpf);

        if (cliente == null || !senha.equals(cliente.getSenha())) {
            throw new IllegalArgumentException("CPF ou senha inválidos.");
        }

        if (contaRepo.buscarPorCpfCliente(cpf).isEmpty()) {
            throw new IllegalArgumentException("Nenhuma conta encontrada para este cliente.");
        }
        return cliente;
    }
}