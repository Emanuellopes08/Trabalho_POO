package application.validacao;

import domain.pessoas.Cliente;

public final class ValidadorCliente {

    private ValidadorCliente() {
    }

    public static void validarCpf(String cpf) {
        if (cpf == null || cpf.isBlank()) {
            throw new IllegalArgumentException("CPF não informado.");
        }
    }

    public static void validarSenha(String senha) {

        if (senha == null || senha.isBlank()) {
            throw new IllegalArgumentException("Senha não informada.");
        }

        if (senha.length() < 8) {
            throw new IllegalArgumentException("A senha deve ter no mínimo 8 caracteres.");
        }
    }

    public static void validarCliente(Cliente cliente) {

        if (cliente == null) {
            throw new IllegalArgumentException("Cliente não informado.");
        }

        validarCpf(cliente.getCpf());
        validarSenha(cliente.getSenha());
    }
}