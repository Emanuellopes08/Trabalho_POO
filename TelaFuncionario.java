import javax.swing.*;
import java.awt.*;

public class TelaFuncionario extends JFrame {

    // Paleta de cores
    private final Color AZUL_ESCURO = new Color(14, 14, 37);
    private final Color VERDE = new Color(72, 255, 129);
    private final Color VERDE_ESCURO = new Color(45, 190, 95);
    private final Color VERMELHO = new Color(180, 70, 70);
    private final Color VERMELHO_ESCURO = new Color(140, 45, 45);
    private final Color BRANCO = Color.WHITE;


    public TelaFuncionario() {
        configurarJanela();
        criarTitulo();
        criarMenu();
    }

/////////////////////////////////////////////////////////////////////
// CONFIGURA A JANELA
/////////////////////////////////////////////////////////////////////

    private void configurarJanela() {
        setSize(900, 650);
        setTitle("Banco ES - Funcionário");
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        getContentPane().setBackground(AZUL_ESCURO);
    }

/////////////////////////////////////////////////////////////////////
// CRIA O TÍTULO
/////////////////////////////////////////////////////////////////////

    private void criarTitulo() {
        JPanel topo = new JPanel();

        topo.setBackground(AZUL_ESCURO);
        topo.setLayout(new BoxLayout(topo, BoxLayout.Y_AXIS));

        topo.add(Box.createVerticalStrut(30));

        JLabel titulo = new JLabel("FUNCIONÁRIO");
        JLabel subtitulo = new JLabel("Gerenciamento de clientes");

        titulo.setFont(new Font("Arial", Font.BOLD, 40));
        titulo.setForeground(BRANCO);

        subtitulo.setFont(new Font("Arial", Font.PLAIN, 18));
        subtitulo.setForeground(VERDE);

        titulo.setAlignmentX(Component.CENTER_ALIGNMENT);
        subtitulo.setAlignmentX(Component.CENTER_ALIGNMENT);

        topo.add(titulo);
        topo.add(Box.createVerticalStrut(5));
        topo.add(subtitulo);

        add(topo, BorderLayout.NORTH);
    }

/////////////////////////////////////////////////////////////////////
// CRIA O MENU
/////////////////////////////////////////////////////////////////////

