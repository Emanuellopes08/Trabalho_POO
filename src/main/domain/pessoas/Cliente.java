package domain.pessoas;

import java.time.LocalDate;

/**
 *Representa um cliente do banco.
 *A classe apenas guarda e protege os dados do cliente. A leitura de dados pelo terminal ou pelas tela é responsabilidade da camada de interface (SRP).
 */
public class Cliente{

    private String nome;
    private final String cpf;
    private final LocalDate dataNascimento;
    private double renda;
    private String telefone;
    private String endereco;
    private final String nomePai;
    private final String nomeMae;
    private String email;

    /**
     * Cria um cliente.
     *
     * @param nome           nome completo (obrigatório)
     * @param cpf            CPF (obrigatório)
     * @param dataNascimento data de nascimento (obrigatória)
     * @param renda          renda mensal (não pode ser negativa)
     * @param telefone       telefone de contato
     * @param endereco       endereço
     * @param nomePai        nome do pai
     * @param nomeMae        nome da mãe
     * @param email          email
     * @throws IllegalArgumentException se nome, CPF ou data forem vazios, ou a renda for negativa
     */
    // Construtor completo
    public Cliente(String nome, String cpf, LocalDate dataNascimento, double renda, String telefone, String endereco, String nomePai, String nomeMae, String email) {
        exigirTexto(nome, "O nome é obrigatório.");
        exigirTexto(cpf, "O CPF é obrigatório.");
        if (dataNascimento == null) {
            throw new IllegalArgumentException("A data de nascimento é obrigatória.");
        }
        exigirRendaValida(renda);

        this.nome = nome;
        this.cpf = cpf;
        this.dataNascimento = dataNascimento;
        this.renda = renda;
        this.telefone = telefone;
        this.endereco = endereco;
        this.nomePai = nomePai;
        this.nomeMae = nomeMae;
        this.email = email;
    }

    /** Lança exceção se o texto for nulo ou estiver em branco. */
    private static void exigirTexto(String texto, String mensagem) {
        if (texto == null || texto.isBlank()) {
            throw new IllegalArgumentException(mensagem);
        }
    }

    /** Lança exceção se a renda for negativa ou inválida. */
    private static void exigirRendaValida(double renda) {
        if (Double.isNaN(renda) || renda < 0) {
            throw new IllegalArgumentException("A renda não pode ser negativa.");
        }
    }



    /** @return o nome do cliente*/
    public String getNome() {
        return nome;
    }

    /**  @param nome novo nome (obrigatório)
     * @throws IllegalArgumentException se o nome for vazio
     */

    public void setNome(String nome) {
        exigirTexto(nome, "O nome é obrigatório.");
        this.nome = nome;
    }

    /** @return o CPF do cliente (não pode ser alterado depois do cadastro) */
    public String getCpf() {
        return cpf;
    }

    /** @return a data de nascimento */
    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    /** @return a renda do cliente */
    public double getRenda() {
        return renda;
    }

    /**
     * @param renda nova renda (não pode ser negativa)
     * @throws IllegalArgumentException se a renda for negativa
     */
    public void setRenda(double renda) {
        exigirRendaValida(renda);
        this.renda = renda;
    }

    /** @return o telefone */
    public String getTelefone() {
        return telefone;
    }

    /** @param telefone novo telefone */
    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    /** @return o endereço */
    public String getEndereco() {
        return endereco;
    }

    /** @param endereco novo endereço */
    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    /** @return o nome do pai */
    public String getNomePai() {
        return nomePai;
    }

    /** @return o nome da mãe */
    public String getNomeMae() {
        return nomeMae;
    }

    /** @return o email */
    public String getEmail() {
        return email;
    }

    /** @param email novo email */
    public void setEmail(String email) {
        this.email = email;
    }

    //para acusar erro
    /** @return nome e CPF do cliente */
    @Override
    public String toString() {
        return nome + " (CPF: " + cpf + ")";
    }
}