package application.validacao;

public class ValidadorCpf {

    private ValidadorCpf() {
    }

    public static void validarCpf(String cpf) {
        if (cpf == null || cpf.isBlank()) {
            throw new IllegalArgumentException("CPF não informado.");
        }
    }
}