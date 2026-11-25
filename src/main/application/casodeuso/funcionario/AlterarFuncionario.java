package application.casodeuso.funcionario;

import application.repositories.FuncionarioRepository;
import application.validacao.ValidadorFuncionario;
import domain.pessoas.Funcionario;

/**
 * Caso de uso responsável pela alteração dos dados
 * dos funcionários cadastrados no sistema bancário.
 */
public class AlterarFuncionario {
    private final FuncionarioRepository funcionarioRepo;

    /**
     * Inicializa o caso de uso com o repositório de funcionários.
     *
     * @param funcionarioRepo repositório responsável pelo acesso
     * aos dados dos funcionários.
     */
    public AlterarFuncionario(FuncionarioRepository funcionarioRepo) {
        this.funcionarioRepo = funcionarioRepo;
    }

    /**
     * Valida os dados do funcionário, verifica sua existência
     * e solicita a atualização de seus dados no repositório.
     *
     * @param funcionario funcionário com os dados atualizados.
     * @throws IllegalArgumentException se o funcionário for nulo,
     * apresentar dados inválidos ou não estiver cadastrado.
     */
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