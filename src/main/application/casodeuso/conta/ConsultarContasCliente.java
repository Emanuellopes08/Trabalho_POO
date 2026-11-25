package application.casodeuso.conta;

import application.repositories.ClienteRepository;
import application.repositories.ContaRepository;
import application.validacao.ValidadorCliente;
import domain.contas.Conta;

import java.util.List;

public class ConsultarContasCliente {

    private final ClienteRepository clienteRepo;
    private final ContaRepository contaRepo;

    public ConsultarContasCliente(ClienteRepository clienteRepo, ContaRepository contaRepo) {
        this.clienteRepo = clienteRepo;
        this.contaRepo = contaRepo;
    }

    public List<Conta> executar(String cpf) {

        ValidadorCliente.validarCpf(cpf);

        if (clienteRepo.buscarPorCpf(cpf) == null) {
            throw new IllegalArgumentException("Cliente não encontrado.");
        }

        return contaRepo.buscarPorCpfCliente(cpf);
    }
}