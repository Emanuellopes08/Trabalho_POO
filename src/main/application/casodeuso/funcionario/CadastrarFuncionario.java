
package application.casodeuso.funcionario;

import application.validacao.ValidadorFuncionario;
import application.repositories.FuncionarioConsultaRepository;
import application.repositories.FuncionarioCadastroRepository;
import domain.pessoas.Funcionario;

/**
 * Caso de uso responsável pelo cadastro de funcionários
 * no sistema bancário.
 */
public class CadastrarFuncionario {

    private final FuncionarioConsultaRepository consultaRepo;
    private final FuncionarioCadastroRepository cadastroRepo;

    /**
     * Inicializa o caso de uso com os contratos de consulta e cadastro.
     *
     * @param consultaRepo contrato para consulta de funcionários.
     * @param cadastroRepo contrato para cadastro de funcionários.
     */
    public CadastrarFuncionario(
            FuncionarioConsultaRepository consultaRepo,
            FuncionarioCadastroRepository cadastroRepo) {

        this.consultaRepo = consultaRepo;
        this.cadastroRepo = cadastroRepo;
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

        Funcionario funcionarioExistente =
                consultaRepo.buscarPorCodigo(codigo);

        if (funcionarioExistente != null) {
            throw new IllegalArgumentException("Funcionário já cadastrado!");
        }

        cadastroRepo.salvar(funcionario);
    }
}
