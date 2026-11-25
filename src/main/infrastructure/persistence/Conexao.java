package infrastructure.persistence;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Conexao {

    private static final String URL = "jdbc:sqlite:banco.db";

    public static Connection conectar() throws SQLException {

        Connection conexao = DriverManager.getConnection(URL);

        try (Statement statement = conexao.createStatement()) {
            statement.execute("PRAGMA foreign_keys = ON");
        } catch (SQLException e) {
            conexao.close();
            throw e;
        }

        return conexao;
    }

    public static void criarTabelas() throws SQLException, IOException {

        String script = Files.readString(
                Path.of("src", "main", "infrastructure", "persistence", "script.sql")
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