import javax.swing.*;
import java.awt.*;

public class TelaLoginFuncionario extends JFrame {

    // Paleta de cores
    private final Color AZUL_ESCURO = new Color(14, 14, 37);
    private final Color AZUL_SECUNDARIO = new Color(28, 30, 56);
    private final Color VERDE = new Color(72, 255, 129);
    private final Color VERDE_ESCURO = new Color(45, 190, 95);
    private final Color VERMELHO_ERRO = new Color(255, 90, 90);
    private final Color BRANCO = Color.WHITE;

    // Campos do login
    private JTextField campoUsuario;
    private JPasswordField campoSenha;
    private JTextField campoCodigo;

    // Mensagens de erro
    private JLabel erroUsuario;
    private JLabel erroSenha;
    private JLabel erroCodigo;


    public TelaLoginFuncionario() {
        configurarJanela();
        criarTitulo();
        criarFormulario();
    }

/////////////////////////////////////////////////////////////////////
//                  CONFIGURA A JANELA
/////////////////////////////////////////////////////////////////////

    private void configurarJanela() {
        setSize(550, 550);
        setTitle("Banco ES - Login Funcionário");
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());
        getContentPane().setBackground(AZUL_ESCURO);
    }

/////////////////////////////////////////////////////////////////////
//                      CRIA O TÍTULO
/////////////////////////////////////////////////////////////////////

    private void criarTitulo() {
        JPanel topo = new JPanel();

        topo.setBackground(AZUL_ESCURO);
        topo.setLayout(new BoxLayout(topo, BoxLayout.Y_AXIS));

        topo.add(Box.createVerticalStrut(30));

        JLabel titulo = new JLabel("FUNCIONÁRIO");
        JLabel subtitulo = new JLabel("Entre com seus dados");

        titulo.setFont(new Font("Arial", Font.BOLD, 35));
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
//                  CRIA O FORMULÁRIO
/////////////////////////////////////////////////////////////////////

    private void criarFormulario() {
        JPanel painel = new JPanel();

        painel.setBackground(AZUL_ESCURO);
        painel.setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();

        gbc.insets = new Insets(5, 10, 5, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        Font fonteLabel = new Font("Arial", Font.BOLD, 16);

/////////////////////////////////////////////////////////////////////
//                      USUÁRIO
/////////////////////////////////////////////////////////////////////

        JLabel lblUsuario = new JLabel("Usuário:");
        lblUsuario.setFont(fonteLabel);
        lblUsuario.setForeground(BRANCO);

        campoUsuario = new JTextField(20);

        erroUsuario = new JLabel(" ");
        erroUsuario.setForeground(VERMELHO_ERRO);

        gbc.gridx = 0;
        gbc.gridy = 0;
        painel.add(lblUsuario, gbc);

        gbc.gridx = 1;
        painel.add(campoUsuario, gbc);

        gbc.gridx = 1;
        gbc.gridy = 1;
        painel.add(erroUsuario, gbc);

/////////////////////////////////////////////////////////////////////
//                      SENHA
/////////////////////////////////////////////////////////////////////

        JLabel lblSenha = new JLabel("Senha:");
        lblSenha.setFont(fonteLabel);
        lblSenha.setForeground(BRANCO);

        campoSenha = new JPasswordField(20);

        erroSenha = new JLabel(" ");
        erroSenha.setForeground(VERMELHO_ERRO);

        gbc.gridx = 0;
        gbc.gridy = 2;
        painel.add(lblSenha, gbc);

        gbc.gridx = 1;
        painel.add(campoSenha, gbc);

        gbc.gridx = 1;
        gbc.gridy = 3;
        painel.add(erroSenha, gbc);

/////////////////////////////////////////////////////////////////////
//                      CÓDIGO
/////////////////////////////////////////////////////////////////////

        JLabel lblCodigo = new JLabel("Código:");
        lblCodigo.setFont(fonteLabel);
        lblCodigo.setForeground(BRANCO);

        campoCodigo = new JTextField(20);

        erroCodigo = new JLabel(" ");
        erroCodigo.setForeground(VERMELHO_ERRO);

        gbc.gridx = 0;
        gbc.gridy = 4;
        painel.add(lblCodigo, gbc);

        gbc.gridx = 1;
        painel.add(campoCodigo, gbc);

        gbc.gridx = 1;
        gbc.gridy = 5;
        painel.add(erroCodigo, gbc);

/////////////////////////////////////////////////////////////////////
//                      BOTÕES
/////////////////////////////////////////////////////////////////////

        BotaoArredondado btnEntrar = new BotaoArredondado("Entrar");
        BotaoArredondado btnVoltar = new BotaoArredondado("Voltar");

        Dimension tamanhoBotao = new Dimension(150, 50);

        btnEntrar.setPreferredSize(tamanhoBotao);
        btnVoltar.setPreferredSize(tamanhoBotao);

        btnEntrar.setBackground(VERDE);
        btnEntrar.setForeground(AZUL_ESCURO);

        btnVoltar.setBackground(AZUL_SECUNDARIO);
        btnVoltar.setForeground(BRANCO);

        Font fonteBotao = new Font("Arial", Font.BOLD, 16);

        btnEntrar.setFont(fonteBotao);
        btnVoltar.setFont(fonteBotao);

        JPanel painelBotoes = new JPanel();
        painelBotoes.setBackground(AZUL_ESCURO);

        painelBotoes.add(btnVoltar);
        painelBotoes.add(btnEntrar);

        gbc.gridx = 0;
        gbc.gridy = 6;
        gbc.gridwidth = 2;
        gbc.insets = new Insets(20, 10, 10, 10);

        painel.add(painelBotoes, gbc);
        add(painel, BorderLayout.CENTER);

/////////////////////////////////////////////////////////////////////
//                      EFEITO DO BOTÃO ENTRAR
/////////////////////////////////////////////////////////////////////

        adicionarEfeitoMouse(btnEntrar, VERDE, VERDE_ESCURO);

/////////////////////////////////////////////////////////////////////
//                      AÇÃO DOS BOTÕES
/////////////////////////////////////////////////////////////////////

        btnVoltar.addActionListener(e -> dispose());
        btnEntrar.addActionListener(e -> tentarLogin());
    }

/////////////////////////////////////////////////////////////////////
//                  VALIDA OS DADOS DO LOGIN
/////////////////////////////////////////////////////////////////////

    private void tentarLogin() {
        String usuario = campoUsuario.getText().trim();
        String senha = new String(campoSenha.getPassword()).trim();
        String codigoTexto = campoCodigo.getText().trim();

        // Limpa mensagens antigas
        erroUsuario.setText(" ");
        erroSenha.setText(" ");
        erroCodigo.setText(" ");

        boolean temErro = false;

/////////////////////////////////////////////////////////////////////
//                      VALIDA USUÁRIO
/////////////////////////////////////////////////////////////////////

        if (usuario.isEmpty()) {
            erroUsuario.setText("Digite o usuário.");
            temErro = true;
        }

/////////////////////////////////////////////////////////////////////
//                      VALIDA SENHA
/////////////////////////////////////////////////////////////////////

        if (senha.isEmpty()) {
            erroSenha.setText("Digite a senha.");
            temErro = true;
        }

/////////////////////////////////////////////////////////////////////
//                      VALIDA CÓDIGO
/////////////////////////////////////////////////////////////////////

        if (codigoTexto.isEmpty()) {
            erroCodigo.setText("Digite o código.");
            temErro = true;

        } else {
            try {
                int codigo = Integer.parseInt(codigoTexto);

                if (codigo < 0) {
                    erroCodigo.setText("Código inválido.");
                    temErro = true;
                }

            } catch (NumberFormatException erro) {
                erroCodigo.setText("O código deve conter apenas números.");
                temErro = true;
            }
        }

/////////////////////////////////////////////////////////////////////
//                  BLOQUEIA O LOGIN SE HOUVER ERRO
/////////////////////////////////////////////////////////////////////

        if (temErro) {
            return;
        }

        /*
         * Aqui depois vamos verificar se
         * usuário, senha e código pertencem
         * a um funcionário cadastrado.
         */
    }

/////////////////////////////////////////////////////////////////////
//                  EFEITO AO PASSAR O MOUSE
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