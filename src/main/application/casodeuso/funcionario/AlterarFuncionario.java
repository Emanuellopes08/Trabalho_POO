
package application.casodeuso.funcionario;

import application.repositories.FuncionarioConsultaRepository;
import application.repositories.FuncionarioAtualizacaoRepository;
import application.validacao.ValidadorFuncionario;
import domain.pessoas.Funcionario;

/**
 * Caso de uso responsável pela alteração dos dados
 * dos funcionários cadastrados no sistema bancário.
 */
public class AlterarFuncionario {

    private final FuncionarioConsultaRepository consultaRepo;
    private final FuncionarioAtualizacaoRepository atualizacaoRepo;

    /**
     * Inicializa o caso de uso com os contratos de consulta
     * e atualização de funcionários.
     *
     * @param consultaRepo contrato responsável pela consulta.
     * @param atualizacaoRepo contrato responsável pela atualização.
     */
    public AlterarFuncionario(
            FuncionarioConsultaRepository consultaRepo,
            FuncionarioAtualizacaoRepository atualizacaoRepo) {

        this.consultaRepo = consultaRepo;
        this.atualizacaoRepo = atualizacaoRepo;
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

        Funcionario funcionarioExistente =
                consultaRepo.buscarPorCodigo(codigo);

        if (funcionarioExistente == null) {
            throw new IllegalArgumentException("Funcionário não encontrado.");
        }

        atualizacaoRepo.atualizar(funcionario);
    }
}
