package infrastructure.persistence;

import application.repositories.FuncionarioRepository;
import domain.pessoas.Funcionario;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Implementação JDBC do repositório de funcionários.
 *
 * Responsável por realizar as operações de persistência
 * dos funcionários no banco de dados SQLite, incluindo
 * cadastro, consulta, listagem, atualização e exclusão.
 *
 * Também realiza a reconstrução de objetos Funcionario
 * a partir dos registros armazenados no banco de dados.
 */
public class FuncionarioRepositoryJDBC implements FuncionarioRepository {

    /**
     * Salva um novo funcionário no banco de dados.
     *
     * @param funcionario objeto Funcionario que será persistido.
     * @throws RuntimeException caso ocorra algum erro durante
     *         a operação de persistência.
     */
    @Override
    public void salvar(Funcionario funcionario) {
        String sql = """
            INSERT INTO funcionarios (codigo, usuario, senha, cargo)
            VALUES (?, ?, ?, ?)
            """;

        try (Connection conexao = Conexao.conectar();
             PreparedStatement stmt = conexao.prepareStatement(sql)) {

            stmt.setInt(1, funcionario.getCodigo());
            stmt.setString(2, funcionario.getUsuario());
            stmt.setString(3, funcionario.getSenha());
            stmt.setString(4, funcionario.getCargo());

            stmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(
                "Erro ao salvar funcionário.", e
            );
        }
    }

    /**
     * Busca um funcionário pelo seu código de identificação.
     *
     * @param codigo código do funcionário procurado.
     * @return objeto Funcionario encontrado ou null caso não exista.
     * @throws RuntimeException caso ocorra algum erro durante
     *         a consulta ao banco de dados.
     */
    @Override
    public Funcionario buscarPorCodigo(int codigo) {
        String sql = "SELECT * FROM funcionarios WHERE codigo = ?";

        try (Connection conexao = Conexao.conectar();
             PreparedStatement stmt = conexao.prepareStatement(sql)) {

            stmt.setInt(1, codigo);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return montarFuncionario(rs);
                }
            }

            return null;

        } catch (SQLException e) {
            throw new RuntimeException(
                "Erro ao buscar funcionário por código.", e
            );
        }
    }

    /**
     * Busca um funcionário pelo seu nome de usuário.
     *
     * @param usuario nome de usuário utilizado na consulta.
     * @return objeto Funcionario encontrado ou null caso não exista.
     * @throws RuntimeException caso ocorra algum erro durante
     *         a consulta ao banco de dados.
     */
    @Override
    public Funcionario buscarPorUsuario(String usuario) {
        String sql = "SELECT * FROM funcionarios WHERE usuario = ?";

        try (Connection conexao = Conexao.conectar();
             PreparedStatement stmt = conexao.prepareStatement(sql)) {

            stmt.setString(1, usuario);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return montarFuncionario(rs);
                }
            }

            return null;

        } catch (SQLException e) {
            throw new RuntimeException(
                "Erro ao buscar funcionário por usuário.", e
            );
        }
    }

    /**
     * Lista todos os funcionários cadastrados no banco de dados,
     * ordenados pelo código de identificação.
     *
     * @return lista contendo os funcionários cadastrados.
     * @throws RuntimeException caso ocorra algum erro durante
     *         a consulta ao banco de dados.
     */
    @Override
    public List<Funcionario> listarTodos() {
        String sql = "SELECT * FROM funcionarios ORDER BY codigo";

        List<Funcionario> funcionarios = new ArrayList<>();

        try (Connection conexao = Conexao.conectar();
             PreparedStatement stmt = conexao.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                funcionarios.add(montarFuncionario(rs));
            }

            return funcionarios;

        } catch (SQLException e) {
            throw new RuntimeException(
                "Erro ao listar funcionários.", e
            );
        }
    }

    /**
     * Atualiza os dados de um funcionário já cadastrado,
     * identificado pelo seu código.
     *
     * @param funcionario objeto Funcionario contendo os dados atualizados.
     * @throws IllegalArgumentException caso o funcionário não seja encontrado.
     * @throws RuntimeException caso ocorra algum erro durante
     *         a atualização no banco de dados.
     */
    @Override
    public void atualizar(Funcionario funcionario) {
        String sql = """
            UPDATE funcionarios
            SET usuario = ?,
                senha = ?,
                cargo = ?
            WHERE codigo = ?
            """;

        try (Connection conexao = Conexao.conectar();
             PreparedStatement stmt = conexao.prepareStatement(sql)) {

            stmt.setString(1, funcionario.getUsuario());
            stmt.setString(2, funcionario.getSenha());
            stmt.setString(3, funcionario.getCargo());
            stmt.setInt(4, funcionario.getCodigo());

            int linhasAfetadas = stmt.executeUpdate();

            if (linhasAfetadas == 0) {
                throw new IllegalArgumentException(
                    "Funcionário não encontrado para atualização."
                );
            }

        } catch (SQLException e) {
            throw new RuntimeException(
                "Erro ao atualizar funcionário.", e
            );
        }
    }

    /**
     * Exclui um funcionário do banco de dados pelo seu código.
     *
     * @param codigo código do funcionário que será excluído.
     * @throws IllegalArgumentException caso o funcionário não seja encontrado.
     * @throws RuntimeException caso ocorra algum erro durante
     *         a exclusão no banco de dados.
     */
    @Override
    public void excluir(int codigo) {
        String sql = "DELETE FROM funcionarios WHERE codigo = ?";

        try (Connection conexao = Conexao.conectar();
             PreparedStatement stmt = conexao.prepareStatement(sql)) {

            stmt.setInt(1, codigo);

            int linhasAfetadas = stmt.executeUpdate();

            if (linhasAfetadas == 0) {
                throw new IllegalArgumentException(
                    "Funcionário não encontrado para exclusão."
                );
            }

        } catch (SQLException e) {
            throw new RuntimeException(
                "Erro ao excluir funcionário.", e
            );
        }
    }

    /**
     * Reconstrói um objeto Funcionario a partir dos dados
     * recuperados do banco de dados.
     *
     * @param rs ResultSet contendo os dados do funcionário.
     * @return objeto Funcionario correspondente ao registro consultado.
     * @throws SQLException caso ocorra algum erro durante
     *         a leitura dos dados.
     */
    private Funcionario montarFuncionario(ResultSet rs)
            throws SQLException {

        return new Funcionario(
            rs.getString("usuario"),
            rs.getString("senha"),
            rs.getInt("codigo"),
            rs.getString("cargo")
        );
    }
}