import javax.swing.*;
import java.awt.*;
import java.util.List;

public class TelaLoginCliente extends JFrame {

    // Paleta de cores
    private final Color AZUL_ESCURO = new Color(14, 14, 37);
    private final Color VERDE = new Color(72, 255, 129);
    private final Color VERDE_ESCURO = new Color(45, 190, 95);
    private final Color VERMELHO = new Color(180, 70, 70);
    private final Color VERMELHO_ESCURO = new Color(140, 45, 45);
    private final Color VERMELHO_ERRO = new Color(255, 90, 90);
    private final Color BRANCO = Color.WHITE;

    // Componentes
    private JTextField campoCpf;
    private JLabel mensagemErro;


    public TelaLoginCliente() {
        configurarJanela();
        criarTitulo();
        criarFormulario();
    }

/////////////////////////////////////////////////////////////////////
// CONFIGURA A JANELA
/////////////////////////////////////////////////////////////////////

    private void configurarJanela() {
        setSize(550, 500);
        setTitle("Banco ES - Login do Cliente");
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

        topo.add(Box.createVerticalStrut(45));

        JLabel titulo = new JLabel("LOGIN DO CLIENTE");
        JLabel subtitulo = new JLabel("Acesse sua conta");

        titulo.setFont(new Font("Arial", Font.BOLD, 32));
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

        gbc.insets = new Insets(8, 10, 8, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

/////////////////////////////////////////////////////////////////////
// CPF
/////////////////////////////////////////////////////////////////////

        JLabel labelCpf = new JLabel("CPF:");

        labelCpf.setFont(new Font("Arial", Font.BOLD, 16));
        labelCpf.setForeground(BRANCO);

        campoCpf = new JTextField(22);

        campoCpf.setFont(new Font("Arial", Font.PLAIN, 16));

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 1;

        painel.add(labelCpf, gbc);

        gbc.gridx = 1;

        painel.add(campoCpf, gbc);

/////////////////////////////////////////////////////////////////////
// MENSAGEM DE ERRO
/////////////////////////////////////////////////////////////////////

        mensagemErro = new JLabel(" ");

        mensagemErro.setFont(new Font("Arial", Font.PLAIN, 14));
        mensagemErro.setForeground(VERMELHO_ERRO);
        mensagemErro.setHorizontalAlignment(SwingConstants.CENTER);

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 2;

        painel.add(mensagemErro, gbc);

/////////////////////////////////////////////////////////////////////
// BOTÃO ENTRAR
/////////////////////////////////////////////////////////////////////

        BotaoArredondado btnEntrar = new BotaoArredondado("Entrar");

        btnEntrar.setPreferredSize(new Dimension(180, 50));
        btnEntrar.setFont(new Font("Arial", Font.BOLD, 16));
        btnEntrar.setBackground(VERDE);
        btnEntrar.setForeground(AZUL_ESCURO);

        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        gbc.insets = new Insets(20, 10, 8, 10);

        painel.add(btnEntrar, gbc);

/////////////////////////////////////////////////////////////////////
// BOTÃO VOLTAR
/////////////////////////////////////////////////////////////////////

        BotaoArredondado btnVoltar = new BotaoArredondado("Voltar");

        btnVoltar.setPreferredSize(new Dimension(180, 50));
        btnVoltar.setFont(new Font("Arial", Font.BOLD, 16));
        btnVoltar.setBackground(VERMELHO);
        btnVoltar.setForeground(BRANCO);

        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        gbc.insets = new Insets(8, 10, 10, 10);

        painel.add(btnVoltar, gbc);

        add(painel, BorderLayout.CENTER);

/////////////////////////////////////////////////////////////////////
// AÇÃO ENTRAR
/////////////////////////////////////////////////////////////////////

        btnEntrar.addActionListener(e -> validarLogin());

/////////////////////////////////////////////////////////////////////
// AÇÃO VOLTAR
/////////////////////////////////////////////////////////////////////

        btnVoltar.addActionListener(e -> dispose());

/////////////////////////////////////////////////////////////////////
// EFEITOS DOS BOTÕES
/////////////////////////////////////////////////////////////////////

        adicionarEfeitoMouse(btnEntrar, VERDE, VERDE_ESCURO);
        adicionarEfeitoMouse(btnVoltar, VERMELHO, VERMELHO_ESCURO);
    }

/////////////////////////////////////////////////////////////////////
// VALIDA O LOGIN
/////////////////////////////////////////////////////////////////////

    private void validarLogin() {
        String cpf = campoCpf.getText().trim();

        mensagemErro.setText(" ");

/////////////////////////////////////////////////////////////////////
// VALIDA CPF
/////////////////////////////////////////////////////////////////////

        if (cpf.isEmpty()) {
            mensagemErro.setText("Digite o CPF do cliente.");
            return;
        }

/////////////////////////////////////////////////////////////////////
// BUSCA CLIENTE
/////////////////////////////////////////////////////////////////////

        Cliente cliente = SistemaBanco.buscarClientePorCpf(cpf);

        if (cliente == null) {
            mensagemErro.setText("Cliente não encontrado.");
            return;
        }

/////////////////////////////////////////////////////////////////////
// BUSCA CONTAS DO CLIENTE
/////////////////////////////////////////////////////////////////////

        List<Conta> contasCliente = SistemaBanco.buscarContasDoCliente(cliente);

        if (contasCliente.isEmpty()) {
            mensagemErro.setText("Nenhuma conta encontrada para este cliente.");
            return;
        }

/////////////////////////////////////////////////////////////////////
// ABRE A TELA DO CLIENTE
/////////////////////////////////////////////////////////////////////

        TelaCliente telaCliente = new TelaCliente(cliente, contasCliente);
        telaCliente.setVisible(true);

        dispose();
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