package application.casodeuso;

import application.repositories.FuncionarioRepository;
import domain.pessoas.Funcionario;

public class ExcluirFuncionario {

    private final FuncionarioRepository funcionarioRepo;

    public ExcluirFuncionario(FuncionarioRepository funcionarioRepo) {
        this.funcionarioRepo = funcionarioRepo;
    }

    public void executar(int codigo) {

        ValidadorCodigo.validarCodigo(codigo);
        Funcionario funcionario = funcionarioRepo.buscarPorCodigo(codigo);

        if (funcionario == null) {
            throw new IllegalArgumentException("Funcionário não encontrado.");
        }

        funcionarioRepo.excluir(codigo);
    }
}