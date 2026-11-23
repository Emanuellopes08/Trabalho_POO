package Contas;

/**
 * Indica que um saque foi solicitado com valor acima do saldo disponível da conta.
 * É uma exceção não verificada, então as telas não são obrigadas a tratá-la,
 * mas podem capturá-la para mostrar uma mensagem ao usuário.
 */
public class SaldoInsuficienteException extends RuntimeException {

    /**
     * Cria a exceção com uma mensagem explicando o motivo.
     *
     * @param mensagem descrição do erro
     */
    public SaldoInsuficienteException(String mensagem) {
        super(mensagem);
    }
}