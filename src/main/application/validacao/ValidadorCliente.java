package application.validacao;

import domain.pessoas.Cliente;

/**
 * Classe utilitária responsável pela validação
 * dos dados dos clientes do sistema bancário.
 */
public final class ValidadorCliente {

    private ValidadorCliente() {
    }

    /**
     * Valida o preenchimento do CPF do cliente.
     *
     * @param cpf CPF informado.
     * @throws IllegalArgumentException se o CPF for nulo,
     * vazio ou composto apenas por espaços.
     */
    public static void validarCpf(String cpf) {
        if (cpf == null || cpf.isBlank()) {
            throw new IllegalArgumentException("CPF não informado.");
        }
    }

    /**
     * Valida o preenchimento e o tamanho mínimo da senha.
     *
     * @param senha senha informada pelo cliente.
     * @throws IllegalArgumentException se a senha não for
     * informada ou possuir menos de oito caracteres.
     */
    public static void validarSenha(String senha) {

        if (senha == null || senha.isBlank()) {
            throw new IllegalArgumentException("Senha não informada.");
        }

        if (senha.length() < 8) {
            throw new IllegalArgumentException("A senha deve ter no mínimo 8 caracteres.");
        }
    }

    /**
     * Valida o CPF e a senha do cliente.
     *
     * @param cliente cliente a ser validado.
     * @throws IllegalArgumentException se o cliente for nulo
     * ou apresentar CPF ou senha inválidos.
     */
    public static void validarCliente(Cliente cliente) {

        if (cliente == null) {
            throw new IllegalArgumentException("Cliente não informado.");
        }

        validarCpf(cliente.getCpf());
        validarSenha(cliente.getSenha());
    }
}