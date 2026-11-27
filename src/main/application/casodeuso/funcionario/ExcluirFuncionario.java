
package application.casodeuso.funcionario;

import application.repositories.FuncionarioConsultaRepository;
import application.repositories.FuncionarioExclusaoRepository;
import application.validacao.ValidadorFuncionario;
import domain.pessoas.Funcionario;

/**
 * Caso de uso responsável pela exclusão de funcionários
 * cadastrados no sistema bancário.
 */
public class ExcluirFuncionario {

    private final FuncionarioConsultaRepository consultaRepo;
    private final FuncionarioExclusaoRepository exclusaoRepo;

    /**
     * Inicializa o caso de uso com os contratos de consulta
     * e exclusão de funcionários.
     *
     * @param consultaRepo contrato responsável pela consulta.
     * @param exclusaoRepo contrato responsável pela exclusão.
     */
    public ExcluirFuncionario(
            FuncionarioConsultaRepository consultaRepo,
            FuncionarioExclusaoRepository exclusaoRepo) {

        this.consultaRepo = consultaRepo;
        this.exclusaoRepo = exclusaoRepo;
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

        Funcionario funcionario = consultaRepo.buscarPorCodigo(codigo);

        if (funcionario == null) {
            throw new IllegalArgumentException("Funcionário não encontrado.");
        }

        exclusaoRepo.excluir(codigo);
    }
}
