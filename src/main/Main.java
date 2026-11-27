
import javax.swing.SwingUtilities;
import javax.swing.JOptionPane;

import presentation.swing.*;
import application.casodeuso.autenticacao.*;
import application.casodeuso.cliente.*;
import application.casodeuso.conta.*;
import application.casodeuso.funcionario.*;
import application.repositories.*;
import infrastructure.persistence.*;

import java.io.IOException;
import java.sql.SQLException;

/**
 * Classe responsável pela inicialização do sistema Banco ES.
 *
 * Atua como ponto de composição da aplicação (Composition Root),
 * criando os repositórios, instanciando os casos de uso e realizando
 * a injeção das dependências necessárias.
 *
 * Também inicializa o banco de dados e inicia a interface gráfica.
 */
public class Main {

    /**
     * Executa a inicialização do sistema.
     *
     * Inicializa as tabelas do banco de dados, configura os repositórios,
     * monta os casos de uso e abre a tela principal.
     *
     * @param args argumentos recebidos na execução do programa
     */
    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> {

            try {
                Conexao.criarTabelas();

            } catch (SQLException | IOException ex) {
                JOptionPane.showMessageDialog(
                        null,
                        "Não foi possível inicializar o banco de dados: " + ex.getMessage(),
                        "Erro",
                        JOptionPane.ERROR_MESSAGE
                );
                return;
            }

            // Criação dos repositórios
            ClienteRepository clientes = new ClienteRepositoryJDBC();
            ContaRepository contas = new ContaRepositoryJDBC();
            FuncionarioRepository funcionarios = new FuncionarioRepositoryJDBC();

            // Criação e injeção dos casos de uso
            AplicacaoBanco aplicacao = new AplicacaoBanco(
                    new CadastrarCliente(clientes, clientes),
                    new ConsultarCliente(clientes),
                    new AlterarCliente(clientes, clientes),
                    new ExcluirCliente(clientes, clientes),
                    new CadastrarConta(contas),
                    new ConsultarContasCliente(clientes, contas),
                    new Depositar(contas),
                    new Sacar(contas),
                    new AutenticarCliente(clientes, contas),
                    new CadastrarFuncionario(funcionarios),
                    new ConsultarFuncionario(funcionarios),
                    new AlterarFuncionario(funcionarios),
                    new ExcluirFuncionario(funcionarios),
                    new AutenticarFuncionario(funcionarios),
                    new AutenticarGerente(funcionarios)
            );

            // Inicialização da interface gráfica
            TelaPrincipal tela = new TelaPrincipal(aplicacao);
            tela.setVisible(true);
        });
    }
}
