package BancoDados;

public class TesteBanco {

    public static void main(String[] args) {

        try {

            Conexao.criarTabelas();

            System.out.println("Conexão com SQLite realizada com sucesso!");
            System.out.println("Tabelas criadas com sucesso!");

        } catch (Exception e) {

            System.out.println("Erro ao configurar o banco de dados:");
            e.printStackTrace();

        }
    }
}