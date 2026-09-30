import javax.swing.*;
import java.awt.*;

public class TelaPrincipal extends JFrame {

    public TelaPrincipal() {

        configurarJanela();
        criarTitulo();
        criarMenu();
    }

/////////////////////////////////////////////////////////////////////

    // Configura a janela principal
    private void configurarJanela() {

        setSize(900, 600);
        setTitle("Banco ES");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        setLayout(new BorderLayout());
    }

/////////////////////////////////////////////////////////////////////

    // Método que cria o título do sistema
    private void criarTitulo() {

        JLabel titulo = new JLabel(
                "Bem-vindo ao Banco ES",
                SwingConstants.CENTER
        );

        add(titulo, BorderLayout.NORTH);
    }

//////////////////////////////////////////////////////////////////////

    // Método que cria o menu lateral
    private void criarMenu() {

        JPanel menu = new JPanel();

        menu.setLayout(
                new BoxLayout(menu, BoxLayout.Y_AXIS)
        );

        menu.setPreferredSize(
                new Dimension(180, 0)
        );

///////////////////////////////////////////////////////////////////////

        // Botões de acesso
        JButton btnGerente =
                new JButton("Gerente");

        JButton btnFuncionario =
                new JButton("Funcionário");

        JButton btnCliente =
                new JButton("Cliente");

        JButton btnSair =
                new JButton("Sair");

///////////////////////////////////////////////////////////////////////

        // Tamanho dos botões
        Dimension tamanhoBotao =
                new Dimension(180, 40);


        btnGerente.setMaximumSize(tamanhoBotao);
        btnFuncionario.setMaximumSize(tamanhoBotao);
        btnCliente.setMaximumSize(tamanhoBotao);
        btnSair.setMaximumSize(tamanhoBotao);

///////////////////////////////////////////////////////////////////////

        // Adiciona os botões no menu
        menu.add(btnGerente);

        menu.add(
                Box.createVerticalStrut(5)
        );


        menu.add(btnFuncionario);

        menu.add(
                Box.createVerticalStrut(5)
        );


        menu.add(btnCliente);

        menu.add(
                Box.createVerticalStrut(5)
        );

        menu.add(btnSair);

//////////////////////////////////////////////////////////////////////

        // Coloca o menu no lado esquerdo da janela
        add(menu, BorderLayout.WEST);

//////////////////////////////////////////////////////////////////////

        // Método que fecha o programa
        btnSair.addActionListener(e -> {

            System.exit(0);

        });
    }
}