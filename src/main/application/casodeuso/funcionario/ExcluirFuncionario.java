package application.casodeuso.funcionario;

import application.repositories.FuncionarioRepository;
import application.validacao.ValidadorFuncionario;
import domain.pessoas.Funcionario;

public class ExcluirFuncionario {

    private final FuncionarioRepository funcionarioRepo;

    public ExcluirFuncionario(FuncionarioRepository funcionarioRepo) {
        this.funcionarioRepo = funcionarioRepo;
    }

    public void executar(int codigo) {

        ValidadorFuncionario.validarCodigo(codigo);
        Funcionario funcionario = funcionarioRepo.buscarPorCodigo(codigo);

        if (funcionario == null) {
            throw new IllegalArgumentException("Funcionário não encontrado.");
        }

        funcionarioRepo.excluir(codigo);
    }
}