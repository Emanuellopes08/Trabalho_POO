import javax.swing.*;
import java.awt.*;

public class TelaLoginCliente extends JFrame {

    // Paleta de cores
    private final Color AZUL_ESCURO = new Color(14, 14, 37);
    private final Color AZUL_SECUNDARIO = new Color(28, 30, 56);
    private final Color VERDE = new Color(72, 255, 129);
    private final Color VERDE_ESCURO = new Color(45, 190, 95);
    private final Color VERMELHO_ERRO = new Color(255, 90, 90);
    private final Color BRANCO = Color.WHITE;

    // Componentes
    private JComboBox<String> tipoConta;

    private JPanel painelCorrente;
    private JPanel painelPJ;

    private JTextField campoCpf;
    private JPasswordField campoSenhaCorrente;

    private JTextField campoCnpj;
    private JPasswordField campoSenhaPJ;

    private JLabel erroCpf;
    private JLabel erroSenhaCorrente;

    private JLabel erroCnpj;
    private JLabel erroSenhaPJ;


    public TelaLoginCliente() {
        configurarJanela();
        criarTitulo();
        criarSelecaoConta();
        criarPainelCorrente();
        criarPainelPJ();
        configurarTrocaDeConta();

        setVisible(true);
    }

/////////////////////////////////////////////////////////////////////
// CONFIGURA A JANELA
/////////////////////////////////////////////////////////////////////

    private void configurarJanela() {
        setTitle("Banco ES - Login do Cliente");
        setSize(600, 500);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(null);
        getContentPane().setBackground(AZUL_ESCURO);
        setLocationRelativeTo(null);
    }

/////////////////////////////////////////////////////////////////////
// CRIA O TÍTULO
/////////////////////////////////////////////////////////////////////

    private void criarTitulo() {
        JLabel titulo = new JLabel("LOGIN DO CLIENTE");
        titulo.setBounds(165, 15, 350, 40);
        titulo.setFont(new Font("Arial", Font.BOLD, 28));
        titulo.setForeground(BRANCO);
        add(titulo);

        JLabel subtitulo = new JLabel("Acesse sua conta");
        subtitulo.setBounds(220, 55, 250, 30);
        subtitulo.setFont(new Font("Arial", Font.PLAIN, 16));
        subtitulo.setForeground(VERDE);
        add(subtitulo);
    }

/////////////////////////////////////////////////////////////////////
// SELEÇÃO DO TIPO DE CONTA
/////////////////////////////////////////////////////////////////////

    private void criarSelecaoConta() {
        JLabel labelConta = new JLabel("Tipo de conta:");
        labelConta.setBounds(50, 100, 120, 30);
        configurarLabel(labelConta);
        add(labelConta);

        tipoConta = new JComboBox<>(new String[]{
                "Conta Corrente",
                "Conta PJ"
        });

        tipoConta.setBounds(170, 100, 250, 35);
        tipoConta.setFont(new Font("Arial", Font.PLAIN, 16));

        add(tipoConta);
    }

/////////////////////////////////////////////////////////////////////
// PAINEL CONTA CORRENTE
/////////////////////////////////////////////////////////////////////

