public class ContaPoupança extends Conta {

    private double taxaRendimento = 0.5;

    public ContaPoupança(Cliente cliente) {
        super(TipoConta.POUPANCA, cliente);
    }

    public void aplicarRendimento() {
        double rendimento = getSaldo() * taxaRendimento / 100;
        efetuarDeposito(rendimento);
    }

    public double consultarRendimento() {
        return taxaRendimento;
    }
}
