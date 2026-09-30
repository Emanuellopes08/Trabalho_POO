import javax.swing.*;
import java.awt.*;

public class TelaLoginCliente extends JFrame {

    // ============================================================
    // PALETA DE CORES DO PROJETO
    // ============================================================

    private final Color AZUL_ESCURO = new Color(14, 14, 37);
    private final Color AZUL_SECUNDARIO = new Color(28, 30, 56);

    private final Color VERDE = new Color(72, 255, 129);
    private final Color VERDE_ESCURO = new Color(45, 190, 95);

    private final Color BRANCO = Color.WHITE;


    // ============================================================
    // COMPONENTES
    // ============================================================

    private JComboBox<String> tipoConta;

    private JPanel painelCorrente;
    private JPanel painelPJ;
    private JPanel painelPoupanca;
    private JPanel painelConjunta;


    // ============================================================
    // CONSTRUTOR
    // ============================================================

    public TelaLoginCliente() {

        setTitle("Banco ES - Cadastro de Cliente");

        setSize(600, 500);

        setDefaultCloseOperation(
                JFrame.DISPOSE_ON_CLOSE
        );

        setLayout(null);

        getContentPane().setBackground(
                AZUL_ESCURO
        );


        // ========================================================
        // TÍTULO
        // ========================================================

        JLabel titulo =
                new JLabel("CADASTRO DE CLIENTE");

        titulo.setBounds(
                150,
                15,
                350,
                40
        );

        titulo.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        28
                )
        );

        titulo.setForeground(BRANCO);

        add(titulo);


        // ========================================================
        // SUBTÍTULO
        // ========================================================

        JLabel subtitulo =
                new JLabel("Informe os dados da conta");

        subtitulo.setBounds(
                190,
                55,
                300,
                30
        );

        subtitulo.setFont(
                new Font(
                        "Arial",
                        Font.PLAIN,
                        16
                )
        );

        subtitulo.setForeground(VERDE);

        add(subtitulo);


        // ========================================================
        // TIPO DE CONTA
        // ========================================================

        JLabel labelConta =
                new JLabel("Tipo de conta:");

        labelConta.setBounds(
                50,
                100,
                120,
                30
        );

        configurarLabel(labelConta);

        add(labelConta);


        tipoConta =
                new JComboBox<>(
                        new String[]{
                                "Conta Corrente",
                                "Conta PJ",
                                "Conta Poupança",
                                "Conta Conjunta"
                        }
                );

        tipoConta.setBounds(
                170,
                100,
                250,
                35
        );

        tipoConta.setFont(
                new Font(
                        "Arial",
                        Font.PLAIN,
                        16
                )
        );

        add(tipoConta);


        // ========================================================
        // PAINEL CONTA CORRENTE
        // ========================================================

        painelCorrente =
                new JPanel();

        painelCorrente.setLayout(null);

        painelCorrente.setBackground(
                AZUL_SECUNDARIO
        );

        painelCorrente.setBounds(
                0,
                150,
                600,
                250
        );


        // --------------------------------------------------------
        // NOME
        // --------------------------------------------------------

        JLabel labelNomeCorrente =
                new JLabel("Nome:");

        labelNomeCorrente.setBounds(
                50,
                25,
                100,
                30
        );

        configurarLabel(labelNomeCorrente);


        JTextField campoNomeCorrente =
                criarCampoTexto();

        campoNomeCorrente.setBounds(
                170,
                25,
                300,
                35
        );


        // --------------------------------------------------------
        // CPF
        // --------------------------------------------------------

        JLabel labelCpfCorrente =
                new JLabel("CPF:");

        labelCpfCorrente.setBounds(
                50,
                75,
                100,
                30
        );

        configurarLabel(labelCpfCorrente);


        JTextField campoCpfCorrente =
                criarCampoTexto();

        campoCpfCorrente.setBounds(
                170,
                75,
                300,
                35
        );


        // --------------------------------------------------------
        // BOTÃO
        // --------------------------------------------------------

        BotaoArredondado botaoCadastrar =
                new BotaoArredondado(
                        "Cadastrar"
                );

        configurarBotao(botaoCadastrar);

        botaoCadastrar.setBounds(
                170,
                130,
                180,
                50
        );

        adicionarEfeitoMouseVerde(
                botaoCadastrar
        );


        botaoCadastrar.addActionListener(e -> {

            if (campoNomeCorrente.getText().trim().isEmpty()) {

                mostrarErro(
                        "Digite o nome do cliente."
                );

                return;
            }

            if (campoCpfCorrente.getText().trim().isEmpty()) {

                mostrarErro(
                        "Digite o CPF do cliente."
                );

                return;
            }

            mostrarSucesso();

        });


        painelCorrente.add(labelNomeCorrente);
        painelCorrente.add(campoNomeCorrente);

        painelCorrente.add(labelCpfCorrente);
        painelCorrente.add(campoCpfCorrente);

        painelCorrente.add(botaoCadastrar);


        // ========================================================
        // PAINEL CONTA PJ
        // ========================================================

        painelPJ =
                new JPanel();

        painelPJ.setLayout(null);

        painelPJ.setBackground(
                AZUL_SECUNDARIO
        );

        painelPJ.setBounds(
                0,
                150,
                600,
                250
        );


        // --------------------------------------------------------
        // CNPJ
        // --------------------------------------------------------

        JLabel labelCnpj =
                new JLabel("CNPJ:");

        labelCnpj.setBounds(
                50,
                20,
                100,
                30
        );

        configurarLabel(labelCnpj);


        JTextField campoCnpj =
                criarCampoTexto();

        campoCnpj.setBounds(
                170,
                20,
                300,
                35
        );


        // --------------------------------------------------------
        // EMPRESA
        // --------------------------------------------------------

        JLabel labelEmpresa =
                new JLabel("Empresa:");

        labelEmpresa.setBounds(
                50,
                65,
                100,
                30
        );

        configurarLabel(labelEmpresa);


        JTextField campoEmpresa =
                criarCampoTexto();

        campoEmpresa.setBounds(
                170,
                65,
                300,
                35
        );


        // --------------------------------------------------------
        // PROPRIETÁRIO
        // --------------------------------------------------------

        JLabel labelProprietario =
                new JLabel("Proprietário:");

        labelProprietario.setBounds(
                50,
                110,
                110,
                30
        );

        configurarLabel(labelProprietario);


        JTextField campoProprietario =
                criarCampoTexto();

        campoProprietario.setBounds(
                170,
                110,
                300,
                35
        );


        // --------------------------------------------------------
        // BOTÃO
        // --------------------------------------------------------

        BotaoArredondado botaoCadastrarPJ =
                new BotaoArredondado(
                        "Cadastrar"
                );

        configurarBotao(botaoCadastrarPJ);

        botaoCadastrarPJ.setBounds(
                170,
                160,
                180,
                50
        );

        adicionarEfeitoMouseVerde(
                botaoCadastrarPJ
        );


        botaoCadastrarPJ.addActionListener(e -> {

            if (campoCnpj.getText().trim().isEmpty()) {

                mostrarErro(
                        "Digite o CNPJ da empresa."
                );

                return;
            }

            if (campoEmpresa.getText().trim().isEmpty()) {

                mostrarErro(
                        "Digite o nome da empresa."
                );

                return;
            }

            if (campoProprietario.getText().trim().isEmpty()) {

                mostrarErro(
                        "Digite o proprietário da conta."
                );

                return;
            }

            mostrarSucesso();

        });


        painelPJ.add(labelCnpj);
        painelPJ.add(campoCnpj);

        painelPJ.add(labelEmpresa);
        painelPJ.add(campoEmpresa);

        painelPJ.add(labelProprietario);
        painelPJ.add(campoProprietario);

        painelPJ.add(botaoCadastrarPJ);


        // ========================================================
        // PAINEL CONTA POUPANÇA
        // ========================================================

        painelPoupanca =
                new JPanel();

        painelPoupanca.setLayout(null);

        painelPoupanca.setBackground(
                AZUL_SECUNDARIO
        );

        painelPoupanca.setBounds(
                0,
                150,
                600,
                250
        );


        // --------------------------------------------------------
        // NOME
        // --------------------------------------------------------

        JLabel labelNomePoupanca =
                new JLabel("Nome:");

        labelNomePoupanca.setBounds(
                50,
                20,
                100,
                30
        );

        configurarLabel(labelNomePoupanca);


        JTextField campoNomePoupanca =
                criarCampoTexto();

        campoNomePoupanca.setBounds(
                170,
                20,
                300,
                35
        );


        // --------------------------------------------------------
        // CPF
        // --------------------------------------------------------

        JLabel labelCpfPoupanca =
                new JLabel("CPF:");

        labelCpfPoupanca.setBounds(
                50,
                65,
                100,
                30
        );

        configurarLabel(labelCpfPoupanca);


        JTextField campoCpfPoupanca =
                criarCampoTexto();

        campoCpfPoupanca.setBounds(
                170,
                65,
                300,
                35
        );


        // --------------------------------------------------------
        // CONSULTAR RENDIMENTO
        // --------------------------------------------------------

        BotaoArredondado botaoConsultarRendimento =
                new BotaoArredondado(
                        "Consultar rendimento"
                );

        configurarBotao(botaoConsultarRendimento);

        botaoConsultarRendimento.setBounds(
                70,
                120,
                200,
                45
        );

        adicionarEfeitoMouseVerde(
                botaoConsultarRendimento
        );


        botaoConsultarRendimento.addActionListener(e -> {

            JOptionPane.showMessageDialog(
                    this,
                    "Funcionalidade de consulta de rendimento.",
                    "Banco ES",
                    JOptionPane.INFORMATION_MESSAGE
            );

        });


        // --------------------------------------------------------
        // APLICAR RENDIMENTO
        // --------------------------------------------------------

        BotaoArredondado botaoAplicarRendimento =
                new BotaoArredondado(
                        "Aplicar rendimento"
                );

        configurarBotao(botaoAplicarRendimento);

        botaoAplicarRendimento.setBounds(
                290,
                120,
                200,
                45
        );

        adicionarEfeitoMouseVerde(
                botaoAplicarRendimento
        );


        botaoAplicarRendimento.addActionListener(e -> {

            JOptionPane.showMessageDialog(
                    this,
                    "Funcionalidade de aplicação de rendimento.",
                    "Banco ES",
                    JOptionPane.INFORMATION_MESSAGE
            );

        });


        // --------------------------------------------------------
        // CADASTRAR
        // --------------------------------------------------------

        BotaoArredondado botaoCadastrarPoupanca =
                new BotaoArredondado(
                        "Cadastrar"
                );

        configurarBotao(botaoCadastrarPoupanca);

        botaoCadastrarPoupanca.setBounds(
                200,
                175,
                180,
                45
        );

        adicionarEfeitoMouseVerde(
                botaoCadastrarPoupanca
        );


        botaoCadastrarPoupanca.addActionListener(e -> {

            if (campoNomePoupanca.getText().trim().isEmpty()) {

                mostrarErro(
                        "Digite o nome do cliente."
                );

                return;
            }

            if (campoCpfPoupanca.getText().trim().isEmpty()) {

                mostrarErro(
                        "Digite o CPF do cliente."
                );

                return;
            }

            mostrarSucesso();

        });


        painelPoupanca.add(labelNomePoupanca);
        painelPoupanca.add(campoNomePoupanca);

        painelPoupanca.add(labelCpfPoupanca);
        painelPoupanca.add(campoCpfPoupanca);

        painelPoupanca.add(botaoConsultarRendimento);
        painelPoupanca.add(botaoAplicarRendimento);
        painelPoupanca.add(botaoCadastrarPoupanca);


        // ========================================================
        // PAINEL CONTA CONJUNTA
        // ========================================================

        painelConjunta =
                new JPanel();

        painelConjunta.setLayout(null);

        painelConjunta.setBackground(
                AZUL_SECUNDARIO
        );

        painelConjunta.setBounds(
                0,
                150,
                600,
                250
        );


        // --------------------------------------------------------
        // NOME
        // --------------------------------------------------------

        JLabel labelNomeConjunta =
                new JLabel("Nome:");

        labelNomeConjunta.setBounds(
                50,
                20,
                100,
                30
        );

        configurarLabel(labelNomeConjunta);


        JTextField campoNomeConjunta =
                criarCampoTexto();

        campoNomeConjunta.setBounds(
                170,
                20,
                300,
                35
        );


        // --------------------------------------------------------
        // CPF
        // --------------------------------------------------------

        JLabel labelCpfConjunta =
                new JLabel("CPF:");

        labelCpfConjunta.setBounds(
                50,
                65,
                100,
                30
        );

        configurarLabel(labelCpfConjunta);


        JTextField campoCpfConjunta =
                criarCampoTexto();

        campoCpfConjunta.setBounds(
                170,
                65,
                300,
                35
        );


        // --------------------------------------------------------
        // CPF DO COTITULAR
        // --------------------------------------------------------

        JLabel labelCpfCotitular =
                new JLabel("CPF cotitular:");

        labelCpfCotitular.setBounds(
                50,
                110,
                120,
                30
        );

        configurarLabel(labelCpfCotitular);


        JTextField campoCpfCotitular =
                criarCampoTexto();

        campoCpfCotitular.setBounds(
                170,
                110,
                300,
                35
        );


        // --------------------------------------------------------
        // BOTÃO
        // --------------------------------------------------------

        BotaoArredondado botaoCadastrarConjunta =
                new BotaoArredondado(
                        "Cadastrar"
                );

        configurarBotao(botaoCadastrarConjunta);

        botaoCadastrarConjunta.setBounds(
                170,
                160,
                180,
                50
        );

        adicionarEfeitoMouseVerde(
                botaoCadastrarConjunta
        );


        botaoCadastrarConjunta.addActionListener(e -> {

            if (campoNomeConjunta.getText().trim().isEmpty()) {

                mostrarErro(
                        "Digite o nome do primeiro titular."
                );

                return;
            }

            if (campoCpfConjunta.getText().trim().isEmpty()) {

                mostrarErro(
                        "Digite o CPF do primeiro titular."
                );

                return;
            }

            if (campoCpfCotitular.getText().trim().isEmpty()) {

                mostrarErro(
                        "Digite o CPF do cotitular."
                );

                return;
            }

            mostrarSucesso();

        });


        painelConjunta.add(labelNomeConjunta);
        painelConjunta.add(campoNomeConjunta);

        painelConjunta.add(labelCpfConjunta);
        painelConjunta.add(campoCpfConjunta);

        painelConjunta.add(labelCpfCotitular);
        painelConjunta.add(campoCpfCotitular);

        painelConjunta.add(botaoCadastrarConjunta);


        // ========================================================
        // ADICIONA OS PAINÉIS
        // ========================================================

        add(painelCorrente);
        add(painelPJ);
        add(painelPoupanca);
        add(painelConjunta);


        // ========================================================
        // ESTADO INICIAL
        // ========================================================

        painelCorrente.setVisible(true);
        painelPJ.setVisible(false);
        painelPoupanca.setVisible(false);
        painelConjunta.setVisible(false);


        // ========================================================
        // EVENTO DO COMBOBOX
        // ========================================================

        tipoConta.addActionListener(e -> {

            String tipo =
                    (String) tipoConta.getSelectedItem();


            painelCorrente.setVisible(false);
            painelPJ.setVisible(false);
            painelPoupanca.setVisible(false);
            painelConjunta.setVisible(false);


            if (tipo.equals("Conta Corrente")) {

                painelCorrente.setVisible(true);

            }

            else if (tipo.equals("Conta PJ")) {

                painelPJ.setVisible(true);

            }

            else if (tipo.equals("Conta Poupança")) {

                painelPoupanca.setVisible(true);

            }

            else if (tipo.equals("Conta Conjunta")) {

                painelConjunta.setVisible(true);

            }


            revalidate();

            repaint();
        });


        // ========================================================
        // FINALIZA
        // ========================================================

        setLocationRelativeTo(null);

        setVisible(true);
    }


    // ============================================================
    // CONFIGURA LABEL
    // ============================================================

    private void configurarLabel(
            JLabel label
    ) {

        label.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        16
                )
        );

        label.setForeground(BRANCO);
    }


    // ============================================================
    // CRIA CAMPO DE TEXTO
    // ============================================================

    private JTextField criarCampoTexto() {

        JTextField campo =
                new JTextField();

        campo.setFont(
                new Font(
                        "Arial",
                        Font.PLAIN,
                        16
                )
        );

        campo.setBackground(BRANCO);

        campo.setForeground(AZUL_ESCURO);

        return campo;
    }


    // ============================================================
    // CONFIGURA BOTÃO
    // ============================================================

    private void configurarBotao(
            BotaoArredondado botao
    ) {

        botao.setBackground(VERDE);

        botao.setForeground(AZUL_ESCURO);

        botao.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        18
                )
        );

        botao.setFocusPainted(false);
    }


    // ============================================================
    // MENSAGEM DE ERRO
    // ============================================================

    private void mostrarErro(
            String mensagem
    ) {

        JOptionPane.showMessageDialog(
                this,
                mensagem,
                "Erro no cadastro",
                JOptionPane.ERROR_MESSAGE
        );
    }


    // ============================================================
    // MENSAGEM DE SUCESSO
    // ============================================================

    private void mostrarSucesso() {

        JOptionPane.showMessageDialog(
                this,
                "Conta criada com sucesso!",
                "Banco ES",
                JOptionPane.INFORMATION_MESSAGE
        );
    }


    // ============================================================
    // EFEITO DO MOUSE
    // ============================================================

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

}