    private void criarPainelCorrente() {
        painelCorrente = new JPanel();

        painelCorrente.setLayout(null);
        painelCorrente.setBackground(AZUL_SECUNDARIO);
        painelCorrente.setBounds(0, 150, 600, 250);

/////////////////////////////////////////////////////////////////////
// CPF
/////////////////////////////////////////////////////////////////////

        JLabel labelCpf = new JLabel("CPF:");
        labelCpf.setBounds(50, 25, 100, 30);
        configurarLabel(labelCpf);

        campoCpf = criarCampoTexto();
        campoCpf.setBounds(170, 25, 300, 35);

        erroCpf = new JLabel(" ");
        erroCpf.setBounds(170, 58, 300, 20);
        erroCpf.setForeground(VERMELHO_ERRO);

/////////////////////////////////////////////////////////////////////
// SENHA
/////////////////////////////////////////////////////////////////////

        JLabel labelSenha = new JLabel("Senha:");
        labelSenha.setBounds(50, 85, 100, 30);
        configurarLabel(labelSenha);

        campoSenhaCorrente = criarCampoSenha();
        campoSenhaCorrente.setBounds(170, 85, 300, 35);

        erroSenhaCorrente = new JLabel(" ");
        erroSenhaCorrente.setBounds(170, 118, 300, 20);
        erroSenhaCorrente.setForeground(VERMELHO_ERRO);

/////////////////////////////////////////////////////////////////////
// BOTÃO ENTRAR
/////////////////////////////////////////////////////////////////////

        BotaoArredondado botaoEntrar = new BotaoArredondado("Entrar");

        configurarBotao(botaoEntrar);

        botaoEntrar.setBounds(170, 155, 180, 50);

        adicionarEfeitoMouseVerde(botaoEntrar);

        botaoEntrar.addActionListener(e -> loginContaCorrente());

/////////////////////////////////////////////////////////////////////
// ADICIONA COMPONENTES
/////////////////////////////////////////////////////////////////////

        painelCorrente.add(labelCpf);
        painelCorrente.add(campoCpf);
        painelCorrente.add(erroCpf);

        painelCorrente.add(labelSenha);
        painelCorrente.add(campoSenhaCorrente);
        painelCorrente.add(erroSenhaCorrente);

        painelCorrente.add(botaoEntrar);

        add(painelCorrente);
    }

/////////////////////////////////////////////////////////////////////
// PAINEL CONTA PJ
/////////////////////////////////////////////////////////////////////

    private void criarPainelPJ() {
        painelPJ = new JPanel();

        painelPJ.setLayout(null);
        painelPJ.setBackground(AZUL_SECUNDARIO);
        painelPJ.setBounds(0, 150, 600, 250);

/////////////////////////////////////////////////////////////////////
// CNPJ
/////////////////////////////////////////////////////////////////////

        JLabel labelCnpj = new JLabel("CNPJ:");
        labelCnpj.setBounds(50, 25, 100, 30);
        configurarLabel(labelCnpj);

        campoCnpj = criarCampoTexto();
        campoCnpj.setBounds(170, 25, 300, 35);

        erroCnpj = new JLabel(" ");
        erroCnpj.setBounds(170, 58, 300, 20);
        erroCnpj.setForeground(VERMELHO_ERRO);

/////////////////////////////////////////////////////////////////////
// SENHA
/////////////////////////////////////////////////////////////////////

        JLabel labelSenha = new JLabel("Senha:");
        labelSenha.setBounds(50, 85, 100, 30);
        configurarLabel(labelSenha);

        campoSenhaPJ = criarCampoSenha();
        campoSenhaPJ.setBounds(170, 85, 300, 35);

        erroSenhaPJ = new JLabel(" ");
        erroSenhaPJ.setBounds(170, 118, 300, 20);
        erroSenhaPJ.setForeground(VERMELHO_ERRO);

/////////////////////////////////////////////////////////////////////
// BOTÃO ENTRAR
/////////////////////////////////////////////////////////////////////

        BotaoArredondado botaoEntrarPJ = new BotaoArredondado("Entrar");

        configurarBotao(botaoEntrarPJ);

        botaoEntrarPJ.setBounds(170, 155, 180, 50);

        adicionarEfeitoMouseVerde(botaoEntrarPJ);

        botaoEntrarPJ.addActionListener(e -> loginContaPJ());

/////////////////////////////////////////////////////////////////////
// ADICIONA COMPONENTES
/////////////////////////////////////////////////////////////////////

        painelPJ.add(labelCnpj);
        painelPJ.add(campoCnpj);
        painelPJ.add(erroCnpj);

        painelPJ.add(labelSenha);
        painelPJ.add(campoSenhaPJ);
        painelPJ.add(erroSenhaPJ);

        painelPJ.add(botaoEntrarPJ);

        add(painelPJ);
    }

/////////////////////////////////////////////////////////////////////
// TROCA ENTRE CONTA CORRENTE E CONTA PJ
/////////////////////////////////////////////////////////////////////

