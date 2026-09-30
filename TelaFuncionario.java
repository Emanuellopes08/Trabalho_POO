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
        setSize(900, 600);
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
        JLabel subtitulo = new JLabel("Área do funcionário");

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


        BotaoArredondado btnCadastrarCliente =
                new BotaoArredondado("Cadastrar Cliente");

        BotaoArredondado btnSair =
                new BotaoArredondado("Sair");


/////////////////////////////////////////////////////////////////////
// CONFIGURA OS BOTÕES
/////////////////////////////////////////////////////////////////////

        Dimension tamanhoBotao = new Dimension(260, 60);
        Font fonteBotao = new Font("Arial", Font.BOLD, 18);


        configurarBotaoVerde(
                btnCadastrarCliente,
                tamanhoBotao,
                fonteBotao
        );


        btnSair.setPreferredSize(tamanhoBotao);
        btnSair.setMaximumSize(tamanhoBotao);
        btnSair.setFont(fonteBotao);
        btnSair.setBackground(VERMELHO);
        btnSair.setForeground(BRANCO);
        btnSair.setAlignmentX(Component.CENTER_ALIGNMENT);


/////////////////////////////////////////////////////////////////////
// EFEITOS AO PASSAR O MOUSE
/////////////////////////////////////////////////////////////////////

        adicionarEfeitoMouse(
                btnCadastrarCliente,
                VERDE,
                VERDE_ESCURO
        );

        adicionarEfeitoMouse(
                btnSair,
                VERMELHO,
                VERMELHO_ESCURO
        );


/////////////////////////////////////////////////////////////////////
// ADICIONA OS BOTÕES
/////////////////////////////////////////////////////////////////////

        menu.add(Box.createVerticalGlue());

        menu.add(btnCadastrarCliente);

        menu.add(
                Box.createVerticalStrut(20)
        );

        menu.add(btnSair);

        menu.add(Box.createVerticalGlue());

        add(menu, BorderLayout.CENTER);


/////////////////////////////////////////////////////////////////////
// AÇÃO CADASTRAR CLIENTE
/////////////////////////////////////////////////////////////////////

        btnCadastrarCliente.addActionListener(e -> {

            TelaCadastroCliente tela =
                    new TelaCadastroCliente();

            tela.setVisible(true);
        });


/////////////////////////////////////////////////////////////////////
// AÇÃO SAIR
/////////////////////////////////////////////////////////////////////

        btnSair.addActionListener(e -> dispose());
    }

/////////////////////////////////////////////////////////////////////
// CONFIGURA O BOTÃO VERDE
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
            }

            @Override
            public void mouseExited(java.awt.event.MouseEvent evt) {
                botao.setBackground(corNormal);
            }
        });
    }
}