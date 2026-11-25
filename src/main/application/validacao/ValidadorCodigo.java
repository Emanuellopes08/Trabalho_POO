package application.validacao;

public final class ValidadorCodigo {

    private ValidadorCodigo() {
    }

    public static void validarCodigo(int codigo) {
        if (codigo < 0) {
            throw new IllegalArgumentException("Código inválido.");
        }
    }
}