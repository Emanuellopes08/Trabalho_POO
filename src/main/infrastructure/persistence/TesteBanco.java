package infrastructure.persistence;

public class TesteBanco {

    public static void main(String[] args) {

        try {

            // Cria as tabelas do banco de dados
            Conexao.criarTabelas();

            System.out.println("Conexão com SQLite realizada com sucesso!");
            System.out.println("Tabelas criadas com sucesso!");

            // Consulta as tabelas existentes no banco
            try (var conexao = Conexao.conectar();
                 var statement = conexao.createStatement();
                 var resultado = statement.executeQuery(
                         "SELECT name FROM sqlite_master WHERE type='table'")) {

                System.out.println("\nTabelas encontradas:");

                while (resultado.next()) {
                    System.out.println("- " + resultado.getString("name"));
                }
            }

        } catch (Exception e) {

            System.out.println("Erro ao configurar o banco de dados:");
            e.printStackTrace();

        }
    }
}