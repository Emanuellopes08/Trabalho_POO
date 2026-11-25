package application.casodeuso.autenticacao;

import application.repositories.FuncionarioRepository;
import application.validacao.ValidadorFuncionario;
import domain.pessoas.Funcionario;

/**
 * Caso de uso responsável pela autenticação dos funcionários
 * no sistema bancário.
 */
public class AutenticarFuncionario {

    private final FuncionarioRepository funcionarioRepo;

    /**
     * Inicializa o caso de uso com o repositório de funcionários.
     *
     * @param funcionarioRepo repositório de funcionários.
     */
    public AutenticarFuncionario(FuncionarioRepository funcionarioRepo) {
        this.funcionarioRepo = funcionarioRepo;
    }

    /**
     * Autentica o funcionário por meio do código de identificação,
     * usuário e senha.
     *
     * @param codigo código de identificação do funcionário.
     * @param usuario usuário de acesso do funcionário.
     * @param senha senha de acesso do funcionário.
     * @return funcionário autenticado.
     * @throws IllegalArgumentException se o código, o usuário
     * ou a senha forem inválidos.
     */
    public Funcionario executar(int codigo, String usuario, String senha) {
        ValidadorFuncionario.validarCodigo(codigo);

        if (usuario == null || usuario.isBlank()) {
            throw new IllegalArgumentException("Usuário não informado.");
        }

        if (senha == null || senha.isBlank()) {
            throw new IllegalArgumentException("Senha não informada.");
        }

        Funcionario funcionario = funcionarioRepo.buscarPorCodigo(codigo);

        if (funcionario == null || !usuario.equals(funcionario.getUsuario()) || !senha.equals(funcionario.getSenha())) {
            throw new IllegalArgumentException("Credenciais inválidas.");
        }
        return funcionario;
    }
}