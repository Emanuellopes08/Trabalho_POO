package Contas;

import Pessoas.Cliente;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Testes que demonstram o Princípio da Substituição de Liskov (LSP):
 * qualquer subclasse de {@link Conta} pode ser usada onde se espera uma {@link Conta},
 * sem mudar as regras de saldo, depósito e saque.
 */
public class ContaLspTest {

    private Cliente titular;
    private Cliente cotitular;
    private List<Conta> contas;

    @BeforeEach
    void preparar() {
        titular = new Cliente("Ana", "111", LocalDate.of(2000, 1, 1), 3000,
                "99999", "Rua A", "Pai", "Mae", "ana@email.com");
        cotitular = new Cliente("Bruno", "222", LocalDate.of(1999, 5, 5), 2500,
                "88888", "Rua B", "Pai", "Mae", "bruno@email.com");

        contas = new ArrayList<>();
        contas.add(new ContaCorrente(titular));
        contas.add(new ContaPoupanca(titular));
        contas.add(new ContaConjunta(titular, cotitular));
        contas.add(new ContaPJ("12345678000199", "Empresa X", 50000, titular));
    }

    @Test
    void todasAsContasPodemSerUsadasComoConta() {
        for (Conta conta : contas) {
            assertNotNull(conta.getTipoConta());
            assertSame(titular, conta.getTitular());
        }
    }

    @Test
    void todasAsContasComecamComSaldoZero() {
        for (Conta conta : contas) {
            assertEquals(0.0, conta.getSaldo(), 0.0001, conta.getTipoConta() + " deveria começar com saldo 0");
        }
    }

    @Test
    void depositoValidoAumentaOSaldoEmTodasAsContas() {
        for (Conta conta : contas) {
            conta.efetuarDeposito(100);
            assertEquals(100.0, conta.getSaldo(), 0.0001, conta.getTipoConta().toString());
        }
    }

    @Test
    void saqueValidoDiminuiOSaldoEmTodasAsContas() {
        for (Conta conta : contas) {
            conta.efetuarDeposito(100);
            conta.efetuarSaque(40);
            assertEquals(60.0, conta.getSaldo(), 0.0001, conta.getTipoConta().toString());
        }
    }

    @Test
    void saqueDoSaldoTotalZeraAConta() {
        for (Conta conta : contas) {
            conta.efetuarDeposito(50);
            conta.efetuarSaque(50);
            assertEquals(0.0, conta.getSaldo(), 0.0001, conta.getTipoConta().toString());
        }
    }

    @Test
    void depositoInvalidoLancaExcecaoEmTodasAsContas() {
        for (Conta conta : contas) {
            assertThrows(IllegalArgumentException.class, () -> conta.efetuarDeposito(0));
            assertThrows(IllegalArgumentException.class, () -> conta.efetuarDeposito(-10));
            assertThrows(IllegalArgumentException.class, () -> conta.efetuarDeposito(Double.NaN));
            assertEquals(0.0, conta.getSaldo(), 0.0001, "saldo não pode mudar após erro");
        }
    }

    @Test
    void saqueInvalidoLancaExcecaoEmTodasAsContas() {
        for (Conta conta : contas) {
            conta.efetuarDeposito(100);
            assertThrows(IllegalArgumentException.class, () -> conta.efetuarSaque(0));
            assertThrows(IllegalArgumentException.class, () -> conta.efetuarSaque(-5));
            assertEquals(100.0, conta.getSaldo(), 0.0001, "saldo não pode mudar após erro");
        }
    }

    @Test
    void saqueAcimaDoSaldoLancaExcecaoEmTodasAsContas() {
        for (Conta conta : contas) {
            conta.efetuarDeposito(100);
            assertThrows(SaldoInsuficienteException.class, () -> conta.efetuarSaque(100.01));
            assertEquals(100.0, conta.getSaldo(), 0.0001, "saldo não pode mudar após erro");
        }
    }

    @Test
    void contaConjuntaCompartilhaOMesmoSaldoEntreTitularECotitular() {
        ContaConjunta conjunta = new ContaConjunta(titular, cotitular);
        Conta comoConta = conjunta;
        comoConta.efetuarDeposito(200);
        assertEquals(200.0, conjunta.getSaldo(), 0.0001);
        assertSame(cotitular, conjunta.getCotitular());
    }

    @Test
    void rendimentoDaPoupancaFuncionaSemQuebrarAsRegrasDeConta() {
        ContaPoupanca poupanca = new ContaPoupanca(titular);

        // saldo zero: não pode lançar erro
        assertDoesNotThrow(poupanca::aplicarRendimento);
        assertEquals(0.0, poupanca.getSaldo(), 0.0001);

        poupanca.efetuarDeposito(1000);
        poupanca.aplicarRendimento();
        assertEquals(1005.0, poupanca.getSaldo(), 0.0001);
    }

    @Test
    void metodosEspecificosContinuamDisponiveisAposVerificarOTipo() {
        for (Conta conta : contas) {
            if (conta instanceof ContaCorrente) {
                assertEquals(20.0, ((ContaCorrente) conta).consultarMensalidade(), 0.0001);
            } else if (conta instanceof ContaPoupanca) {
                assertEquals(0.5, ((ContaPoupanca) conta).consultarRendimento(), 0.0001);
            } else if (conta instanceof ContaConjunta) {
                assertEquals("Bruno", ((ContaConjunta) conta).getCotitular().getNome());
            } else if (conta instanceof ContaPJ) {
                assertEquals("12345678000199", ((ContaPJ) conta).getCnpj());
            } else {
                fail("Tipo de conta inesperado: " + conta.getClass());
            }
        }
    }

    @Test
    void construtoresRejeitamDadosInvalidos() {
        assertThrows(IllegalArgumentException.class, () -> new ContaCorrente(null));
        assertThrows(IllegalArgumentException.class, () -> new ContaConjunta(titular, null));
        assertThrows(IllegalArgumentException.class, () -> new ContaConjunta(titular, titular));
        assertThrows(IllegalArgumentException.class, () -> new ContaPJ("", "Empresa", 10, titular));
        assertThrows(IllegalArgumentException.class, () -> new ContaPJ("123", "Empresa", -1, titular));
    }
}
