import javax.swing.*;
import java.awt.*;

public class TelaConsultaCliente extends JFrame {

    // Paleta de cores
    private final Color AZUL_ESCURO = new Color(14, 14, 37);
    private final Color AZUL_SECUNDARIO = new Color(28, 30, 56);
    private final Color VERDE = new Color(72, 255, 129);
    private final Color VERDE_ESCURO = new Color(45, 190, 95);
    private final Color VERMELHO_ERRO = new Color(255, 90, 90);
    private final Color BRANCO = Color.WHITE;

    // Componentes
    private JComboBox<String> tipoConta;
    private JLabel labelDocumento;
    private JTextField campoDocumento;
    private JLabel erroDocumento;

    private JLabel resultadoNome;
    private JLabel resultadoDocumento;


    public TelaConsultaCliente() {
        configurarJanela();
        criarTitulo();
        criarFormulario();
    }

/////////////////////////////////////////////////////////////////////
// CONFIGURA A JANELA
/////////////////////////////////////////////////////////////////////

    private void configurarJanela() {
        setSize(600, 520);
        setTitle("Banco ES - Consultar Cliente");
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

        JLabel titulo = new JLabel("CONSULTAR CLIENTE");
        JLabel subtitulo = new JLabel("Escolha o tipo de conta");

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

        gbc.insets = new Insets(5, 10, 5, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

/////////////////////////////////////////////////////////////////////
// TIPO DE CONTA
/////////////////////////////////////////////////////////////////////

        JLabel labelTipoConta = new JLabel("Tipo de conta:");
        labelTipoConta.setFont(new Font("Arial", Font.BOLD, 16));
        labelTipoConta.setForeground(BRANCO);

        tipoConta = new JComboBox<>(new String[]{
                "Conta Corrente",
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
        labelDocumento.setFont(new Font("Arial", Font.BOLD, 16));
        labelDocumento.setForeground(BRANCO);

        campoDocumento = new JTextField(20);

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
// BOTÕES
/////////////////////////////////////////////////////////////////////

        BotaoArredondado btnConsultar = new BotaoArredondado("Consultar");
        BotaoArredondado btnVoltar = new BotaoArredondado("Voltar");

        Dimension tamanhoBotao = new Dimension(150, 50);
        Font fonteBotao = new Font("Arial", Font.BOLD, 16);

        btnConsultar.setPreferredSize(tamanhoBotao);
        btnVoltar.setPreferredSize(tamanhoBotao);

        btnConsultar.setFont(fonteBotao);
        btnVoltar.setFont(fonteBotao);

        btnConsultar.setBackground(VERDE);
        btnConsultar.setForeground(AZUL_ESCURO);

        btnVoltar.setBackground(AZUL_SECUNDARIO);
        btnVoltar.setForeground(BRANCO);

        JPanel painelBotoes = new JPanel();

        painelBotoes.setBackground(AZUL_ESCURO);
        painelBotoes.add(btnVoltar);
        painelBotoes.add(btnConsultar);

        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        gbc.insets = new Insets(20, 10, 10, 10);

        painel.add(painelBotoes, gbc);

/////////////////////////////////////////////////////////////////////
// RESULTADO DA CONSULTA
/////////////////////////////////////////////////////////////////////

        JPanel painelResultado = new JPanel();

        painelResultado.setLayout(new BoxLayout(painelResultado, BoxLayout.Y_AXIS));
        painelResultado.setBackground(AZUL_SECUNDARIO);
        painelResultado.setBorder(BorderFactory.createEmptyBorder(15, 25, 15, 25));

        resultadoNome = new JLabel(" ");
        resultadoDocumento = new JLabel(" ");

        resultadoNome.setFont(new Font("Arial", Font.BOLD, 17));
        resultadoDocumento.setFont(new Font("Arial", Font.PLAIN, 16));

        resultadoNome.setForeground(BRANCO);
        resultadoDocumento.setForeground(BRANCO);

        resultadoNome.setAlignmentX(Component.CENTER_ALIGNMENT);
        resultadoDocumento.setAlignmentX(Component.CENTER_ALIGNMENT);

        painelResultado.add(resultadoNome);
        painelResultado.add(Box.createVerticalStrut(8));
        painelResultado.add(resultadoDocumento);

        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 2;
        gbc.insets = new Insets(15, 10, 10, 10);

        painel.add(painelResultado, gbc);

        add(painel, BorderLayout.CENTER);

/////////////////////////////////////////////////////////////////////
// ALTERA CPF OU CNPJ CONFORME O TIPO DA CONTA
/////////////////////////////////////////////////////////////////////

        tipoConta.addActionListener(e -> alterarTipoDocumento());

/////////////////////////////////////////////////////////////////////
// AÇÕES DOS BOTÕES
/////////////////////////////////////////////////////////////////////

        btnConsultar.addActionListener(e -> consultarCliente());
        btnVoltar.addActionListener(e -> dispose());

/////////////////////////////////////////////////////////////////////
// EFEITO DO BOTÃO
/////////////////////////////////////////////////////////////////////

        adicionarEfeitoMouse(btnConsultar, VERDE, VERDE_ESCURO);
    }

/////////////////////////////////////////////////////////////////////
// ALTERA O CAMPO ENTRE CPF E CNPJ
/////////////////////////////////////////////////////////////////////

    private void alterarTipoDocumento() {
        String contaSelecionada = (String) tipoConta.getSelectedItem();

        campoDocumento.setText("");
        erroDocumento.setText(" ");
        resultadoNome.setText(" ");
        resultadoDocumento.setText(" ");

        if ("Conta PJ".equals(contaSelecionada)) {
            labelDocumento.setText("CNPJ:");
        } else {
            labelDocumento.setText("CPF:");
        }
    }

/////////////////////////////////////////////////////////////////////
// CONSULTA O CLIENTE
/////////////////////////////////////////////////////////////////////

    private void consultarCliente() {
        String contaSelecionada = (String) tipoConta.getSelectedItem();
        String documento = campoDocumento.getText().trim();

        erroDocumento.setText(" ");
        resultadoNome.setText(" ");
        resultadoDocumento.setText(" ");

/////////////////////////////////////////////////////////////////////
// VALIDA SE O CAMPO ESTÁ VAZIO
/////////////////////////////////////////////////////////////////////

        if (documento.isEmpty()) {

            if ("Conta PJ".equals(contaSelecionada)) {
                erroDocumento.setText("Digite o CNPJ.");
            } else {
                erroDocumento.setText("Digite o CPF.");
            }

            return;
        }

/////////////////////////////////////////////////////////////////////
// CONSULTA CONTA CORRENTE PELO CPF
/////////////////////////////////////////////////////////////////////

        if ("Conta Corrente".equals(contaSelecionada)) {

            Cliente cliente = SistemaBanco.buscarClientePorCpf(documento);

            if (cliente == null) {
                erroDocumento.setText("Cliente não encontrado.");
                return;
            }

            resultadoNome.setText("Nome: " + cliente.getNome());
            resultadoDocumento.setText("CPF: " + cliente.getCpf());
        }

/////////////////////////////////////////////////////////////////////
// CONSULTA CONTA PJ PELO CNPJ
/////////////////////////////////////////////////////////////////////

        else if ("Conta PJ".equals(contaSelecionada)) {

            ContaPJ contaPJ = SistemaBanco.buscarContaPjPorCnpj(documento);

            if (contaPJ == null) {
                erroDocumento.setText("Conta PJ não encontrada.");
                return;
            }

            resultadoNome.setText("Titular: " + contaPJ.getTitular().getNome());
            resultadoDocumento.setText("CNPJ: " + documento);
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
                botao.setForeground(BRANCO);
            }

            @Override
            public void mouseExited(java.awt.event.MouseEvent evt) {
                botao.setBackground(corNormal);
                botao.setForeground(AZUL_ESCURO);
            }
        });
    }
}