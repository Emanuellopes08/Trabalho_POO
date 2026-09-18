public class Administrador {

    private String usuario;
    private String senha;
    private int codigo;

    // Construtor
    public Administrador(String usuario, String senha, int codigo) {
        this.usuario = usuario;
        this.senha = senha;
        this.codigo = codigo;
    }

    // get para retornar o usuário
    public String getUsuario() {
        return usuario;
    }

    // set para alterar ou informar o usuário
    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    // get para retornar a senha
    public String getSenha() {
        return senha;
    }

    // set para alterar ou informar a senha
    public void setSenha(String senha) {
        this.senha = senha;
    }

    // get para retornar o código
    public int getCodigo() {
        return codigo;
    }

    // set para alterar ou informar o código
    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }
}