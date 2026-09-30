import javax.swing.*;
import java.awt.*;

public class TelaAlterarCliente extends JFrame {

    // Paleta de cores
    private final Color AZUL_ESCURO = new Color(14, 14, 37);
    private final Color VERDE = new Color(72, 255, 129);
    private final Color VERDE_ESCURO = new Color(45, 190, 95);
    private final Color VERMELHO = new Color(180, 70, 70);
    private final Color VERMELHO_ESCURO = new Color(140, 45, 45);
    private final Color VERMELHO_ERRO = new Color(255, 90, 90);
    private final Color BRANCO = Color.WHITE;

    // Componentes
    private JComboBox<String> tipoConta;

    private JLabel labelDocumento;
    private JTextField campoDocumento;
    private JLabel erroDocumento;

    private JTextField campoNome;
    private JTextField campoRenda;
    private JTextField campoTelefone;
    private JTextField campoEndereco;
    private JTextField campoEmail;


    public TelaAlterarCliente() {
        configurarJanela();
        criarTitulo();
        criarFormulario();
    }

/////////////////////////////////////////////////////////////////////
// CONFIGURA A JANELA
/////////////////////////////////////////////////////////////////////

    private void configurarJanela() {
        setSize(700, 700);
        setTitle("Banco ES - Alterar Cliente");
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

        topo.add(Box.createVerticalStrut(25));

        JLabel titulo = new JLabel("ALTERAR CLIENTE");
        JLabel subtitulo = new JLabel("Busque o cliente e altere seus dados");

        titulo.setFont(new Font("Arial", Font.BOLD, 34));
        titulo.setForeground(BRANCO);

        subtitulo.setFont(new Font("Arial", Font.PLAIN, 17));
        subtitulo.setForeground(VERDE);

        titulo.setAlignmentX(Component.CENTER_ALIGNMENT);
        subtitulo.setAlignmentX(Component.CENTER_ALIGNMENT);

        topo.add(titulo);
        topo.add(Box.createVerticalStrut(5));
        topo.add(subtitulo);

        add(topo, BorderLayout.NORTH);
    }

/////////////////////////////////////////////////////////////////////
// CRIA O FORMULÁRIO
/////////////////////////////////////////////////////////////////////

