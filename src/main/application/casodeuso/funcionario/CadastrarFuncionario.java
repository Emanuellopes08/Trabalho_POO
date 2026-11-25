package application.casodeuso.funcionario;

import application.validacao.ValidadorFuncionario;
import domain.pessoas.Funcionario;
import application.repositories.FuncionarioRepository;

public class CadastrarFuncionario{

    private final FuncionarioRepository funcionarioRepo;

    public CadastrarFuncionario(FuncionarioRepository funcionarioRepo) {
        this.funcionarioRepo = funcionarioRepo;
    }

    public void executar(Funcionario funcionario) {

        int codigo = funcionario.getCodigo();
        ValidadorFuncionario.validarFuncionario(funcionario);

        Funcionario funcionarioExistente = funcionarioRepo.buscarPorCodigo(codigo);

        if (funcionarioExistente != null) {
            throw new IllegalArgumentException("Funcionário já cadastrado!");
        }

        funcionarioRepo.salvar(funcionario);
    }
}
