public class ContaPJ extends Conta {

    CadastroCliente cliente;

    private boolean cnpjAtivo = true; // verifica se o CNPJ está ativo ou não
    private int CNPJ;
    private String nomeEmpresa;
    private double faturaEmpresa;


    ContaPJ(int CNPJ, String nomeEmpresa, double faturaEmpresa, CadastroCliente cliente, boolean cnpjAtivo){

        this.CNPJ = CNPJ;
        this.nomeEmpresa = nomeEmpresa;
        this.faturaEmpresa = faturaEmpresa;
        this.cliente = cliente;
        this.cnpjAtivo = cnpjAtivo;
    }

    // Gets e Sets
    public String getNomeEmpresa(){
        return nomeEmpresa;
    }

    public void setNomeEmpresa(String nomeEmpresa){
        this.nomeEmpresa = nomeEmpresa;
    }

    public double getFatura(){
        return faturaEmpresa;
    }

    private void setCNPJ(int CNPJ){
        this.CNPJ = CNPJ;
    }

    public int getCNPJ(){
        return CNPJ;
    }
}
