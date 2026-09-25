public class Gerente extends Funcionario {

    private String nome = "Dorivaldo Silva";
    private String senha = "Dori123456$";
    private int codigo = 1;
    private String Cargo = "Gerente";

    // Construtor
    public Gerente(String nome, String senha, int codigo) {
        this.nome = nome;
        this.senha = senha;
        this.codigo = codigo;
    }

    public void EmitirRelatorio() {
        System.out.println("Emitindo relatório do gerente: " + nome);
    }

    public void 
    // get para retornar o nome
    public String getNome() {
        return nome;
    }

    // get para retornar a senha
    public String getSenha() {
        return senha;
    }

    // get para retornar o código
    public int getCodigo() {
        return codigo;
    }
}