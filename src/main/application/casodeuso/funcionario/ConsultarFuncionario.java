
package application.casodeuso.funcionario;

import application.repositories.FuncionarioConsultaRepository;
import application.validacao.ValidadorFuncionario;
import domain.pessoas.Funcionario;

/**
 * Caso de uso responsável pela consulta de funcionários
 * por meio do código de identificação.
 */
public class ConsultarFuncionario {

    private final FuncionarioConsultaRepository funcionarioRepo;

    /**
     * Inicializa o caso de uso com o contrato de consulta
     * de funcionários.
     *
     * @param funcionarioRepo contrato responsável pela consulta
     * dos dados dos funcionários.
     */
    public ConsultarFuncionario(
            FuncionarioConsultaRepository funcionarioRepo) {
        this.funcionarioRepo = funcionarioRepo;
    }

    /**
     * Valida o código informado e consulta o funcionário
     * cadastrado no repositório.
     *
     * @param codigo código do funcionário a ser consultado.
     * @return funcionário encontrado no repositório.
     * @throws IllegalArgumentException se o código for inválido
     * ou se o funcionário não for encontrado.
     */
    public Funcionario executar(int codigo) {

        ValidadorFuncionario.validarCodigo(codigo);

        Funcionario funcionario = funcionarioRepo.buscarPorCodigo(codigo);

        if (funcionario == null) {
            throw new IllegalArgumentException("Funcionário não encontrado.");
        }

        return funcionario;
    }
}
