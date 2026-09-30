import javax.swing.*;
import java.awt.*;

public class TelaPrincipal extends JFrame {

    // Paleta de cores do projeto
    private final Color AZUL_ESCURO = new Color(14, 14, 37);
    private final Color AZUL_SECUNDARIO = new Color(28, 30, 56);

    private final Color VERDE = new Color(72, 255, 129);
    private final Color VERDE_ESCURO = new Color(45, 190, 95);

    private final Color VERMELHO = new Color(180, 70, 70);
    private final Color VERMELHO_ESCURO = new Color(140, 45, 45);

    private final Color BRANCO = Color.WHITE;


    public TelaPrincipal() {

        configurarJanela();
        criarTitulo();
        criarMenu();
    }

/////////////////////////////////////////////////////////////////////
//                      CONFIGURA A JANELA PRINCIPAL
/////////////////////////////////////////////////////////////////////

    private void configurarJanela() {

        setSize(900, 600);
        setTitle("Banco ES");

        setDefaultCloseOperation(EXIT_ON_CLOSE);

        // Faz a janela abrir no centro da tela
        setLocationRelativeTo(null);

        setLayout(new BorderLayout());


        // Cor do fundo da janela
        getContentPane().setBackground(AZUL_ESCURO);
    }

/////////////////////////////////////////////////////////////////////
//                      CRIA O TÍTULO DO SISTEMA
/////////////////////////////////////////////////////////////////////

    private void criarTitulo() {

        JPanel topo = new JPanel();

        topo.setLayout(
                new BoxLayout(
                        topo,
                        BoxLayout.Y_AXIS
                )
        );


        // Fundo igual ao restante da tela
        topo.setBackground(AZUL_ESCURO);


        // Espaço acima do título
        topo.add(
                Box.createVerticalStrut(25)
        );


        JLabel titulo =
                new JLabel("BANCO ES");

        JLabel subtitulo =
                new JLabel("Bem-vindo ao sistema");


        // Fonte do título
        titulo.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        45
                )
        );


        // Título branco
        titulo.setForeground(BRANCO);


        // Fonte do subtítulo
        subtitulo.setFont(
                new Font(
                        "Arial",
                        Font.PLAIN,
                        20
                )
        );


        // Subtítulo verde
        subtitulo.setForeground(VERDE);


        // Centraliza os textos
        titulo.setAlignmentX(
                Component.CENTER_ALIGNMENT
        );

        subtitulo.setAlignmentX(
                Component.CENTER_ALIGNMENT
        );


        topo.add(titulo);

        topo.add(
                Box.createVerticalStrut(5)
        );

        topo.add(subtitulo);


        add(
                topo,
                BorderLayout.NORTH
        );
    }

