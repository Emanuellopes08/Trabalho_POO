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

/**
 * Implementação JDBC do repositório de contas.
 *
 * Responsável por realizar as operações de persistência
 * das contas bancárias no banco de dados SQLite, incluindo
 * cadastro, consulta, atualização e exclusão.
 *
 * Também realiza a reconstrução dos objetos Conta a partir
 * dos registros armazenados no banco de dados.
 */
public class ContaRepositoryJDBC implements ContaRepository {

    /**
     * Salva uma nova conta no banco de dados.
     *
     * Após a inserção, recupera o identificador gerado
     * automaticamente pelo SQLite e atribui esse ID ao objeto Conta.
     *
     * @param conta objeto Conta que será salvo.
     * @throws RuntimeException caso ocorra algum erro durante
     *         a operação de persistência.
     */
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

    /**
     * Busca uma conta pelo seu identificador.
     *
     * @param id identificador da conta procurada.
     * @return objeto Conta encontrado ou null caso não exista.
     * @throws RuntimeException caso ocorra algum erro durante
     *         a consulta ao banco de dados.
     */
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

    /**
     * Busca todas as contas nas quais o cliente participa
     * como titular ou cotitular.
     *
     * @param cpf CPF do cliente utilizado na consulta.
     * @return lista de contas vinculadas ao cliente.
     * @throws RuntimeException caso ocorra algum erro durante
     *         a consulta ao banco de dados.
     */
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

    /**
     * Lista todas as contas cadastradas no banco de dados,
     * ordenadas pelo identificador.
     *
     * @return lista contendo todas as contas cadastradas.
     * @throws RuntimeException caso ocorra algum erro durante
     *         a consulta ao banco de dados.
     */
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

    /**
     * Atualiza os dados de uma conta já cadastrada,
     * identificada pelo seu ID.
     *
     * @param conta objeto Conta contendo os dados atualizados.
     * @throws IllegalArgumentException caso a conta não seja encontrada.
     * @throws RuntimeException caso ocorra algum erro durante
     *         a atualização no banco de dados.
     */
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

    /**
     * Exclui uma conta do banco de dados pelo seu identificador.
     *
     * @param id identificador da conta que será excluída.
     * @throws IllegalArgumentException caso a conta não seja encontrada.
     * @throws RuntimeException caso ocorra algum erro durante
     *         a exclusão no banco de dados.
     */
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

    /**
     * Preenche os parâmetros de um PreparedStatement com os dados
     * da conta, considerando os diferentes tipos de conta existentes.
     *
     * Para contas conjuntas, define o CPF do cotitular.
     * Para contas PJ, define o CNPJ, nome da empresa e fatura.
     * Nos demais casos, os campos específicos recebem valores nulos.
     *
     * @param stmt PreparedStatement que receberá os parâmetros.
     * @param conta objeto Conta contendo os dados utilizados
     *              no preenchimento.
     * @throws SQLException caso ocorra algum erro ao definir
     *         os parâmetros da instrução SQL.
     */
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

    /**
     * Reconstrói um objeto Conta a partir dos dados recuperados
     * do banco de dados.
     *
     * Identifica o tipo de conta e instancia a classe correspondente,
     * recuperando também os dados do titular e, quando necessário,
     * do cotitular.
     *
     * @param conexao conexão ativa com o banco de dados.
     * @param rs ResultSet contendo os dados da conta.
     * @return objeto Conta correspondente ao registro consultado.
     * @throws SQLException caso ocorra algum erro na leitura dos dados.
     * @throws IllegalArgumentException caso o tipo de conta seja desconhecido.
     */
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

        double saldo = rs.getDouble("saldo");

        if (saldo > 0) {
            conta.efetuarDeposito(saldo);
        }

        return conta;
    }

    /**
     * Busca um cliente pelo CPF utilizando a mesma conexão
     * empregada na operação de consulta da conta.
     *
     * @param conexao conexão ativa com o banco de dados.
     * @param cpf CPF do cliente procurado.
     * @return objeto Cliente encontrado ou null caso não exista.
     * @throws SQLException caso ocorra algum erro durante
     *         a consulta ou leitura dos dados.
     */
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