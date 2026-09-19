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

    // get para retornar a senha
    public String getSenha() {
        return senha;
    }

    // get para retornar o código
    public int getCodigo() {
        return codigo;
    }
}