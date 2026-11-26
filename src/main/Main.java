import javax.swing.SwingUtilities;
import presentation.swing.*;
import infrastructure.persistence.Conexao;
import java.io.IOException;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                Conexao.criarTabelas();
            } catch (SQLException | IOException ex) {
                JOptionPane.showMessageDialog(null, "Não foi possível inicializar o banco de dados: " + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
                return;
            }
            AplicacaoBanco aplicacao = new AplicacaoBanco();
            TelaPrincipal tela = new TelaPrincipal(aplicacao);
            tela.setVisible(true);
        });
    }
}