    private void configurarTrocaDeConta() {
        painelCorrente.setVisible(true);
        painelPJ.setVisible(false);

        tipoConta.addActionListener(e -> {
            String tipo = (String) tipoConta.getSelectedItem();

            if (tipo.equals("Conta Corrente")) {
                painelCorrente.setVisible(true);
                painelPJ.setVisible(false);
            }

            else if (tipo.equals("Conta PJ")) {
                painelCorrente.setVisible(false);
                painelPJ.setVisible(true);
            }

            revalidate();
            repaint();
        });
    }

/////////////////////////////////////////////////////////////////////
// LOGIN DA CONTA CORRENTE
/////////////////////////////////////////////////////////////////////

    private void loginContaCorrente() {
        String cpf = campoCpf.getText().trim();
        String senha = new String(campoSenhaCorrente.getPassword()).trim();

        erroCpf.setText(" ");
        erroSenhaCorrente.setText(" ");

        boolean temErro = false;

        if (cpf.isEmpty()) {
            erroCpf.setText("Digite o CPF.");
            temErro = true;
        }

        if (senha.isEmpty()) {
            erroSenhaCorrente.setText("Digite a senha.");
            temErro = true;
        }

        if (temErro) {
            return;
        }

        /*
         * Depois vamos verificar aqui se
         * CPF e senha realmente pertencem
         * a uma Conta Corrente cadastrada.
         *
         * Se estiver correto:
         *
         * TelaCliente telaCliente = new TelaCliente();
         * telaCliente.setVisible(true);
         * dispose();
         */
    }

/////////////////////////////////////////////////////////////////////
// LOGIN DA CONTA PJ
/////////////////////////////////////////////////////////////////////

    private void loginContaPJ() {
        String cnpj = campoCnpj.getText().trim();
        String senha = new String(campoSenhaPJ.getPassword()).trim();

        erroCnpj.setText(" ");
        erroSenhaPJ.setText(" ");

        boolean temErro = false;

        if (cnpj.isEmpty()) {
            erroCnpj.setText("Digite o CNPJ.");
            temErro = true;
        }

        if (senha.isEmpty()) {
            erroSenhaPJ.setText("Digite a senha.");
            temErro = true;
        }

        if (temErro) {
            return;
        }

        /*
         * Depois vamos verificar aqui se
         * CNPJ e senha realmente pertencem
         * a uma Conta PJ cadastrada.
         *
         * Se estiver correto:
         *
         * TelaCliente telaCliente = new TelaCliente();
         * telaCliente.setVisible(true);
         * dispose();
         */
    }

/////////////////////////////////////////////////////////////////////
// CONFIGURA LABEL
/////////////////////////////////////////////////////////////////////

    private void configurarLabel(JLabel label) {
        label.setFont(new Font("Arial", Font.BOLD, 16));
        label.setForeground(BRANCO);
    }

/////////////////////////////////////////////////////////////////////
// CRIA CAMPO DE TEXTO
/////////////////////////////////////////////////////////////////////

    private JTextField criarCampoTexto() {
        JTextField campo = new JTextField();

        campo.setFont(new Font("Arial", Font.PLAIN, 16));
        campo.setBackground(BRANCO);
        campo.setForeground(AZUL_ESCURO);

        return campo;
    }

/////////////////////////////////////////////////////////////////////
// CRIA CAMPO DE SENHA
/////////////////////////////////////////////////////////////////////

    private JPasswordField criarCampoSenha() {
        JPasswordField campo = new JPasswordField();

        campo.setFont(new Font("Arial", Font.PLAIN, 16));
        campo.setBackground(BRANCO);
        campo.setForeground(AZUL_ESCURO);

        return campo;
    }

/////////////////////////////////////////////////////////////////////
// CONFIGURA BOTÃO
/////////////////////////////////////////////////////////////////////

    private void configurarBotao(BotaoArredondado botao) {
        botao.setBackground(VERDE);
        botao.setForeground(AZUL_ESCURO);
        botao.setFont(new Font("Arial", Font.BOLD, 18));
        botao.setFocusPainted(false);
    }

/////////////////////////////////////////////////////////////////////
// EFEITO DO MOUSE
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
}