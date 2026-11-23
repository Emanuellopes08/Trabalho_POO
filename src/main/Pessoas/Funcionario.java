package Pessoas;

/**
 * Representa um funcionário do banco, identificado por usuário, senha, código e cargo (RN8).
 */
public class Funcionario {

    private String usuario;
    private String senha;
    private final int codigo;
    private String cargo;

    /**
     * Cria um funcionário.
     *
     * @param usuario nome de usuário (obrigatório)
     * @param senha   senha de acesso (obrigatória)
     * @param codigo  código de identificação
     * @param cargo   cargo exercido (obrigatório)
     * @throws IllegalArgumentException se usuário, senha ou cargo forem vazios
     */
    public Funcionario(String usuario, String senha, int codigo, String cargo) {
        exigirTexto(usuario, "O usuário é obrigatório.");
        exigirTexto(senha, "A senha é obrigatória.");
        exigirTexto(cargo, "O cargo é obrigatório.");
        this.usuario = usuario;
        this.senha = senha;
        this.codigo = codigo;
        this.cargo = cargo;
    }

    /** Lança exceção se o texto for nulo ou estiver em branco. */
    private static void exigirTexto(String texto, String mensagem) {
        if (texto == null || texto.isBlank()) {
            throw new IllegalArgumentException(mensagem);
        }
    }
    /** @return o nome de usuário */
    public String getUsuario() {
        return usuario;
    }

    /** @return a senha */
    public String getSenha() {
        return senha;
    }

    /** @return o código do funcionário (não pode ser alterado) */
    public int getCodigo() {
        return codigo;
    }

    /** @return o cargo */
    public String getCargo() {
        return cargo;
    }

    /**
     * @param usuario novo usuário (obrigatório)
     * @throws IllegalArgumentException se for vazio
     */
    public void setUsuario(String usuario) {
        exigirTexto(usuario, "O usuário é obrigatório.");
        this.usuario = usuario;
    }

    /**
     * @param senha nova senha (obrigatória)
     * @throws IllegalArgumentException se for vazia
     */
    public void setSenha(String senha) {
        exigirTexto(senha, "A senha é obrigatória.");
        this.senha = senha;
    }

    /**
     * @param cargo novo cargo (obrigatório)
     * @throws IllegalArgumentException se for vazio
     */
    public void setCargo(String cargo) {
        exigirTexto(cargo, "O cargo é obrigatório.");
        this.cargo = cargo;
    }
}