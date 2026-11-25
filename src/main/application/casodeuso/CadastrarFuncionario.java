package application.casodeuso;

import application.validacao.ValidadorCodigo;
import domain.pessoas.Funcionario;
import application.repositories.FuncionarioRepository;

public class CadastrarFuncionario{

    private final FuncionarioRepository funcionarioRepo;

    public CadastrarFuncionario(FuncionarioRepository funcionarioRepo) {
        this.funcionarioRepo = funcionarioRepo;
    }

    public void executar(Funcionario funcionario) {

        if (funcionario == null) {
            throw new IllegalArgumentException("Funcionário não informado.");
        }

        int codigo = funcionario.getCodigo();
        ValidadorCodigo.validarCodigo(codigo);

        Funcionario funcionarioExistente =
                funcionarioRepo.buscarPorCodigo(codigo);

        if (funcionarioExistente != null) {
            throw new IllegalArgumentException("Funcionário já cadastrado!");
        }

        funcionarioRepo.salvar(funcionario);
    }
}
