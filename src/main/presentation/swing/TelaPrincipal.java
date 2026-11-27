package presentation.swing;

import javax.swing.*;
import java.awt.*;

public class TelaPrincipal extends JFrame {

    private final AplicacaoBanco aplicacao;

    // Paleta de cores do projeto
    private final Color AZUL_ESCURO = new Color(14, 14, 37);
    private final Color VERDE = new Color(72, 255, 129);
    private final Color VERDE_ESCURO = new Color(45, 190, 95);
    private final Color VERMELHO = new Color(180, 70, 70);
    private final Color VERMELHO_ESCURO = new Color(140, 45, 45);
    private final Color BRANCO = Color.WHITE;


    public TelaPrincipal(AplicacaoBanco aplicacao) {
        this.aplicacao = aplicacao;
        configurarJanela();
        criarTitulo();
        criarMenu();
            TemaBanco.aplicar(this);
    }

/////////////////////////////////////////////////////////////////////
// CONFIGURA A JANELA PRINCIPAL
    /////////////////////////////////////////////////////////////////////

    private void configurarJanela() {
        setSize(900, 600);
        setTitle("Banco ES");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        getContentPane().setBackground(AZUL_ESCURO);
    }

/////////////////////////////////////////////////////////////////////
// CRIA O TÍTULO DO SISTEMA
    /////////////////////////////////////////////////////////////////////

    private void criarTitulo() {
        JPanel topo = new JPanel();
        topo.setLayout(new BoxLayout(topo, BoxLayout.Y_AXIS));
        topo.setBackground(AZUL_ESCURO);
        topo.setBorder(BorderFactory.createEmptyBorder(18, 20, 12, 20));

        JPanel marca = new JPanel(new FlowLayout(FlowLayout.CENTER, 18, 0));
        marca.setBackground(AZUL_ESCURO);

        // O logotipo é carregado como recurso para funcionar também fora da IDE.
        java.net.URL recursoLogo = getClass().getResource("/logo-banco-es.png");
        if (recursoLogo != null) {
            JPanel molduraLogo = new JPanel(new BorderLayout()) {
                @Override
                protected void paintComponent(Graphics g) {
                    super.paintComponent(g);
                    Graphics2D g2 = (Graphics2D) g.create();
                    g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                            RenderingHints.VALUE_ANTIALIAS_ON);
                    g2.setColor(new Color(239, 244, 252));
                    g2.fillRoundRect(0, 0, getWidth(), getHeight(), 26, 26);
                    g2.dispose();
                }
            };
            molduraLogo.setOpaque(false);
            molduraLogo.setPreferredSize(new Dimension(124, 116));
            molduraLogo.setBorder(BorderFactory.createEmptyBorder(7, 7, 7, 7));

            Image imagem = new ImageIcon(recursoLogo).getImage()
                    .getScaledInstance(110, 99, Image.SCALE_SMOOTH);
            JLabel logo = new JLabel(new ImageIcon(imagem));
            logo.setHorizontalAlignment(SwingConstants.CENTER);
            logo.setOpaque(false);
            molduraLogo.add(logo, BorderLayout.CENTER);
            marca.add(molduraLogo);
        }

        JPanel textos = new JPanel();
        textos.setLayout(new BoxLayout(textos, BoxLayout.Y_AXIS));
        textos.setBackground(AZUL_ESCURO);

        JLabel titulo = new JLabel("BANCO ES");
        JLabel subtitulo = new JLabel("Bem-vindo ao seu banco digital");

        titulo.setFont(new Font("Segoe UI", Font.BOLD, 39));
        titulo.setForeground(BRANCO);
        subtitulo.setFont(new Font("Segoe UI", Font.PLAIN, 17));
        subtitulo.setForeground(VERDE);

        titulo.setAlignmentX(Component.LEFT_ALIGNMENT);
        subtitulo.setAlignmentX(Component.LEFT_ALIGNMENT);
        textos.add(Box.createVerticalGlue());
        textos.add(titulo);
        textos.add(Box.createVerticalStrut(5));
        textos.add(subtitulo);
        textos.add(Box.createVerticalGlue());
        marca.add(textos);

        topo.add(marca);
        add(topo, BorderLayout.NORTH);
    }

