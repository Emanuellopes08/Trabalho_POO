package application.casodeuso.funcionario;

import application.repositories.FuncionarioRepository;
import application.validacao.ValidadorFuncionario;
import domain.pessoas.Funcionario;

/**
 * Caso de uso responsável pela exclusão de funcionários
 * cadastrados no sistema bancário.
 */
public class ExcluirFuncionario {

    private final FuncionarioRepository funcionarioRepo;

    /**
     * Inicializa o caso de uso com o repositório de funcionários.
     *
     * @param funcionarioRepo repositório responsável pelo acesso
     * aos dados dos funcionários.
     */
    public ExcluirFuncionario(FuncionarioRepository funcionarioRepo) {
        this.funcionarioRepo = funcionarioRepo;
    }

    /**
     * Valida o código, verifica a existência do funcionário
     * e solicita sua exclusão do repositório.
     *
     * @param codigo código do funcionário a ser excluído.
     * @throws IllegalArgumentException se o código for inválido
     * ou se o funcionário não for encontrado.
     */
    public void executar(int codigo) {
        ValidadorFuncionario.validarCodigo(codigo);
        Funcionario funcionario = funcionarioRepo.buscarPorCodigo(codigo);

        if (funcionario == null) {
            throw new IllegalArgumentException("Funcionário não encontrado.");
        }
        funcionarioRepo.excluir(codigo);
    }
}