package infrastructure.persistence;

import application.repositories.ContaRepository;
import domain.contas.*;
import domain.pessoas.Cliente;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ContaRepositoryJDBC implements ContaRepository {

    @Override
    public void salvar(Conta conta) {
        String sql = """
            INSERT INTO contas
            (tipo_conta, titular_cpf, cotitular_cpf, saldo,
             cnpj, nome_empresa, fatura_empresa)
            VALUES (?, ?, ?, ?, ?, ?, ?)
            """;

        try (Connection conexao = Conexao.conectar();
             PreparedStatement stmt = conexao.prepareStatement(sql)) {

            preencherParametros(stmt, conta);
            stmt.executeUpdate();

            // Recupera o ID gerado pelo SQLite
            try (PreparedStatement consulta = conexao.prepareStatement(
                    "SELECT last_insert_rowid()");
                 ResultSet rs = consulta.executeQuery()) {

                if (rs.next()) {
                    conta.setId(rs.getInt(1));
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao salvar conta.", e);
        }
    }

    @Override
    public Conta buscarPorId(int id) {
        String sql = "SELECT * FROM contas WHERE id = ?";

        try (Connection conexao = Conexao.conectar();
             PreparedStatement stmt = conexao.prepareStatement(sql)) {

            stmt.setInt(1, id);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return montarConta(conexao, rs);
                }
            }

            return null;

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao buscar conta por ID.", e);
        }
    }

    @Override
    public List<Conta> buscarPorCpfCliente(String cpf) {
        String sql = """
            SELECT * FROM contas
            WHERE titular_cpf = ? OR cotitular_cpf = ?
            """;

        List<Conta> contas = new ArrayList<>();

        try (Connection conexao = Conexao.conectar();
             PreparedStatement stmt = conexao.prepareStatement(sql)) {

            stmt.setString(1, cpf);
            stmt.setString(2, cpf);

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    contas.add(montarConta(conexao, rs));
                }
            }

            return contas;

        } catch (SQLException e) {
            throw new RuntimeException(
                "Erro ao buscar contas do cliente.", e
            );
        }
    }

    @Override
    public List<Conta> listarTodas() {
        String sql = "SELECT * FROM contas ORDER BY id";

        List<Conta> contas = new ArrayList<>();

        try (Connection conexao = Conexao.conectar();
             PreparedStatement stmt = conexao.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                contas.add(montarConta(conexao, rs));
            }

            return contas;

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao listar contas.", e);
        }
    }

    @Override
    public void atualizar(Conta conta) {
        String sql = """
            UPDATE contas
            SET tipo_conta = ?,
                titular_cpf = ?,
                cotitular_cpf = ?,
                saldo = ?,
                cnpj = ?,
                nome_empresa = ?,
                fatura_empresa = ?
            WHERE id = ?
            """;

        try (Connection conexao = Conexao.conectar();
             PreparedStatement stmt = conexao.prepareStatement(sql)) {

            preencherParametros(stmt, conta);
            stmt.setInt(8, conta.getId());

            int linhasAfetadas = stmt.executeUpdate();

            if (linhasAfetadas == 0) {
                throw new IllegalArgumentException(
                    "Conta não encontrada para atualização."
                );
            }

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao atualizar conta.", e);
        }
    }

    @Override
    public void excluir(int id) {
        String sql = "DELETE FROM contas WHERE id = ?";

        try (Connection conexao = Conexao.conectar();
             PreparedStatement stmt = conexao.prepareStatement(sql)) {

            stmt.setInt(1, id);

            int linhasAfetadas = stmt.executeUpdate();

            if (linhasAfetadas == 0) {
                throw new IllegalArgumentException(
                    "Conta não encontrada para exclusão."
                );
            }

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao excluir conta.", e);
        }
    }

    // Preenche os parâmetros utilizados no INSERT e UPDATE
    private void preencherParametros(
            PreparedStatement stmt, Conta conta) throws SQLException {

        stmt.setString(1, conta.getTipoConta().name());
        stmt.setString(2, conta.getTitular().getCpf());

        if (conta instanceof ContaConjunta conjunta) {
            stmt.setString(3, conjunta.getCotitular().getCpf());
        } else {
            stmt.setNull(3, java.sql.Types.VARCHAR);
        }

        stmt.setDouble(4, conta.getSaldo());

        if (conta instanceof ContaPJ pj) {
            stmt.setString(5, pj.getCnpj());
            stmt.setString(6, pj.getNomeEmpresa());
            stmt.setDouble(7, pj.getFatura());
        } else {
            stmt.setNull(5, java.sql.Types.VARCHAR);
            stmt.setNull(6, java.sql.Types.VARCHAR);
            stmt.setNull(7, java.sql.Types.REAL);
        }
    }

    // Constrói o objeto Conta a partir dos dados do banco
    private Conta montarConta(
            Connection conexao, ResultSet rs) throws SQLException {

        String cpfTitular = rs.getString("titular_cpf");
        Cliente titular = buscarCliente(conexao, cpfTitular);

        String tipo = rs.getString("tipo_conta");

        Conta conta;

        switch (TipoConta.valueOf(tipo)) {
            case POUPANCA:
                conta = new ContaPoupanca(titular);
                break;

            case CORRENTE:
                conta = new ContaCorrente(titular);
                break;

            case CONJUNTA:
                String cpfCotitular = rs.getString("cotitular_cpf");
                Cliente cotitular = buscarCliente(conexao, cpfCotitular);

                conta = new ContaConjunta(titular, cotitular);
                break;

            case PJ:
                conta = new ContaPJ(
                    rs.getString("cnpj"),
                    rs.getString("nome_empresa"),
                    rs.getDouble("fatura_empresa"),
                    titular
                );
                break;

            default:
                throw new IllegalArgumentException(
                    "Tipo de conta desconhecido: " + tipo
                );
        }

        conta.setId(rs.getInt("id"));
        conta.efetuarDeposito(rs.getDouble("saldo"));

        return conta;
    }

    // Busca o cliente utilizando a mesma conexão da operação
    private Cliente buscarCliente(
            Connection conexao, String cpf) throws SQLException {

        String sql = "SELECT * FROM clientes WHERE cpf = ?";

        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setString(1, cpf);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new Cliente(
                        rs.getString("nome"),
                        rs.getString("cpf"),
                        java.time.LocalDate.parse(
                            rs.getString("data_nascimento")
                        ),
                        rs.getDouble("renda"),
                        rs.getString("telefone"),
                        rs.getString("endereco"),
                        rs.getString("nome_pai"),
                        rs.getString("nome_mae"),
                        rs.getString("email")
                    );
                }
            }
        }

        return null;
    }
}