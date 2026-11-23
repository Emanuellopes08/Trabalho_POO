package Contas;
import Pessoas.Cliente;

/**
 * Conta para pessoa jurídica (RN7), com dados da empresa.
 * Depósito e saque seguem exatamente a regra de {@link Conta}.
 */
public class ContaPJ extends Conta {

    private final String cnpj;
    private String nomeEmpresa;
    private final double faturaEmpresa;

    /**
     * Cria uma conta PJ com saldo zero.
     * @param cnpj          CNPJ da empresa (obrigatório)
     * @param nomeEmpresa   nome da empresa (obrigatório)
     * @param faturaEmpresa faturamento da empresa (não pode ser negativo)
     * @param cliente       titular da conta
     * @throws IllegalArgumentException se CNPJ ou nome estiverem vazios, ou o faturamento for negativo
     */

    public ContaPJ(String cnpj, String nomeEmpresa, double faturaEmpresa, Cliente cliente) {
        super(TipoConta.PJ, cliente);
        if (cnpj == null || cnpj.isBlank()) {
            throw new IllegalArgumentException("O CNPJ é obrigatório.");
        }
        if (nomeEmpresa == null || nomeEmpresa.isBlank()) {
            throw new IllegalArgumentException("O nome da empresa é obrigatório.");
        }
        if (faturaEmpresa < 0 || Double.isNaN(faturaEmpresa)) {
            throw new IllegalArgumentException("O faturamento não pode ser negativo.");
        }
        this.cnpj = cnpj;
        this.nomeEmpresa = nomeEmpresa;
        this.faturaEmpresa = faturaEmpresa;
    }

    /**
     * @return o nome da empresa
     */
    public String getNomeEmpresa() {
        return nomeEmpresa;
    }

    /**
     * Altera o nome da empresa.
     * @param nomeEmpresa novo nome (não pode ser vazio)
     * @throws IllegalArgumentException se o nome for nulo ou vazio
     */
    public void setNomeEmpresa(String nomeEmpresa) {
        if (nomeEmpresa == null || nomeEmpresa.isBlank()) {
            throw new IllegalArgumentException("O nome da empresa é obrigatório.");
        }
        this.nomeEmpresa = nomeEmpresa;
    }

    /**
     * @return o faturamento da empresa
     */
    public double getFatura() {
        return faturaEmpresa;
    }

    /**
     * @return o CNPJ da empresa
     */
    public String getCnpj() {
        return cnpj;
    }
}