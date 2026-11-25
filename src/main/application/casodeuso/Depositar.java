package application.casodeuso;

import domain.contas.Conta;
import application.repositories.ContaRepository;

public class Depositar extends CasoUso{

    private final ContaRepository contaRepo;

    public Depositar(ContaRepository contaRepo) {
        this.contaRepo = contaRepo;
    }

    public void executar(int id, double valor) {

        validarValor(valor);
        Conta conta = contaRepo.buscarPorId(id);

        if (conta == null) {
            throw new IllegalArgumentException("Conta não encontrada.");
        }

        conta.efetuarDeposito(valor);

        contaRepo.atualizar(conta);
    }
}
