public class ContaCorrente extends Conta{
    private double mensalidade = 20.00;

    public ContaCorrente(Cliente cliente){
        super(TipoConta.CORRENTE, cliente);
    }

    public double consultarMensalidade(){
        return mensalidade;
    }

}
