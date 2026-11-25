package application.casodeuso;

import application.repositories.FuncionarioRepository;
import application.validacao.ValidadorFuncionario;
import domain.pessoas.Funcionario;

public class ConsultarFuncionario {

    private final FuncionarioRepository funcionarioRepo;

    public ConsultarFuncionario(FuncionarioRepository funcionarioRepo) {
        this.funcionarioRepo = funcionarioRepo;
    }

    public Funcionario executar(int codigo) {

        ValidadorFuncionario.validarCodigo(codigo);
        Funcionario funcionario = funcionarioRepo.buscarPorCodigo(codigo);

        if (funcionario == null) {
            throw new IllegalArgumentException(
                    "Funcionário não encontrado."
            );
        }
        return funcionario;
    }
}