    private void criarMenu() {
        JPanel menu = new JPanel();

        menu.setBackground(AZUL_ESCURO);
        menu.setLayout(new BoxLayout(menu, BoxLayout.Y_AXIS));

        BotaoArredondado btnCadastrar = new BotaoArredondado("Cadastrar Cliente");
        BotaoArredondado btnConsultar = new BotaoArredondado("Consultar Cliente");
        BotaoArredondado btnAlterar = new BotaoArredondado("Alterar Cliente");
        BotaoArredondado btnExcluir = new BotaoArredondado("Excluir Cliente");
        BotaoArredondado btnVoltar = new BotaoArredondado("Voltar");

/////////////////////////////////////////////////////////////////////
// CONFIGURAÇÃO DOS BOTÕES
/////////////////////////////////////////////////////////////////////

        Dimension tamanhoBotao = new Dimension(280, 55);
        Font fonteBotao = new Font("Arial", Font.BOLD, 17);

        configurarBotaoVerde(btnCadastrar, tamanhoBotao, fonteBotao);
        configurarBotaoVerde(btnConsultar, tamanhoBotao, fonteBotao);
        configurarBotaoVerde(btnAlterar, tamanhoBotao, fonteBotao);
        configurarBotaoVerde(btnExcluir, tamanhoBotao, fonteBotao);

        btnVoltar.setPreferredSize(tamanhoBotao);
        btnVoltar.setMaximumSize(tamanhoBotao);
        btnVoltar.setFont(fonteBotao);
        btnVoltar.setBackground(VERMELHO);
        btnVoltar.setForeground(BRANCO);
        btnVoltar.setAlignmentX(Component.CENTER_ALIGNMENT);

/////////////////////////////////////////////////////////////////////
// EFEITOS DO MOUSE
/////////////////////////////////////////////////////////////////////

        adicionarEfeitoMouse(btnCadastrar, VERDE, VERDE_ESCURO);
        adicionarEfeitoMouse(btnConsultar, VERDE, VERDE_ESCURO);
        adicionarEfeitoMouse(btnAlterar, VERDE, VERDE_ESCURO);
        adicionarEfeitoMouse(btnExcluir, VERDE, VERDE_ESCURO);
        adicionarEfeitoMouse(btnVoltar, VERMELHO, VERMELHO_ESCURO);

/////////////////////////////////////////////////////////////////////
// ADICIONA OS BOTÕES NA TELA
/////////////////////////////////////////////////////////////////////

        menu.add(Box.createVerticalGlue());

        menu.add(btnCadastrar);
        menu.add(Box.createVerticalStrut(15));

        menu.add(btnConsultar);
        menu.add(Box.createVerticalStrut(15));

        menu.add(btnAlterar);
        menu.add(Box.createVerticalStrut(15));

        menu.add(btnExcluir);
        menu.add(Box.createVerticalStrut(15));

        menu.add(btnVoltar);

        menu.add(Box.createVerticalGlue());

        add(menu, BorderLayout.CENTER);

/////////////////////////////////////////////////////////////////////
// AÇÃO CADASTRAR CLIENTE
/////////////////////////////////////////////////////////////////////

        btnCadastrar.addActionListener(e -> {
            TelaCadastroCliente telaCadastro = new TelaCadastroCliente();
            telaCadastro.setVisible(true);
        });

/////////////////////////////////////////////////////////////////////
// AÇÃO CONSULTAR CLIENTE
/////////////////////////////////////////////////////////////////////

        btnConsultar.addActionListener(e -> {
            TelaConsultaCliente telaConsulta = new TelaConsultaCliente();
            telaConsulta.setVisible(true);
        });

/////////////////////////////////////////////////////////////////////
// AÇÃO ALTERAR CLIENTE
/////////////////////////////////////////////////////////////////////

        btnAlterar.addActionListener(e -> {
            TelaAlterarCliente telaAlterar = new TelaAlterarCliente();
            telaAlterar.setVisible(true);
        });

/////////////////////////////////////////////////////////////////////
// AÇÃO EXCLUIR CLIENTE
/////////////////////////////////////////////////////////////////////

        btnExcluir.addActionListener(e -> {

            /*
             * Depois vamos criar:
             *
             * TelaExcluirCliente
             */

        });

/////////////////////////////////////////////////////////////////////
// AÇÃO VOLTAR
/////////////////////////////////////////////////////////////////////

        btnVoltar.addActionListener(e -> dispose());
    }

/////////////////////////////////////////////////////////////////////
// CONFIGURA OS BOTÕES VERDES
/////////////////////////////////////////////////////////////////////

    private void configurarBotaoVerde(BotaoArredondado botao, Dimension tamanho, Font fonte) {
        botao.setPreferredSize(tamanho);
        botao.setMaximumSize(tamanho);
        botao.setFont(fonte);
        botao.setBackground(VERDE);
        botao.setForeground(AZUL_ESCURO);
        botao.setAlignmentX(Component.CENTER_ALIGNMENT);
    }

/////////////////////////////////////////////////////////////////////
// EFEITO AO PASSAR O MOUSE
/////////////////////////////////////////////////////////////////////

    private void adicionarEfeitoMouse(BotaoArredondado botao, Color corNormal, Color corEscura) {

        botao.addMouseListener(new java.awt.event.MouseAdapter() {

            @Override
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                botao.setBackground(corEscura);

                if (corNormal.equals(VERDE)) {
                    botao.setForeground(BRANCO);
                }
            }

            @Override
            public void mouseExited(java.awt.event.MouseEvent evt) {
                botao.setBackground(corNormal);

                if (corNormal.equals(VERDE)) {
                    botao.setForeground(AZUL_ESCURO);
                }
            }
        });
    }
}