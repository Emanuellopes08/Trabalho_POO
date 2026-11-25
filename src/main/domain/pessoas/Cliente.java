package domain.pessoas;

import java.time.LocalDate;

/**
 * Representa um cliente do banco.
 * A classe apenas guarda e protege os dados do cliente. A leitura de dados
 * pelo terminal ou pelas telas é responsabilidade da camada de interface (SRP).
 */
public class Cliente {

    private String nome;
    private final String cpf;
    private final LocalDate dataNascimento;
    private double renda;
    private String telefone;
    private String endereco;
    private final String nomePai;
    private final String nomeMae;
    private String email;
    private String senha;

    /**
     * Construtor compatível com o código antigo.
     * Mantido para não quebrar testes e outras partes que ainda não informam senha.
     */
    public Cliente(String nome, String cpf, LocalDate dataNascimento, double renda,
                   String telefone, String endereco, String nomePai, String nomeMae,
                   String email) {
        this(nome, cpf, dataNascimento, renda, telefone, endereco, nomePai, nomeMae,
                email, null);
    }

    /**
     * Cria um cliente com senha.
     *
     * @param nome nome completo (obrigatório)
     * @param cpf CPF (obrigatório)
     * @param dataNascimento data de nascimento (obrigatória)
     * @param renda renda mensal (não pode ser negativa)
     * @param telefone telefone de contato
     * @param endereco endereço
     * @param nomePai nome do pai
     * @param nomeMae nome da mãe
     * @param email email
     * @param senha senha do cliente (mínimo de 8 caracteres)
     */
    public Cliente(String nome, String cpf, LocalDate dataNascimento, double renda,
                   String telefone, String endereco, String nomePai, String nomeMae,
                   String email, String senha) {
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
        this.senha = senha;

        if (senha != null) {
            exigirSenhaValida(senha);
        }
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

    /** Valida a senha informada no cadastro. */
    private static void exigirSenhaValida(String senha) {
        if (senha == null || senha.isBlank()) {
            throw new IllegalArgumentException("A senha é obrigatória.");
        }
        if (senha.length() < 8) {
            throw new IllegalArgumentException("A senha deve ter pelo menos 8 caracteres.");
        }
    }

    /** @return o nome do cliente */
    public String getNome() {
        return nome;
    }

    /** @param nome novo nome (obrigatório) */
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

    /** @param renda nova renda (não pode ser negativa) */
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

    /** @return a senha cadastrada; pode ser null em clientes criados pelo construtor antigo */
    public String getSenha() {
        return senha;
    }

    /**
     * Altera a senha do cliente.
     * Senhas devem ser armazenadas com hash antes de persistir em produção.
     */
    public void setSenha(String senha) {
        exigirSenhaValida(senha);
        this.senha = senha;
    }

    /** @return nome e CPF do cliente */
    @Override
    public String toString() {
        return nome + " (CPF: " + cpf + ")";
    }
}
