
package infrastructure.persistence;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.nio.file.Files;
import java.nio.file.Path;
import java.io.IOException;

public class Conexao {

    private static final String URL = "jdbc:sqlite:banco_es.db";

    public static Connection conectar() throws SQLException {
        return DriverManager.getConnection(URL);
    }

    public static void criarTabelas() throws SQLException, IOException {

        String script = Files.readString(
                Path.of("BancoDados", "script.sql")
        );

        try (Connection conexao = conectar();
             Statement statement = conexao.createStatement()) {

            String[] comandos = script.split(";");

            for (String comando : comandos) {

                if (!comando.trim().isEmpty()) {
                    statement.execute(comando);
                }
            }
        }
    }
}   