package application.validacao;

/**
 * Classe utilitária responsável pela validação
 * dos valores utilizados nas operações bancárias.
 */
public final class ValidadorValor {

    private ValidadorValor() {
    }

    /**
     * Verifica se o valor informado é finito e maior que zero.
     *
     * @param valor valor monetário da operação bancária.
     * @throws IllegalArgumentException se o valor for menor
     * ou igual a zero, infinito ou NaN.
     */
    public static void validarValor(double valor) {
        if (!Double.isFinite(valor) || valor <= 0) {
            throw new IllegalArgumentException("Valor inválido.");
        }
    }
}