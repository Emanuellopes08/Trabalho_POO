import application.casodeuso.funcionario.CadastrarFuncionario;
import application.casodeuso.funcionario.ConsultarFuncionario;
import application.casodeuso.funcionario.AlterarFuncionario;
import application.casodeuso.funcionario.ExcluirFuncionario;
import application.repositories.FuncionarioRepository;
import domain.pessoas.Funcionario;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class FuncionarioCasoUsoTest {

    private final FuncionarioRepository repositorio =
            new FuncionarioRepositoryMemoria();

    private final CadastrarFuncionario cadastrarFuncionario =
            new CadastrarFuncionario(repositorio, repositorio);

    private Funcionario criarFuncionario() {
        return new Funcionario(
                "Ana",
                "12345678",
                1,
                "Funcionário"
        );
    }

    @Test
    void deveCadastrarFuncionario() {
        Funcionario funcionario = criarFuncionario();

        cadastrarFuncionario.executar(funcionario);

        assertEquals(funcionario, repositorio.buscarPorCodigo(1));
        assertEquals(1, repositorio.listarTodos().size());
    }

    @Test
    void deveRejeitarCodigoDuplicado() {
        cadastrarFuncionario.executar(criarFuncionario());

        assertThrows(IllegalArgumentException.class,
                () -> cadastrarFuncionario.executar(criarFuncionario()));

        assertEquals(1, repositorio.listarTodos().size());
    }

    @Test
    void deveConsultarFuncionarioExistente() {
        Funcionario funcionario = criarFuncionario();
        cadastrarFuncionario.executar(funcionario);

        ConsultarFuncionario consultarFuncionario =
                new ConsultarFuncionario(repositorio);

        Funcionario resultado = consultarFuncionario.executar(1);

        assertEquals(funcionario, resultado);
    }

    @Test
    void deveRejeitarConsultaDeFuncionarioInexistente() {
        ConsultarFuncionario consultarFuncionario =
                new ConsultarFuncionario(repositorio);

        assertThrows(IllegalArgumentException.class,
                () -> consultarFuncionario.executar(99));
    }

    @Test
    void deveAlterarFuncionarioExistente() {
        cadastrarFuncionario.executar(criarFuncionario());

        Funcionario funcionarioAtualizado = criarFuncionario();
        funcionarioAtualizado.setSenha("87654321");

        AlterarFuncionario alterarFuncionario =
                new AlterarFuncionario(repositorio, repositorio);

        alterarFuncionario.executar(funcionarioAtualizado);

        assertEquals("87654321",
                repositorio.buscarPorCodigo(1).getSenha());

        assertEquals(1, repositorio.listarTodos().size());
    }

    @Test
    void deveRejeitarAlteracaoDeFuncionarioInexistente() {
        Funcionario funcionario = criarFuncionario();

        AlterarFuncionario alterarFuncionario =
                new AlterarFuncionario(repositorio, repositorio);

        assertThrows(IllegalArgumentException.class,
                () -> alterarFuncionario.executar(funcionario));
    }

    @Test
    void deveExcluirFuncionarioExistente() {
        cadastrarFuncionario.executar(criarFuncionario());

        ExcluirFuncionario excluirFuncionario =
                new ExcluirFuncionario(repositorio, repositorio);

        excluirFuncionario.executar(1);

        assertNull(repositorio.buscarPorCodigo(1));
        assertTrue(repositorio.listarTodos().isEmpty());
    }

    @Test
    void deveRejeitarExclusaoDeFuncionarioInexistente() {
        ExcluirFuncionario excluirFuncionario =
                new ExcluirFuncionario(repositorio, repositorio);

        assertThrows(IllegalArgumentException.class,
                () -> excluirFuncionario.executar(99));
    }

    private static class FuncionarioRepositoryMemoria
            implements FuncionarioRepository {

        private final List<Funcionario> funcionarios =
                new ArrayList<>();

        @Override
        public void salvar(Funcionario funcionario) {
            funcionarios.add(funcionario);
        }

        @Override
        public Funcionario buscarPorCodigo(int codigo) {
            for (Funcionario funcionario : funcionarios) {
                if (funcionario.getCodigo() == codigo) {
                    return funcionario;
                }
            }
            return null;
        }

        @Override
        public Funcionario buscarPorUsuario(String usuario) {
            for (Funcionario funcionario : funcionarios) {
                if (funcionario.getUsuario().equals(usuario)) {
                    return funcionario;
                }
            }
            return null;
        }

        @Override
        public List<Funcionario> listarTodos() {
            return List.copyOf(funcionarios);
        }

        @Override
        public void atualizar(Funcionario funcionario) {
            excluir(funcionario.getCodigo());
            salvar(funcionario);
        }

        @Override
        public void excluir(int codigo) {
            funcionarios.removeIf(f -> f.getCodigo() == codigo);
        }
    }
}