package application.casodeuso.conta;

import application.validacao.ValidadorValor;
import domain.contas.Conta;
import application.repositories.ContaRepository;

public class Sacar{

    private final ContaRepository contaRepo;

    public Sacar(ContaRepository contaRepo) {
        this.contaRepo = contaRepo;
    }

    public void executar(int id, double valor) {

        ValidadorValor.validarValor(valor);
        Conta conta = contaRepo.buscarPorId(id);

        if (conta == null) {
            throw new IllegalArgumentException("Conta não encontrada.");
        }

        if (valor > conta.getSaldo()) {
            throw new IllegalArgumentException("Saldo insuficiente.");
        }

        conta.efetuarSaque(valor);

        contaRepo.atualizar(conta);
    }
}
