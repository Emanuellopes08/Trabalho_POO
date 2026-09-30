import javax.swing.*;
import java.awt.*;

public class TelaCadastroCliente extends JFrame {

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


    // ============================================================
    // CONSTRUTOR
    // ============================================================

    public TelaCadastroCliente() {

        setTitle("Banco ES - Cadastro de Cliente");

        setSize(600, 500);

        setDefaultCloseOperation(
                JFrame.DISPOSE_ON_CLOSE
        );

        // Layout que você já estava utilizando
        setLayout(null);

        // Cor do fundo
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
                                "Conta PJ"
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


        // Nome

        JLabel labelNome =
                new JLabel("Nome:");

        labelNome.setBounds(
                50,
                25,
                100,
                30
        );

        configurarLabel(labelNome);


        JTextField campoNome =
                criarCampoTexto();

        campoNome.setBounds(
                170,
                25,
                300,
                35
        );


        // CPF

        JLabel labelCpf =
                new JLabel("CPF:");

        labelCpf.setBounds(
                50,
                75,
                100,
                30
        );

        configurarLabel(labelCpf);


        JTextField campoCpf =
                criarCampoTexto();

        campoCpf.setBounds(
                170,
                75,
                300,
                35
        );


        // Botão

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

            JOptionPane.showMessageDialog(
                    this,
                    "Conta criada com sucesso!",
                    "Banco ES",
                    JOptionPane.INFORMATION_MESSAGE
            );

        });


        painelCorrente.add(labelNome);
        painelCorrente.add(campoNome);

        painelCorrente.add(labelCpf);
        painelCorrente.add(campoCpf);

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


        // CNPJ

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


        // Empresa

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


        // Proprietário

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


        // Botão

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

            JOptionPane.showMessageDialog(
                    this,
                    "Conta criada com sucesso!",
                    "Banco ES",
                    JOptionPane.INFORMATION_MESSAGE
            );

        });


        painelPJ.add(labelCnpj);
        painelPJ.add(campoCnpj);

        painelPJ.add(labelEmpresa);
        painelPJ.add(campoEmpresa);

        painelPJ.add(labelProprietario);
        painelPJ.add(campoProprietario);

        painelPJ.add(botaoCadastrarPJ);


        // ========================================================
        // ADICIONA OS PAINÉIS
        // ========================================================

        add(painelCorrente);
        add(painelPJ);


        // ========================================================
        // ESTADO INICIAL
        // ========================================================

        painelCorrente.setVisible(true);

        painelPJ.setVisible(false);


        // ========================================================
        // EVENTO DO COMBOBOX
        // ========================================================

        tipoConta.addActionListener(e -> {

            String tipo =
                    (String) tipoConta.getSelectedItem();


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