import application.casodeuso.conta.Depositar;
import application.repositories.ContaRepository;
import domain.contas.Conta;
import domain.contas.ContaCorrente;
import domain.pessoas.Cliente;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class ContaCasoUsoTest {

    private final ContaRepository repositorio = new ContaRepositoryMemoria();

    private final Depositar depositar = new Depositar(repositorio);

    private Conta criarConta() {
        Cliente cliente = new Cliente(
                "Ana",
                "12345678900",
                LocalDate.of(2000, 1, 1),
                3000,
                "999999999",
                "Rua A",
                "Pai",
                "Mae",
                "ana@email.com"
        );

        Conta conta = new ContaCorrente(cliente);
        conta.setId(1);

        return conta;
    }

    @Test
    void deveRealizarDeposito() {
        Conta conta = criarConta();
        repositorio.salvar(conta);

        depositar.executar(1, 500);

        assertEquals(500.0,
                repositorio.buscarPorId(1).getSaldo());
    }

    @Test
    void deveRejeitarDepositoComValorInvalido() {
        Conta conta = criarConta();
        repositorio.salvar(conta);

        assertThrows(IllegalArgumentException.class,
                () -> depositar.executar(1, -100));

        assertEquals(0.0, conta.getSaldo());
    }

    @Test
    void deveRejeitarDepositoEmContaInexistente() {
        assertThrows(IllegalArgumentException.class,
                () -> depositar.executar(99, 500));
    }

    private static class ContaRepositoryMemoria implements ContaRepository {

        private final List<Conta> contas = new ArrayList<>();

        @Override
        public void salvar(Conta conta) {
            contas.add(conta);
        }

        @Override
        public Conta buscarPorId(int id) {
            for (Conta conta : contas) {
                if (conta.getId() == id) {
                    return conta;
                }
            }
            return null;
        }

        @Override
        public List<Conta> buscarPorCpfCliente(String cpf) {
            List<Conta> resultado = new ArrayList<>();

            for (Conta conta : contas) {
                if (conta.getTitular().getCpf().equals(cpf)) {
                    resultado.add(conta);
                }
            }
            return resultado;
        }

        @Override
        public List<Conta> listarTodas() {
            return List.copyOf(contas);
        }

        @Override
        public void atualizar(Conta conta) {
            excluir(conta.getId());
            salvar(conta);
        }

        @Override
        public void excluir(int id) {
            contas.removeIf(c -> c.getId() == id);
        }
    }
}