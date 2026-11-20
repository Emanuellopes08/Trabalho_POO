package Contas;
import Pessoas.Cliente;

public class ContaPJ extends Conta {

    private String cnpj;
    private String nomeEmpresa;
    private double faturaEmpresa;

    public ContaPJ(String cnpj, String nomeEmpresa,
                   double faturaEmpresa, Cliente cliente) {

        super(TipoConta.PJ, cliente);

        this.cnpj = cnpj;
        this.nomeEmpresa = nomeEmpresa;
        this.faturaEmpresa = faturaEmpresa;

    }

    public String getNomeEmpresa() {
        return nomeEmpresa;
    }

    public void setNomeEmpresa(String nomeEmpresa) {
        this.nomeEmpresa = nomeEmpresa;
    }

    public double getFatura() {
        return faturaEmpresa;
    }

    public String getCnpj() {
        return cnpj;
    }
}