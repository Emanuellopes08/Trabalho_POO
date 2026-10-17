package Pessoas;

public class Funcionario {

    private String usuario;
    private String senha;
    private int codigo;
    private String cargo;

    // Construtor da classe Pessoas.Funcionario
    public Funcionario(String usuario, String senha, int codigo, String cargo) {
        this.usuario = usuario;
        this.senha = senha;
        this.codigo = codigo;
        this.cargo = cargo;
    }

    public String getUsuario() {
        return usuario;
    }

    public String getSenha() {
        return senha;
    }

    public int getCodigo() {
        return codigo;
    }

    public String getCargo() {
        return cargo;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }
}