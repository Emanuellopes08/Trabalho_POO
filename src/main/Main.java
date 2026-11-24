import javax.swing.SwingUtilities;
import presentation.swing.TelaPrincipal;

/**
 * Classe principal responsável pela inicialização do sistema Banco ES.
 * Atua como o ponto central de montagem (Composition Root) da arquitetura,
 * onde as implementações de infraestrutura são instanciadas e injetadas nos
 * casos de uso e na interface gráfica (DIP).
 */
public class Main {

    /**
     * Ponto de entrada da aplicação. Configura as dependências e inicia
     * a interface gráfica na thread de eventos do Swing.
     *
     * @param args Argumentos de linha de comando (não utilizados).
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            inicializarSistema();
        });
    }

    /**
     * Orquestra a injeção de dependências entre as camadas (Infraestrutura ->
     * Casos de Uso -> Interface Gráfica) e exibe a tela inicial.
     */
    private static void inicializarSistema() {
        // 1. Infraestrutura (Repositórios concretos JDBC - Vinícios)
        // Ex.: ClienteRepository clienteRepo = new ClienteRepositoryJDBC();
        // Ex.: ContaRepository contaRepo = new ContaRepositoryJDBC();
        // Ex.: FuncionarioRepository funcionarioRepo = new FuncionarioRepositoryJDBC();

        // 2. Aplicação (Casos de Uso recebendo as abstrações via construtor - Liane)
        // Ex.: CadastrarCliente cadastrarCliente = new CadastrarCliente(clienteRepo);

        // 3. Apresentação (Interface Gráfica recebendo os casos de uso - Adryan)
        TelaPrincipal tela = new TelaPrincipal();
        tela.setVisible(true);
    }
}