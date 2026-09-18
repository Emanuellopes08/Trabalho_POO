public class ContaCorrente extends Conta{
    private double mensalidade;

    public ContaCorrente(double mensalidade, Cliente cliente){
        this.mensalidade = mensalidade;
        this.cliente = cliente;
    }

    public double consultarMensalidade(){
        return mensalidade;
    }

}
