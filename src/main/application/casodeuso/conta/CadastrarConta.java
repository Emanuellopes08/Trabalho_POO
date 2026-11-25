package application.casodeuso.conta;

import application.repositories.ContaRepository;
import domain.contas.Conta;

/**
 * Caso de uso responsável pelo cadastro de contas bancárias.
 * Utiliza a abstração Conta, permitindo o cadastro de diferentes
 * modalidades sem modificar a implementação deste caso de uso,
 * seguindo o princípio OCP.
 */
public class CadastrarConta {
    private final ContaRepository contaRepo;

    /**
     * Inicializa o caso de uso com o repositório de contas.
     *
     * @param contaRepo repositório responsável pelo acesso
     * aos dados das contas bancárias.
     */
    public CadastrarConta(ContaRepository contaRepo){
        this.contaRepo = contaRepo;
    }

    /**
     * Verifica se a conta foi informada e solicita seu cadastro
     * no repositório.
     *
     * @param conta conta bancária a ser cadastrada.
     * @throws IllegalArgumentException se a conta for nula.
     */
    public void executar (Conta conta) {
        if (conta == null) {
            throw new IllegalArgumentException("Conta não informada!");
        }
    contaRepo.salvar(conta);
    }
}

