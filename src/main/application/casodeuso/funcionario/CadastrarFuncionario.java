package application.casodeuso.funcionario;

import application.validacao.ValidadorFuncionario;
import domain.pessoas.Funcionario;
import application.repositories.FuncionarioRepository;

/**
 * Caso de uso responsável pelo cadastro de funcionários
 * no sistema bancário.
 */
public class CadastrarFuncionario{
    private final FuncionarioRepository funcionarioRepo;

    /**
     * Inicializa o caso de uso com o repositório de funcionários.
     *
     * @param funcionarioRepo repositório responsável pelo acesso
     * aos dados dos funcionários.
     */
    public CadastrarFuncionario(FuncionarioRepository funcionarioRepo) {
        this.funcionarioRepo = funcionarioRepo;
    }

    /**
     * Valida os dados do funcionário, verifica se ele já está
     * cadastrado e solicita seu cadastro no repositório.
     *
     * @param funcionario funcionário a ser cadastrado.
     * @throws IllegalArgumentException se os dados forem inválidos
     * ou se o funcionário já estiver cadastrado.
     */
    public void executar(Funcionario funcionario) {
        ValidadorFuncionario.validarFuncionario(funcionario);
        int codigo = funcionario.getCodigo();

        Funcionario funcionarioExistente = funcionarioRepo.buscarPorCodigo(codigo);

        if (funcionarioExistente != null) {
            throw new IllegalArgumentException("Funcionário já cadastrado!");
        }
        funcionarioRepo.salvar(funcionario);
    }
}