    private void criarFormulario() {
        JPanel painel = new JPanel();

        painel.setBackground(AZUL_ESCURO);
        painel.setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();

        gbc.insets = new Insets(6, 10, 6, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        Font fonteLabel = new Font("Arial", Font.BOLD, 16);

/////////////////////////////////////////////////////////////////////
// TIPO DE CONTA
/////////////////////////////////////////////////////////////////////

        JLabel labelTipoConta = new JLabel("Tipo de conta:");
        labelTipoConta.setFont(fonteLabel);
        labelTipoConta.setForeground(BRANCO);

        tipoConta = new JComboBox<>(new String[]{
                "Conta Poupança",
                "Conta Corrente",
                "Conta Conjunta",
                "Conta PJ"
        });

        tipoConta.setFont(new Font("Arial", Font.PLAIN, 15));

        gbc.gridx = 0;
        gbc.gridy = 0;
        painel.add(labelTipoConta, gbc);

        gbc.gridx = 1;
        painel.add(tipoConta, gbc);

/////////////////////////////////////////////////////////////////////
// CPF OU CNPJ
/////////////////////////////////////////////////////////////////////

        labelDocumento = new JLabel("CPF:");
        labelDocumento.setFont(fonteLabel);
        labelDocumento.setForeground(BRANCO);

        campoDocumento = new JTextField(22);

        erroDocumento = new JLabel(" ");
        erroDocumento.setForeground(VERMELHO_ERRO);

        gbc.gridx = 0;
        gbc.gridy = 1;
        painel.add(labelDocumento, gbc);

        gbc.gridx = 1;
        painel.add(campoDocumento, gbc);

        gbc.gridx = 1;
        gbc.gridy = 2;
        painel.add(erroDocumento, gbc);

/////////////////////////////////////////////////////////////////////
// BOTÃO BUSCAR
/////////////////////////////////////////////////////////////////////

        BotaoArredondado btnBuscar = new BotaoArredondado("Buscar Cliente");

        Dimension tamanhoBuscar = new Dimension(180, 45);

        btnBuscar.setPreferredSize(tamanhoBuscar);
        btnBuscar.setFont(new Font("Arial", Font.BOLD, 15));
        btnBuscar.setBackground(VERDE);
        btnBuscar.setForeground(AZUL_ESCURO);

        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        gbc.insets = new Insets(10, 10, 20, 10);

        painel.add(btnBuscar, gbc);

/////////////////////////////////////////////////////////////////////
// NOME
/////////////////////////////////////////////////////////////////////

        JLabel labelNome = new JLabel("Nome:");
        labelNome.setFont(fonteLabel);
        labelNome.setForeground(BRANCO);

        campoNome = new JTextField(22);

        gbc.gridwidth = 1;
        gbc.insets = new Insets(6, 10, 6, 10);

        gbc.gridx = 0;
        gbc.gridy = 4;
        painel.add(labelNome, gbc);

        gbc.gridx = 1;
        painel.add(campoNome, gbc);

/////////////////////////////////////////////////////////////////////
// RENDA
/////////////////////////////////////////////////////////////////////

        JLabel labelRenda = new JLabel("Renda:");
        labelRenda.setFont(fonteLabel);
        labelRenda.setForeground(BRANCO);

        campoRenda = new JTextField(22);

        gbc.gridx = 0;
        gbc.gridy = 5;
        painel.add(labelRenda, gbc);

        gbc.gridx = 1;
        painel.add(campoRenda, gbc);

/////////////////////////////////////////////////////////////////////
// TELEFONE
/////////////////////////////////////////////////////////////////////

        JLabel labelTelefone = new JLabel("Telefone:");
        labelTelefone.setFont(fonteLabel);
        labelTelefone.setForeground(BRANCO);

        campoTelefone = new JTextField(22);

        gbc.gridx = 0;
        gbc.gridy = 6;
        painel.add(labelTelefone, gbc);

        gbc.gridx = 1;
        painel.add(campoTelefone, gbc);

/////////////////////////////////////////////////////////////////////
// ENDEREÇO
/////////////////////////////////////////////////////////////////////

        JLabel labelEndereco = new JLabel("Endereço:");
        labelEndereco.setFont(fonteLabel);
        labelEndereco.setForeground(BRANCO);

        campoEndereco = new JTextField(22);

        gbc.gridx = 0;
        gbc.gridy = 7;
        painel.add(labelEndereco, gbc);

        gbc.gridx = 1;
        painel.add(campoEndereco, gbc);

/////////////////////////////////////////////////////////////////////
// EMAIL
/////////////////////////////////////////////////////////////////////

        JLabel labelEmail = new JLabel("Email:");
        labelEmail.setFont(fonteLabel);
        labelEmail.setForeground(BRANCO);

        campoEmail = new JTextField(22);

        gbc.gridx = 0;
        gbc.gridy = 8;
        painel.add(labelEmail, gbc);

        gbc.gridx = 1;
        painel.add(campoEmail, gbc);

/////////////////////////////////////////////////////////////////////
// BOTÕES SALVAR E VOLTAR
/////////////////////////////////////////////////////////////////////

        BotaoArredondado btnSalvar = new BotaoArredondado("Salvar Alterações");
        BotaoArredondado btnVoltar = new BotaoArredondado("Voltar");

        Dimension tamanhoBotao = new Dimension(190, 50);
        Font fonteBotao = new Font("Arial", Font.BOLD, 16);

        btnSalvar.setPreferredSize(tamanhoBotao);
        btnVoltar.setPreferredSize(tamanhoBotao);

        btnSalvar.setFont(fonteBotao);
        btnVoltar.setFont(fonteBotao);

        btnSalvar.setBackground(VERDE);
        btnSalvar.setForeground(AZUL_ESCURO);

        btnVoltar.setBackground(VERMELHO);
        btnVoltar.setForeground(BRANCO);

        JPanel painelBotoes = new JPanel();

        painelBotoes.setBackground(AZUL_ESCURO);
        painelBotoes.add(btnVoltar);
        painelBotoes.add(btnSalvar);

        gbc.gridx = 0;
        gbc.gridy = 9;
        gbc.gridwidth = 2;
        gbc.insets = new Insets(25, 10, 10, 10);

        painel.add(painelBotoes, gbc);

        add(painel, BorderLayout.CENTER);

/////////////////////////////////////////////////////////////////////
// ALTERA CPF OU CNPJ
/////////////////////////////////////////////////////////////////////

        tipoConta.addActionListener(e -> alterarTipoDocumento());

/////////////////////////////////////////////////////////////////////
// AÇÃO BUSCAR
/////////////////////////////////////////////////////////////////////

        btnBuscar.addActionListener(e -> {

            /*
             * Integração será feita depois.
             *
             * Conta Poupança -> CPF
             * Conta Corrente -> CPF
             * Conta Conjunta -> CPF
             * Conta PJ -> CNPJ
             */

        });

/////////////////////////////////////////////////////////////////////
// AÇÃO SALVAR ALTERAÇÕES
/////////////////////////////////////////////////////////////////////

        btnSalvar.addActionListener(e -> {

            /*
             * Depois vamos integrar com o SistemaBanco
             * e salvar as alterações do cliente.
             */

        });

/////////////////////////////////////////////////////////////////////
// AÇÃO VOLTAR
/////////////////////////////////////////////////////////////////////

        btnVoltar.addActionListener(e -> dispose());

/////////////////////////////////////////////////////////////////////
// EFEITOS DOS BOTÕES
/////////////////////////////////////////////////////////////////////

        adicionarEfeitoMouse(btnBuscar, VERDE, VERDE_ESCURO);
        adicionarEfeitoMouse(btnSalvar, VERDE, VERDE_ESCURO);
        adicionarEfeitoMouse(btnVoltar, VERMELHO, VERMELHO_ESCURO);
    }

/////////////////////////////////////////////////////////////////////
// ALTERA ENTRE CPF E CNPJ
/////////////////////////////////////////////////////////////////////

    private void alterarTipoDocumento() {
        String contaSelecionada = (String) tipoConta.getSelectedItem();

        campoDocumento.setText("");
        erroDocumento.setText(" ");

        if ("Conta PJ".equals(contaSelecionada)) {
            labelDocumento.setText("CNPJ:");
        } else {
            labelDocumento.setText("CPF:");
        }
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