public class ContaConjunta extends Conta{
    private String titular;
    private String cocliente;


     ContaConjunta(String titular, String cocliente){
        this.titular=titular;
        this.cocliente=cocliente;

    }

    @Override
    public String toString() {
        return "Titular: " + titular + "e Cotitular: " + cocliente;
    }
}
