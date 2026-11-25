package domain.pessoas;
/**
 * Representa o gerente do banco, que é um {@link Funcionario} com cargo "Gerente"
 * e responsável pelo gerenciamento dos funcionários (RN9 e RN10).
 */
public class Gerente extends Funcionario {

    /**
     * Cria um gerente.
     *
     * @param usuario nome de usuário
     * @param senha   senha de acesso
     * @param codigo  código de identificação
     */
    public Gerente(String usuario, String senha, int codigo) {
        super(usuario, senha, codigo, "Gerente");
    }

    /**
     * Monta o texto do relatório do gerente. Quem chamar decide onde mostrar
     * (terminal ou tela), porque a entidade não imprime nada.
     *
     * @return o texto do relatório
     */
    public String emitirRelatorio() {
        return "Relatório do gerente " + getUsuario() + " (código " + getCodigo() + ").";
    }
}