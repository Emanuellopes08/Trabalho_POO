public class Operacao {
    private double saldo = 0;
    private double deposito;
    private double saque;

    // Os sets para poder inserir informações (Deposito e saque)
    public void setDeposito(double deposito) {
        this.deposito = deposito;
    }
    public void setSaque(double saque){
        this.saque = saque;
    }

    //get para poder chamar as informações (Deposito, saque e Saldo)
    public double getDeposito() {
        return deposito;
    }
    public double getSaque() {
        return saque;
    }
    public double getSaldo() {
        return saldo;
    }

    //para aumentar o saldo
    public void efetuarDeposito(){
        this.saldo += deposito;
    }

    //para diminuir o saldo
    public void efetuarSaque(){
        this.saldo -= saque;
    }
}

