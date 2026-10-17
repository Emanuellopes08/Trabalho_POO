package InterfaceGrafica;

import Pessoas.Cliente;

import javax.swing.*;
import java.awt.*;

public class TelaConsultaCliente extends JFrame {

    // Paleta de cores
    private final Color AZUL_ESCURO = new Color(14, 14, 37);
    private final Color AZUL_SECUNDARIO = new Color(28, 30, 56);
    private final Color VERDE = new Color(72, 255, 129);
    private final Color VERDE_ESCURO = new Color(45, 190, 95);
    private final Color VERMELHO = new Color(180, 70, 70);
    private final Color VERMELHO_ESCURO = new Color(140, 45, 45);
    private final Color VERMELHO_ERRO = new Color(255, 90, 90);
    private final Color BRANCO = Color.WHITE;

    // Componentes
    private JTextField campoCpf;
    private JLabel mensagemErro;

    private JLabel resultadoNome;
    private JLabel resultadoCpf;


    public TelaConsultaCliente() {
        configurarJanela();
        criarTitulo();
        criarFormulario();
    }

/////////////////////////////////////////////////////////////////////
// CONFIGURA A JANELA
/////////////////////////////////////////////////////////////////////

    private void configurarJanela() {
        setSize(600, 500);
        setTitle("Banco ES - Consultar Pessoas.Cliente");
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
        JLabel subtitulo = new JLabel("Informe o CPF do cliente");

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

        gbc.insets = new Insets(6, 10, 6, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        Font fonteLabel = new Font("Arial", Font.BOLD, 16);

/////////////////////////////////////////////////////////////////////
// CPF - STRING
/////////////////////////////////////////////////////////////////////

        JLabel labelCpf = new JLabel("CPF:");

        labelCpf.setFont(fonteLabel);
        labelCpf.setForeground(BRANCO);

        campoCpf = new JTextField(22);

        gbc.gridx = 0;
        gbc.gridy = 0;

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
// BOTÃO CONSULTAR
/////////////////////////////////////////////////////////////////////

        BotaoArredondado btnConsultar = new BotaoArredondado("Consultar");

        btnConsultar.setPreferredSize(new Dimension(170, 50));
        btnConsultar.setFont(new Font("Arial", Font.BOLD, 16));
        btnConsultar.setBackground(VERDE);
        btnConsultar.setForeground(AZUL_ESCURO);

        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        gbc.insets = new Insets(10, 10, 20, 10);

        painel.add(btnConsultar, gbc);

/////////////////////////////////////////////////////////////////////
// RESULTADO DA CONSULTA
/////////////////////////////////////////////////////////////////////

        JPanel painelResultado = new JPanel();

        painelResultado.setLayout(new BoxLayout(painelResultado, BoxLayout.Y_AXIS));
        painelResultado.setBackground(AZUL_SECUNDARIO);
        painelResultado.setBorder(BorderFactory.createEmptyBorder(20, 40, 20, 40));

        resultadoNome = new JLabel(" ");
        resultadoCpf = new JLabel(" ");

        resultadoNome.setFont(new Font("Arial", Font.BOLD, 18));
        resultadoCpf.setFont(new Font("Arial", Font.PLAIN, 16));

        resultadoNome.setForeground(BRANCO);
        resultadoCpf.setForeground(BRANCO);

        resultadoNome.setAlignmentX(Component.CENTER_ALIGNMENT);
        resultadoCpf.setAlignmentX(Component.CENTER_ALIGNMENT);

        painelResultado.add(resultadoNome);
        painelResultado.add(Box.createVerticalStrut(8));
        painelResultado.add(resultadoCpf);

        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        gbc.insets = new Insets(10, 10, 20, 10);

        painel.add(painelResultado, gbc);

/////////////////////////////////////////////////////////////////////
// BOTÃO VOLTAR
/////////////////////////////////////////////////////////////////////

        BotaoArredondado btnVoltar = new BotaoArredondado("Voltar");

        btnVoltar.setPreferredSize(new Dimension(170, 50));
        btnVoltar.setFont(new Font("Arial", Font.BOLD, 16));
        btnVoltar.setBackground(VERMELHO);
        btnVoltar.setForeground(BRANCO);

        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 2;
        gbc.insets = new Insets(5, 10, 10, 10);

        painel.add(btnVoltar, gbc);

        add(painel, BorderLayout.CENTER);

/////////////////////////////////////////////////////////////////////
// AÇÃO CONSULTAR
/////////////////////////////////////////////////////////////////////

        btnConsultar.addActionListener(e -> consultarCliente());

/////////////////////////////////////////////////////////////////////
// AÇÃO VOLTAR
/////////////////////////////////////////////////////////////////////

        btnVoltar.addActionListener(e -> dispose());

/////////////////////////////////////////////////////////////////////
// EFEITOS DOS BOTÕES
/////////////////////////////////////////////////////////////////////

        adicionarEfeitoMouse(btnConsultar, VERDE, VERDE_ESCURO);
        adicionarEfeitoMouse(btnVoltar, VERMELHO, VERMELHO_ESCURO);
    }

/////////////////////////////////////////////////////////////////////
// CONSULTA O CLIENTE
/////////////////////////////////////////////////////////////////////

    private void consultarCliente() {
        String cpf = campoCpf.getText().trim();

        mensagemErro.setText(" ");
        resultadoNome.setText(" ");
        resultadoCpf.setText(" ");

/////////////////////////////////////////////////////////////////////
// VALIDA CPF
/////////////////////////////////////////////////////////////////////

        if (cpf.isEmpty()) {
            mensagemErro.setText("Digite o CPF do cliente.");
            return;
        }

/////////////////////////////////////////////////////////////////////
// PROCURA O CLIENTE
/////////////////////////////////////////////////////////////////////

        Cliente cliente = SistemaBanco.buscarClientePorCpf(cpf);

        if (cliente == null) {
            mensagemErro.setText("Pessoas.Cliente não encontrado.");
            return;
        }

/////////////////////////////////////////////////////////////////////
// MOSTRA OS DADOS DO CLIENTE
/////////////////////////////////////////////////////////////////////

        resultadoNome.setText("Nome: " + cliente.getNome());
        resultadoCpf.setText("CPF: " + cliente.getCpf());
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