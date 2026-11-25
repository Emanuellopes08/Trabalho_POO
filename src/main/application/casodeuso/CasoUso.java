package application.casodeuso;

public abstract class CasoUso {

    protected void validarCpf(String cpf) {
        if (cpf == null || cpf.isBlank()) {
            throw new IllegalArgumentException("CPF não informado.");
        }
    }

    protected void validarValor(double valor) {
        if (!Double.isFinite(valor) || valor <= 0) {
            throw new IllegalArgumentException("Valor inválido.");
        }
    }
}