/////////////////////////////////////////////////////////////////////
//                      CRIA O MENU PRINCIPAL
/////////////////////////////////////////////////////////////////////

    private void criarMenu() {

        JPanel menu = new JPanel();

        menu.setLayout(
                new BoxLayout(
                        menu,
                        BoxLayout.Y_AXIS
                )
        );


        // Fundo do menu
        menu.setBackground(AZUL_ESCURO);


        // Criação dos botões
        BotaoArredondado btnGerente =
                new BotaoArredondado("Gerente");

        BotaoArredondado btnFuncionario =
                new BotaoArredondado("Funcionário");

        BotaoArredondado btnCliente =
                new BotaoArredondado("Cliente");

        BotaoArredondado btnSair =
                new BotaoArredondado("Sair");


        // Cor dos botões principais
        btnGerente.setBackground(VERDE);
        btnFuncionario.setBackground(VERDE);
        btnCliente.setBackground(VERDE);


        // Cor do botão sair
        btnSair.setBackground(VERMELHO);


        // Cor das letras dos botões verdes
        btnGerente.setForeground(AZUL_ESCURO);
        btnFuncionario.setForeground(AZUL_ESCURO);
        btnCliente.setForeground(AZUL_ESCURO);


        // Cor da letra do botão sair
        btnSair.setForeground(BRANCO);


        // Fonte dos botões
        Font fonteBotao =
                new Font(
                        "Arial",
                        Font.BOLD,
                        18
                );

        btnGerente.setFont(fonteBotao);
        btnFuncionario.setFont(fonteBotao);
        btnCliente.setFont(fonteBotao);
        btnSair.setFont(fonteBotao);


        // Tamanho dos botões
        Dimension tamanhoBotao =
                new Dimension(240, 60);


        btnGerente.setPreferredSize(tamanhoBotao);
        btnFuncionario.setPreferredSize(tamanhoBotao);
        btnCliente.setPreferredSize(tamanhoBotao);
        btnSair.setPreferredSize(tamanhoBotao);


        btnGerente.setMaximumSize(tamanhoBotao);
        btnFuncionario.setMaximumSize(tamanhoBotao);
        btnCliente.setMaximumSize(tamanhoBotao);
        btnSair.setMaximumSize(tamanhoBotao);


        // Centraliza os botões
        btnGerente.setAlignmentX(
                Component.CENTER_ALIGNMENT
        );

        btnFuncionario.setAlignmentX(
                Component.CENTER_ALIGNMENT
        );

        btnCliente.setAlignmentX(
                Component.CENTER_ALIGNMENT
        );

        btnSair.setAlignmentX(
                Component.CENTER_ALIGNMENT
        );


        // Efeito ao passar o mouse
        adicionarEfeitoMouseVerde(btnGerente);
        adicionarEfeitoMouseVerde(btnFuncionario);
        adicionarEfeitoMouseVerde(btnCliente);

        adicionarEfeitoMouseVermelho(btnSair);


        // Espaço flexível acima
        menu.add(
                Box.createVerticalGlue()
        );


        menu.add(btnGerente);

        menu.add(
                Box.createVerticalStrut(15)
        );


        menu.add(btnFuncionario);

        menu.add(
                Box.createVerticalStrut(15)
        );


        menu.add(btnCliente);

        menu.add(
                Box.createVerticalStrut(15)
        );


        menu.add(btnSair);


        // Espaço flexível abaixo
        menu.add(
                Box.createVerticalGlue()
        );


        add(
                menu,
                BorderLayout.CENTER
        );


        // Fecha o programa
        btnSair.addActionListener(e -> {

            System.exit(0);

        });

        //Abre uma nova janela de cadastro do cliente
        btnCliente.addActionListener(e -> {
            new TelaCadastroCliente();
        });
    }

/////////////////////////////////////////////////////////////////////
//                      EFEITO DOS BOTÕES VERDES
/////////////////////////////////////////////////////////////////////

    private void adicionarEfeitoMouseVerde(
            BotaoArredondado botao
    ) {

        botao.addMouseListener(
                new java.awt.event.MouseAdapter() {

                    @Override
                    public void mouseEntered(
                            java.awt.event.MouseEvent evt
                    ) {

                        botao.setBackground(
                                VERDE_ESCURO
                        );

                        botao.setForeground(
                                BRANCO
                        );
                    }

                    @Override
                    public void mouseExited(
                            java.awt.event.MouseEvent evt
                    ) {

                        botao.setBackground(
                                VERDE
                        );

                        botao.setForeground(
                                AZUL_ESCURO
                        );
                    }
                }
        );
    }

/////////////////////////////////////////////////////////////////////
//                      EFEITO DO BOTÃO SAIR
/////////////////////////////////////////////////////////////////////

    private void adicionarEfeitoMouseVermelho(
            BotaoArredondado botao
    ) {

        botao.addMouseListener(
                new java.awt.event.MouseAdapter() {

                    @Override
                    public void mouseEntered(
                            java.awt.event.MouseEvent evt
                    ) {

                        botao.setBackground(
                                VERMELHO_ESCURO
                        );
                    }

                    @Override
                    public void mouseExited(
                            java.awt.event.MouseEvent evt
                    ) {

                        botao.setBackground(
                                VERMELHO
                        );
                    }
                }
        );
    }
}