package infrastructure.persistence;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Classe responsável pelo gerenciamento das conexões
 * com o banco de dados SQLite do sistema Banco ES.
 *
 * Também realiza a criação das tabelas e a execução
 * de scripts necessários para inicialização do banco.
 */
public class Conexao {

    /**
     * URL de conexão com o banco de dados SQLite.
     */
    private static final String URL = "jdbc:sqlite:banco.db";

    /**
     * Estabelece uma conexão com o banco de dados SQLite
     * e ativa a verificação das chaves estrangeiras.
     *
     * @return conexão ativa com o banco de dados.
     * @throws SQLException caso ocorra algum erro ao estabelecer
     *         a conexão ou ativar as chaves estrangeiras.
     */
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

    /**
     * Cria as tabelas do banco de dados executando os comandos
     * SQL definidos no arquivo script.sql.
     *
     * Também realiza uma migração para adicionar a coluna
     * senha à tabela clientes, caso ela ainda não exista.
     *
     * O arquivo script.sql deve estar localizado em:
     * src/main/infrastructure/persistence/script.sql
     *
     * @throws SQLException caso ocorra algum erro durante a
     *         execução dos comandos SQL ou da migração.
     * @throws IOException caso ocorra algum erro na leitura
     *         do arquivo script.sql.
     */
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

            // Migração compatível com bancos criados antes da coluna de senha.
            try {
                statement.execute("ALTER TABLE clientes ADD COLUMN senha TEXT");
            } catch (SQLException colunaJaExiste) {
                if (!colunaJaExiste.getMessage().toLowerCase().contains("duplicate column")) {
                    throw colunaJaExiste;
                }
            }
        }
    }
}