package application.casodeuso.funcionario;

import application.repositories.FuncionarioRepository;
import application.validacao.ValidadorFuncionario;
import domain.pessoas.Funcionario;

public class AlterarFuncionario {

    private final FuncionarioRepository funcionarioRepo;

    public AlterarFuncionario(FuncionarioRepository funcionarioRepo) {
        this.funcionarioRepo = funcionarioRepo;
    }

    public void executar(Funcionario funcionario) {

        ValidadorFuncionario.validarFuncionario(funcionario);
        int codigo = funcionario.getCodigo();
        Funcionario funcionarioExistente = funcionarioRepo.buscarPorCodigo(codigo);

        if (funcionarioExistente == null) {
            throw new IllegalArgumentException("Funcionário não encontrado.");
        }

        funcionarioRepo.atualizar(funcionario);
    }
}