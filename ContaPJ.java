public class ContaPJ extends Conta {

    CadastroCliente cliente;

    private String CNPJ;
    private String nomeEmpresa;
    private double faturamentoEmpresa;


    public ContaPJ(String CNPJ, String nomeEmpresa, double faturaEmpresa, CadastroCliente cliente, boolean cnpjAtivo){

        this.CNPJ = CNPJ;
        this.nomeEmpresa = nomeEmpresa;
        this.faturamentoEmpresa = faturaEmpresa;
        this.cliente = cliente;

    }

    public double calcularTaxa(){
        return 25.00;
    }

    // Gets e Sets
    public String getNomeEmpresa(){
        return nomeEmpresa;
    }

    public void setNomeEmpresa(String nomeEmpresa){
        this.nomeEmpresa = nomeEmpresa;
    }

    public double getFaturamento(){
        return faturamentoEmpresa;
    }

    private void setCNPJ(String CNPJ){
        this.CNPJ = CNPJ;
    }

    public String getCNPJ(){
        return CNPJ;
    }
}
