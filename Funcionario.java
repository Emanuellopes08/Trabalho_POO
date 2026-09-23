public class Funcionario extends Administrador{

    //contrutor da classe funcionario
    public Funcionario(String usuario, String senha, int codigo, String Cargo){
        this.usuario = usuario;
        this.senha = senha;
        this.codigo = codigo;
        this.Cargo = Cargo;
    }

    private String Cargo;

    //get para retormar o cargo do funcionario
    public String getCargo(){
        return Cargo;
    }

}
