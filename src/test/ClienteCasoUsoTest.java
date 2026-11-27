import application.casodeuso.cliente.CadastrarCliente;
import application.casodeuso.cliente.ConsultarCliente;
import application.casodeuso.cliente.AlterarCliente;
import application.casodeuso.cliente.ExcluirCliente;
import application.repositories.ClienteRepository;
import domain.pessoas.Cliente;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class ClienteCasoUsoTest {

    private final ClienteRepository repositorio = new ClienteRepositoryMemoria();

    private final CadastrarCliente cadastrarCliente = new CadastrarCliente(repositorio, repositorio);

    private Cliente criarCliente() {
        return new Cliente(
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
    }

    @Test
    void deveCadastrarCliente() {
        Cliente cliente = criarCliente();
        cadastrarCliente.executar(cliente, "12345678");

        assertEquals(cliente, repositorio.buscarPorCpf(cliente.getCpf()));
        assertEquals("12345678", cliente.getSenha());
    }

    @Test
    void deveRejeitarCpfDuplicado() {
        Cliente cliente = criarCliente();

        cadastrarCliente.executar(cliente, "12345678");

        assertThrows(IllegalArgumentException.class,
                () -> cadastrarCliente.executar(criarCliente(), "12345678"));

        assertEquals(1, repositorio.listarTodos().size());
    }

    @Test
    void deveConsultarClienteExistente() {
        Cliente cliente = criarCliente();
        cadastrarCliente.executar(cliente, "12345678");

        ConsultarCliente consultarCliente = new ConsultarCliente(repositorio);

        Cliente resultado = consultarCliente.executar(cliente.getCpf());

        assertEquals(cliente, resultado);
    }

    @Test
    void deveRejeitarConsultaDeClienteInexistente() {
        ConsultarCliente consultarCliente = new ConsultarCliente(repositorio);

        assertThrows(IllegalArgumentException.class,
                () -> consultarCliente.executar("99999999999"));
    }

    @Test
    void deveAlterarClienteExistente() {
        Cliente cliente = criarCliente();
        cadastrarCliente.executar(cliente, "12345678");

        Cliente clienteAtualizado = criarCliente();
        clienteAtualizado.setSenha("87654321");

        AlterarCliente alterarCliente = new AlterarCliente(repositorio, repositorio);

        alterarCliente.executar(clienteAtualizado);

        assertEquals("87654321", repositorio.buscarPorCpf(cliente.getCpf()).getSenha());

        assertEquals(1, repositorio.listarTodos().size());
    }

    @Test
    void deveRejeitarAlteracaoDeClienteInexistente() {
        Cliente cliente = criarCliente();
        cliente.setSenha("12345678");

        AlterarCliente alterarCliente = new AlterarCliente(repositorio, repositorio);

        assertThrows(IllegalArgumentException.class,
                () -> alterarCliente.executar(cliente));
    }

    @Test
    void deveExcluirClienteExistente() {
        Cliente cliente = criarCliente();
        cadastrarCliente.executar(cliente, "12345678");

        ExcluirCliente excluirCliente = new ExcluirCliente(repositorio, repositorio);

        excluirCliente.executar(cliente.getCpf());

        assertNull(repositorio.buscarPorCpf(cliente.getCpf()));
        assertEquals(0, repositorio.listarTodos().size());
    }

    @Test
    void deveRejeitarExclusaoDeClienteInexistente() {
        ExcluirCliente excluirCliente = new ExcluirCliente(repositorio, repositorio);

        assertThrows(IllegalArgumentException.class,
                () -> excluirCliente.executar("99999999999"));
    }

    private static class ClienteRepositoryMemoria implements ClienteRepository {

        private final List<Cliente> clientes = new ArrayList<>();

        @Override
        public void salvar(Cliente cliente) {
            clientes.add(cliente);
        }

        @Override
        public Cliente buscarPorCpf(String cpf) {
            for (Cliente cliente : clientes) {
                if (cliente.getCpf().equals(cpf)) {
                    return cliente;
                }
            }
            return null;
        }

        @Override
        public List<Cliente> listarTodos() {
            return List.copyOf(clientes);
        }

        @Override
        public void atualizar(Cliente cliente) {
            excluir(cliente.getCpf());
            salvar(cliente);
        }

        @Override
        public void excluir(String cpf) {
            clientes.removeIf(c -> c.getCpf().equals(cpf));
        }
    }
}