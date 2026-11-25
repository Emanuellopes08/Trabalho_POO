package infrastructure.persistence;

import application.repositories.FuncionarioRepository;
import domain.pessoas.Funcionario;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class FuncionarioRepositoryJDBC implements FuncionarioRepository {

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

    // Reconstrói o objeto Funcionario utilizando os dados do banco
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