public class Funcionario {

    private String usuario;
    private String senha;
    private int codigo;
    private String cargo;

    // Construtor da classe Funcionario
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
    /////////////////////////////////////////////////////////////////////
    // ALTERA O USUÁRIO
    /////////////////////////////////////////////////////////////////////

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    /////////////////////////////////////////////////////////////////////
    // ALTERA A SENHA
    /////////////////////////////////////////////////////////////////////

    public void setSenha(String senha) {
        this.senha = senha;
    }

    /////////////////////////////////////////////////////////////////////
    // ALTERA O CARGO
    /////////////////////////////////////////////////////////////////////

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }
}