public class Gerente extends Funcionario {

    public Gerente(String usuario, String senha, int codigo) {
        super(usuario, senha, codigo, "Gerente");
    }

    public void emitirRelatorio() {
        System.out.println("Emitindo relatório do gerente.");
    }
}