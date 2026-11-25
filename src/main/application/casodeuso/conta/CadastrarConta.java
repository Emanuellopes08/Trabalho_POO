package application.casodeuso.conta;

import application.repositories.ContaRepository;
import domain.contas.Conta;

public class CadastrarConta {
    private final ContaRepository contaRepo;

    public CadastrarConta(ContaRepository contaRepo){
        this.contaRepo = contaRepo;
    }

    public void executar (Conta conta) {
        if (conta == null) {
            throw new IllegalArgumentException("Conta não informada!");
        }
    contaRepo.salvar(conta);
    }
}

