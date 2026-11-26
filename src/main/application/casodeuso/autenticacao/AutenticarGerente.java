package application.casodeuso.autenticacao;

import application.repositories.FuncionarioRepository;
import domain.pessoas.Funcionario;

/**
 * Caso de uso responsável pela autenticação e autorização
 * de acesso dos gerentes ao sistema bancário.
 * Reutiliza a autenticação de funcionários, evitando
 * a duplicação das validações de credenciais.
 */
public class AutenticarGerente {

    private final AutenticarFuncionario autenticarFuncionario;

    /**
     * Inicializa o caso de uso de autenticação do gerente,
     * utilizando a autenticação de funcionários.
     *
     * @param funcionarioRepo repositório de funcionários.
     */
    public AutenticarGerente(FuncionarioRepository funcionarioRepo) {
        this.autenticarFuncionario = new AutenticarFuncionario(funcionarioRepo);
    }

    /**
     * Autentica o funcionário e verifica se possui
     * autorização para acessar a área gerencial.
     *
     * @param codigo código de identificação do gerente.
     * @param usuario usuário de acesso do gerente.
     * @param senha senha de acesso do gerente.
     * @return funcionário autenticado com cargo de gerente.
     * @throws IllegalArgumentException se as credenciais forem
     * inválidas ou se o funcionário não possuir cargo de gerente.
     */
    public Funcionario executar(int codigo, String usuario, String senha) {
        Funcionario funcionario = autenticarFuncionario.executar(codigo, usuario, senha);

        if (!"Gerente".equals(funcionario.getCargo())) {
            throw new IllegalArgumentException("Acesso não autorizado.");
        }
        return funcionario;
    }
}