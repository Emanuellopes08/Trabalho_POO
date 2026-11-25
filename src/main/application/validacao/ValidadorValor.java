package application.validacao;

public final class ValidadorValor {

    private ValidadorValor() {
    }

    public static void validarValor(double valor) {
        if (!Double.isFinite(valor) || valor <= 0) {
            throw new IllegalArgumentException("Valor inválido.");
        }
    }
}