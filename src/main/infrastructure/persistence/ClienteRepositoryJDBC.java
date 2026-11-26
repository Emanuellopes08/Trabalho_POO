
package infrastructure.persistence;

import application.repositories.ClienteRepository;
import domain.pessoas.Cliente;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Implementação JDBC do repositório de clientes.
 * Responsável por realizar as operações de persistência
 * dos clientes no banco de dados SQLite.
 */
public class ClienteRepositoryJDBC implements ClienteRepository {

    /**
     * Salva um novo cliente no banco de dados.
     *
     * @param cliente objeto Cliente que será salvo.
     */
    @Override
    public void salvar(Cliente cliente) {
        String sql = "INSERT INTO clientes "
                + "(cpf, nome, data_nascimento, renda, telefone, endereco, "
                + "nome_pai, nome_mae, email, senha) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection conexao = Conexao.conectar();
             PreparedStatement stmt = conexao.prepareStatement(sql)) {

            stmt.setString(1, cliente.getCpf());
            stmt.setString(2, cliente.getNome());
            stmt.setString(3, cliente.getDataNascimento().toString());
            stmt.setDouble(4, cliente.getRenda());
            stmt.setString(5, cliente.getTelefone());
            stmt.setString(6, cliente.getEndereco());
            stmt.setString(7, cliente.getNomePai());
            stmt.setString(8, cliente.getNomeMae());
            stmt.setString(9, cliente.getEmail());
            stmt.setString(10, cliente.getSenha());

            stmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao salvar cliente.", e);
        }
    }

    /**
     * Busca um cliente pelo CPF.
     *
     * @param cpf CPF do cliente procurado.
     * @return objeto Cliente encontrado ou null caso não exista.
     */
    @Override
    public Cliente buscarPorCpf(String cpf) {
        String sql = "SELECT * FROM clientes WHERE cpf = ?";

        try (Connection conexao = Conexao.conectar();
             PreparedStatement stmt = conexao.prepareStatement(sql)) {

            stmt.setString(1, cpf);

            try (ResultSet resultado = stmt.executeQuery()) {
                if (resultado.next()) {
                    return montarCliente(resultado);
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao buscar cliente pelo CPF.", e);
        }

        return null;
    }

    /**
     * Lista todos os clientes cadastrados.
     *
     * @return lista com os clientes encontrados.
     */
    @Override
    public List<Cliente> listarTodos() {
        String sql = "SELECT * FROM clientes ORDER BY nome";
        List<Cliente> clientes = new ArrayList<>();

        try (Connection conexao = Conexao.conectar();
             PreparedStatement stmt = conexao.prepareStatement(sql);
             ResultSet resultado = stmt.executeQuery()) {

            while (resultado.next()) {
                clientes.add(montarCliente(resultado));
            }

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao listar clientes.", e);
        }

        return clientes;
    }

    /**
     * Atualiza os dados de um cliente identificado pelo CPF.
     *
     * @param cliente objeto Cliente com os dados atualizados.
     */
    @Override
    public void atualizar(Cliente cliente) {
        String sql = "UPDATE clientes SET "
                + "nome = ?, "
                + "data_nascimento = ?, "
                + "renda = ?, "
                + "telefone = ?, "
                + "endereco = ?, "
                + "nome_pai = ?, "
                + "nome_mae = ?, "
                + "email = ?, "
                + "senha = ? "
                + "WHERE cpf = ?";

        try (Connection conexao = Conexao.conectar();
             PreparedStatement stmt = conexao.prepareStatement(sql)) {

            stmt.setString(1, cliente.getNome());
            stmt.setString(2, cliente.getDataNascimento().toString());
            stmt.setDouble(3, cliente.getRenda());
            stmt.setString(4, cliente.getTelefone());
            stmt.setString(5, cliente.getEndereco());
            stmt.setString(6, cliente.getNomePai());
            stmt.setString(7, cliente.getNomeMae());
            stmt.setString(8, cliente.getEmail());
            stmt.setString(9, cliente.getSenha());
            stmt.setString(10, cliente.getCpf());

            int linhasAlteradas = stmt.executeUpdate();

            if (linhasAlteradas == 0) {
                throw new IllegalArgumentException(
                        "Cliente não encontrado para atualização."
                );
            }

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao atualizar cliente.", e);
        }
    }

    /**
     * Exclui um cliente pelo CPF.
     * A exclusão respeita as chaves estrangeiras do banco de dados.
     *
     * @param cpf CPF do cliente que será excluído.
     */
    @Override
    public void excluir(String cpf) {
        String sql = "DELETE FROM clientes WHERE cpf = ?";

        try (Connection conexao = Conexao.conectar();
             PreparedStatement stmt = conexao.prepareStatement(sql)) {

            stmt.setString(1, cpf);

            int linhasAlteradas = stmt.executeUpdate();

            if (linhasAlteradas == 0) {
                throw new IllegalArgumentException(
                        "Cliente não encontrado para exclusão."
                );
            }

        } catch (SQLException e) {
            throw new RuntimeException(
                    "Erro ao excluir cliente. Verifique se ele possui contas vinculadas.",
                    e
            );
        }
    }

    /**
     * Reconstrói um objeto Cliente a partir dos dados do ResultSet.
     *
     * @param resultado resultado da consulta SQL.
     * @return objeto Cliente preenchido com os dados do banco.
     * @throws SQLException caso ocorra erro na leitura dos dados.
     */
    private Cliente montarCliente(ResultSet resultado) throws SQLException {
        Cliente cliente = new Cliente(
                resultado.getString("nome"),
                resultado.getString("cpf"),
                LocalDate.parse(resultado.getString("data_nascimento")),
                resultado.getDouble("renda"),
                resultado.getString("telefone"),
                resultado.getString("endereco"),
                resultado.getString("nome_pai"),
                resultado.getString("nome_mae"),
                resultado.getString("email")
        );
        String senha = resultado.getString("senha");
        if (senha != null && !senha.isBlank()) {
            cliente.setSenha(senha);
        }
        return cliente;
    }
}
