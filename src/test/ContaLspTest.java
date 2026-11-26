import domain.contas.*;
import domain.pessoas.*;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class ContaLspTest {

    private Cliente criarCliente(String nome, String cpf) {
        return new Cliente(nome, cpf, LocalDate.of(2000, 1, 1), 3000,
                "99999", "Rua A", "Pai", "Mae", nome.toLowerCase() + "@email.com");
    }

    /** Cria uma conta de cada modalidade, todas guardadas como o tipo geral Conta. */
    private List<Conta> criarTodasAsContas() {
        Cliente titular = criarCliente("Ana", "111");
        Cliente cotitular = criarCliente("Bruno", "222");

        List<Conta> contas = new ArrayList<>();
        contas.add(new ContaCorrente(titular));
        contas.add(new ContaPoupanca(titular));
        contas.add(new ContaConjunta(titular, cotitular));
        contas.add(new ContaPJ("12345678000199", "Empresa X", 50000, titular));
        return contas;
    }

    @Test
    void testarRendimentoContaPoupanca() {
        ContaPoupanca conta = new ContaPoupanca(criarCliente("Ana", "111"));   // <<< ALTERADO (era new ContaPoupanca(null))

        assertEquals(0.5, conta.consultarRendimento(), 0.001);
    }

    @Test
    void testarMensalidadeContaCorrente() {
        ContaCorrente conta = new ContaCorrente(criarCliente("Ana", "111"));

        assertEquals(20.0, conta.consultarMensalidade(), 0.001);
    }

    @Test
    void todasAsContasComecamComSaldoZero() {
        for (Conta conta : criarTodasAsContas()) {
            assertEquals(0.0, conta.getSaldo(), 0.001, conta.getTipoConta() + " deveria começar com saldo 0");
        }
    }

    @Test
    void depositoValidoAumentaOSaldoEmTodasAsContas() {
        for (Conta conta : criarTodasAsContas()) {
            conta.efetuarDeposito(100);

            assertEquals(100.0, conta.getSaldo(), 0.001, conta.getTipoConta().toString());
        }
    }

    @Test
    void saqueValidoDiminuiOSaldoEmTodasAsContas() {
        for (Conta conta : criarTodasAsContas()) {
            conta.efetuarDeposito(100);
            conta.efetuarSaque(40);

            assertEquals(60.0, conta.getSaldo(), 0.001, conta.getTipoConta().toString());
        }
    }

    @Test
    void saqueDoSaldoTotalZeraAConta() {
        for (Conta conta : criarTodasAsContas()) {
            conta.efetuarDeposito(50);
            conta.efetuarSaque(50);

            assertEquals(0.0, conta.getSaldo(), 0.001, conta.getTipoConta().toString());
        }
    }

    @Test
    void depositoInvalidoLancaExcecaoEmTodasAsContas() {
        for (Conta conta : criarTodasAsContas()) {
            assertThrows(IllegalArgumentException.class, () -> conta.efetuarDeposito(0));
            assertThrows(IllegalArgumentException.class, () -> conta.efetuarDeposito(-10));
            assertThrows(IllegalArgumentException.class, () -> conta.efetuarDeposito(Double.NaN));

            assertEquals(0.0, conta.getSaldo(), 0.001, "o saldo não pode mudar depois de um erro");
        }
    }

    @Test
    void saqueInvalidoLancaExcecaoEmTodasAsContas() {
        for (Conta conta : criarTodasAsContas()) {
            conta.efetuarDeposito(100);

            assertThrows(IllegalArgumentException.class, () -> conta.efetuarSaque(0));
            assertThrows(IllegalArgumentException.class, () -> conta.efetuarSaque(-5));

            assertEquals(100.0, conta.getSaldo(), 0.001, "o saldo não pode mudar depois de um erro");
        }
    }

    @Test
    void saqueAcimaDoSaldoLancaExcecaoEmTodasAsContas() {
        for (Conta conta : criarTodasAsContas()) {
            conta.efetuarDeposito(100);

            assertThrows(SaldoInsuficienteException.class, () -> conta.efetuarSaque(100.01));

            assertEquals(100.0, conta.getSaldo(), 0.001, "o saldo não pode mudar depois de um erro");
        }
    }

    @Test
    void contaConjuntaCompartilhaOMesmoSaldoEntreTitularECotitular() {
        Cliente titular = criarCliente("Ana", "111");
        Cliente cotitular = criarCliente("Bruno", "222");
        ContaConjunta conjunta = new ContaConjunta(titular, cotitular);

        Conta comoConta = conjunta;
        comoConta.efetuarDeposito(200);

        assertEquals(200.0, conjunta.getSaldo(), 0.001);
        assertSame(cotitular, conjunta.getCotitular());
    }

    @Test
    void rendimentoDaPoupancaFuncionaSemQuebrarAsRegrasDeConta() {
        ContaPoupanca poupanca = new ContaPoupanca(criarCliente("Ana", "111"));

        assertDoesNotThrow(poupanca::aplicarRendimento);
        assertEquals(0.0, poupanca.getSaldo(), 0.001);

        poupanca.efetuarDeposito(1000);
        poupanca.aplicarRendimento();

        assertEquals(1005.0, poupanca.getSaldo(), 0.001);
    }

    @Test
    void construtoresRejeitamDadosInvalidos() {
        Cliente titular = criarCliente("Ana", "111");

        assertThrows(IllegalArgumentException.class, () -> new ContaPoupanca(null));
        assertThrows(IllegalArgumentException.class, () -> new ContaConjunta(titular, null));
        assertThrows(IllegalArgumentException.class, () -> new ContaConjunta(titular, titular));
        assertThrows(IllegalArgumentException.class, () -> new ContaPJ("", "Empresa", 10, titular));
        assertThrows(IllegalArgumentException.class, () -> new ContaPJ("123", "Empresa", -1, titular));
    }
}
