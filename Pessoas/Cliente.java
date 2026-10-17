package Pessoas;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class Cliente{

    private String nome;
    private String cpf;
    private LocalDate dataNascimento;
    private double renda;
    private String telefone;
    private String endereco;
    private String nomePai;
    private String nomeMae;
    private String email;

    // Construtor completo
    public Cliente(String nome, String cpf, LocalDate dataNascimento, double renda, String telefone, String endereco, String nomePai, String nomeMae, String email) {
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

    // Metodo que pede os dados ao usuário
    public void solicitarDados() {
        Scanner sc = new Scanner(System.in);
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        System.out.print("Nome: ");
        this.nome = sc.nextLine();

        System.out.print("CPF: ");
        this.cpf = sc.nextLine();

        // DateTimeFormatter é a classe que define o "formato" (padrão) em que uma data
        // vai ser lida ou escrita como texto.
        // O metodo estático "ofPattern(...)" recebe uma String descrevendo o padrão desejado:
        //   dd -> dia com 2 dígitos (ex: 05)
        //   MM -> mês com 2 dígitos (ex: 03)
        //   yyyy -> ano com 4 dígitos (ex: 1998)
        // Ou seja, esse formatador vai entender/exigir datas no formato "dia/mes/ano".
        // Criamos o objeto "formato" para poder usar depois, ao converter texto em LocalDate.
      
        System.out.print("Data de nascimento (dd/MM/yyyy): ");
        this.dataNascimento = LocalDate.parse(sc.nextLine(), formato);

        System.out.print("Renda: ");
        this.renda = Double.parseDouble(sc.nextLine());

        System.out.print("Telefone: ");
        this.telefone = sc.nextLine();

        System.out.print("Endereço: ");
        this.endereco = sc.nextLine();

        System.out.print("Nome do pai: ");
        this.nomePai = sc.nextLine();

        System.out.print("Nome da mãe: ");
        this.nomeMae = sc.nextLine();

        System.out.print("Email: ");
        this.email = sc.nextLine();
    }

    // Getters e Setters
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(LocalDate dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public double getRenda() {
        return renda;
    }

    public void setRenda(double renda) {
        this.renda = renda;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getNomePai() {
        return nomePai;
    }

    public void setNomePai(String nomePai) {
        this.nomePai = nomePai;
    }

    public String getNomeMae() {
        return nomeMae;
    }

    public void setNomeMae(String nomeMae) {
        this.nomeMae = nomeMae;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    //para acusar erro
    @Override
    public String toString() {
        return nome + cpf + dataNascimento +  renda + telefone  + endereco  + nomePai  + nomeMae + email;
}
}