/////////////////////////////////////////////////////////////////////
// CRIA O MENU PRINCIPAL
    /////////////////////////////////////////////////////////////////////

    private void criarMenu() {
        JPanel menu = new JPanel();

        menu.setLayout(new BoxLayout(menu, BoxLayout.Y_AXIS));
        menu.setBackground(AZUL_ESCURO);


        BotaoArredondado btnGerente = new BotaoArredondado("Gerente");
        BotaoArredondado btnFuncionario = new BotaoArredondado("Funcionário");
        BotaoArredondado btnCliente = new BotaoArredondado("Cliente");
        BotaoArredondado btnSair = new BotaoArredondado("Sair");

/////////////////////////////////////////////////////////////////////
// CONFIGURAÇÃO DAS CORES
/////////////////////////////////////////////////////////////////////

        btnGerente.setBackground(VERDE);
        btnFuncionario.setBackground(VERDE);
        btnCliente.setBackground(VERDE);
        btnSair.setBackground(VERMELHO);

        btnGerente.setForeground(AZUL_ESCURO);
        btnFuncionario.setForeground(AZUL_ESCURO);
        btnCliente.setForeground(AZUL_ESCURO);
        btnSair.setForeground(BRANCO);

/////////////////////////////////////////////////////////////////////
// CONFIGURAÇÃO DA FONTE
/////////////////////////////////////////////////////////////////////

        Font fonteBotao = new Font("Arial", Font.BOLD, 18);

        btnGerente.setFont(fonteBotao);
        btnFuncionario.setFont(fonteBotao);
        btnCliente.setFont(fonteBotao);
        btnSair.setFont(fonteBotao);

/////////////////////////////////////////////////////////////////////
// CONFIGURAÇÃO DO TAMANHO
/////////////////////////////////////////////////////////////////////

        Dimension tamanhoBotao = new Dimension(240, 60);

        btnGerente.setPreferredSize(tamanhoBotao);
        btnFuncionario.setPreferredSize(tamanhoBotao);
        btnCliente.setPreferredSize(tamanhoBotao);
        btnSair.setPreferredSize(tamanhoBotao);

        btnGerente.setMaximumSize(tamanhoBotao);
        btnFuncionario.setMaximumSize(tamanhoBotao);
        btnCliente.setMaximumSize(tamanhoBotao);
        btnSair.setMaximumSize(tamanhoBotao);

/////////////////////////////////////////////////////////////////////
// CENTRALIZA OS BOTÕES
/////////////////////////////////////////////////////////////////////

        btnGerente.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnFuncionario.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnCliente.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnSair.setAlignmentX(Component.CENTER_ALIGNMENT);

/////////////////////////////////////////////////////////////////////
// EFEITO AO PASSAR O MOUSE
/////////////////////////////////////////////////////////////////////

        adicionarEfeitoMouseVerde(btnGerente);
        adicionarEfeitoMouseVerde(btnFuncionario);
        adicionarEfeitoMouseVerde(btnCliente);
        adicionarEfeitoMouseVermelho(btnSair);

/////////////////////////////////////////////////////////////////////
// ADICIONA OS BOTÕES NA TELA
/////////////////////////////////////////////////////////////////////

        menu.add(Box.createVerticalGlue());

        menu.add(btnGerente);
        menu.add(Box.createVerticalStrut(15));

        menu.add(btnFuncionario);
        menu.add(Box.createVerticalStrut(15));

        menu.add(btnCliente);
        menu.add(Box.createVerticalStrut(15));

        menu.add(btnSair);

        menu.add(Box.createVerticalGlue());

        add(menu, BorderLayout.CENTER);

/////////////////////////////////////////////////////////////////////
// AÇÃO DO BOTÃO GERENTE
/////////////////////////////////////////////////////////////////////

        btnGerente.addActionListener(e -> {
            TelaLoginGerente telaGerente = new TelaLoginGerente(aplicacao);
            telaGerente.setVisible(true);
        });

/////////////////////////////////////////////////////////////////////
// AÇÃO DO BOTÃO FUNCIONÁRIO
/////////////////////////////////////////////////////////////////////

        btnFuncionario.addActionListener(e -> {
            TelaLoginFuncionario telaFuncionario = new TelaLoginFuncionario(aplicacao);
            telaFuncionario.setVisible(true);
        });

/////////////////////////////////////////////////////////////////////
// AÇÃO DO BOTÃO CLIENTE
/////////////////////////////////////////////////////////////////////

        btnCliente.addActionListener(e -> {
            TelaLoginCliente telaCliente = new TelaLoginCliente(aplicacao);
            telaCliente.setVisible(true);
        });

/////////////////////////////////////////////////////////////////////
// AÇÃO DO BOTÃO SAIR
/////////////////////////////////////////////////////////////////////

        btnSair.addActionListener(e -> System.exit(0));
    }

/////////////////////////////////////////////////////////////////////
// EFEITO DOS BOTÕES VERDES
    /////////////////////////////////////////////////////////////////////

    private void adicionarEfeitoMouseVerde(BotaoArredondado botao) {

        botao.addMouseListener(new java.awt.event.MouseAdapter() {

            @Override
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                botao.setBackground(VERDE_ESCURO);
                botao.setForeground(BRANCO);
            }

            @Override
            public void mouseExited(java.awt.event.MouseEvent evt) {
                botao.setBackground(VERDE);
                botao.setForeground(AZUL_ESCURO);
            }
        });
    }

/////////////////////////////////////////////////////////////////////
// EFEITO DO BOTÃO SAIR
    /////////////////////////////////////////////////////////////////////

    private void adicionarEfeitoMouseVermelho(BotaoArredondado botao) {

        botao.addMouseListener(new java.awt.event.MouseAdapter() {

            @Override
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                botao.setBackground(VERMELHO_ESCURO);
            }

            @Override
            public void mouseExited(java.awt.event.MouseEvent evt) {
                botao.setBackground(VERMELHO);
            }
        });
    }
}