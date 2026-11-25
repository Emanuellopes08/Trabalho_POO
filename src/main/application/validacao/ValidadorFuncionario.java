package application.validacao;

import domain.pessoas.Funcionario;

/**
 * Classe utilitária responsável pela validação
 * dos dados dos funcionários do sistema bancário.
 */
public final class ValidadorFuncionario {

    private ValidadorFuncionario() {
    }

    /**
     * Valida o código de identificação do funcionário.
     *
     * @param codigo código do funcionário.
     * @throws IllegalArgumentException se o código for negativo.
     */
    public static void validarCodigo(int codigo) {
        if (codigo < 0) {
            throw new IllegalArgumentException("Código inválido.");
        }
    }

    /**
     * Valida o código, o usuário, a senha e o cargo
     * do funcionário.
     *
     * @param funcionario funcionário a ser validado.
     * @throws IllegalArgumentException se o funcionário for nulo
     * ou apresentar algum dos dados inválidos.
     */
    public static void validarFuncionario(Funcionario funcionario) {

        if (funcionario == null) {throw new IllegalArgumentException("Funcionário não informado.");
        }

        validarCodigo(funcionario.getCodigo());

        if (funcionario.getUsuario() == null || funcionario.getUsuario().isBlank()) {
            throw new IllegalArgumentException("Usuário não informado.");
        }

        if (funcionario.getSenha() == null || funcionario.getSenha().isEmpty()) {
            throw new IllegalArgumentException("Senha não informada.");
        }

        if (funcionario.getSenha().length() < 8) {
            throw new IllegalArgumentException("A senha deve ter no mínimo 8 caracteres.");
        }

        if (funcionario.getCargo() == null || funcionario.getCargo().isBlank()) {
            throw new IllegalArgumentException("Cargo não informado.");
        }
    }
}