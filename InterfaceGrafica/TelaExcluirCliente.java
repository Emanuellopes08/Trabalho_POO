package InterfaceGrafica;

import Pessoas.Cliente;

import javax.swing.*;
import java.awt.*;

public class TelaExcluirCliente extends JFrame {

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

    private Cliente clienteEncontrado;


    public TelaExcluirCliente() {
        configurarJanela();
        criarTitulo();
        criarFormulario();
    }

/////////////////////////////////////////////////////////////////////
// CONFIGURA A JANELA
/////////////////////////////////////////////////////////////////////

    private void configurarJanela() {
        setSize(650, 540);
        setTitle("Banco ES - Excluir Pessoas.Cliente");
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

        JLabel titulo = new JLabel("EXCLUIR CLIENTE");
        JLabel subtitulo = new JLabel("Busque o cliente pelo CPF antes de excluir");

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
// CPF
/////////////////////////////////////////////////////////////////////

        JLabel labelCpf = new JLabel("CPF:");

        labelCpf.setFont(fonteLabel);
        labelCpf.setForeground(BRANCO);

        campoCpf = new JTextField(22);

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 1;

        painel.add(labelCpf, gbc);

        gbc.gridx = 1;

        painel.add(campoCpf, gbc);

/////////////////////////////////////////////////////////////////////
// BOTÃO BUSCAR
/////////////////////////////////////////////////////////////////////

        BotaoArredondado btnBuscar = new BotaoArredondado("Buscar Pessoas.Cliente");

        btnBuscar.setPreferredSize(new Dimension(180, 45));
        btnBuscar.setFont(new Font("Arial", Font.BOLD, 15));
        btnBuscar.setBackground(VERDE);
        btnBuscar.setForeground(AZUL_ESCURO);

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 2;
        gbc.insets = new Insets(10, 10, 5, 10);

        painel.add(btnBuscar, gbc);

/////////////////////////////////////////////////////////////////////
// MENSAGEM DE ERRO
/////////////////////////////////////////////////////////////////////

        mensagemErro = new JLabel(" ");

        mensagemErro.setFont(new Font("Arial", Font.PLAIN, 14));
        mensagemErro.setForeground(VERMELHO_ERRO);
        mensagemErro.setHorizontalAlignment(SwingConstants.CENTER);

        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2;

        painel.add(mensagemErro, gbc);

/////////////////////////////////////////////////////////////////////
// RESULTADO DA BUSCA
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
        gbc.insets = new Insets(15, 10, 20, 10);

        painel.add(painelResultado, gbc);

/////////////////////////////////////////////////////////////////////
// BOTÕES
/////////////////////////////////////////////////////////////////////

        BotaoArredondado btnExcluir = new BotaoArredondado("Excluir Pessoas.Cliente");
        BotaoArredondado btnVoltar = new BotaoArredondado("Voltar");

        Dimension tamanhoBotao = new Dimension(180, 50);
        Font fonteBotao = new Font("Arial", Font.BOLD, 16);

        btnExcluir.setPreferredSize(tamanhoBotao);
        btnVoltar.setPreferredSize(tamanhoBotao);

        btnExcluir.setFont(fonteBotao);
        btnVoltar.setFont(fonteBotao);

        btnExcluir.setBackground(VERMELHO);
        btnExcluir.setForeground(BRANCO);

        btnVoltar.setBackground(AZUL_SECUNDARIO);
        btnVoltar.setForeground(BRANCO);

        JPanel painelBotoes = new JPanel();

        painelBotoes.setBackground(AZUL_ESCURO);
        painelBotoes.add(btnVoltar);
        painelBotoes.add(btnExcluir);

        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 2;
        gbc.insets = new Insets(10, 10, 10, 10);

        painel.add(painelBotoes, gbc);

        add(painel, BorderLayout.CENTER);

/////////////////////////////////////////////////////////////////////
// BOTÃO EXCLUIR COMEÇA DESATIVADO
/////////////////////////////////////////////////////////////////////

        btnExcluir.setEnabled(false);

/////////////////////////////////////////////////////////////////////
// AÇÃO BUSCAR
/////////////////////////////////////////////////////////////////////

        btnBuscar.addActionListener(e -> {
            String cpf = campoCpf.getText().trim();

            mensagemErro.setText(" ");
            resultadoNome.setText(" ");
            resultadoCpf.setText(" ");
            clienteEncontrado = null;
            btnExcluir.setEnabled(false);

/////////////////////////////////////////////////////////////////////
// VALIDA CPF
/////////////////////////////////////////////////////////////////////

            if (cpf.isEmpty()) {
                mensagemErro.setText("Digite o CPF do cliente.");
                return;
            }

/////////////////////////////////////////////////////////////////////
// BUSCA O CLIENTE
/////////////////////////////////////////////////////////////////////

            clienteEncontrado = SistemaBanco.buscarClientePorCpf(cpf);

            if (clienteEncontrado == null) {
                mensagemErro.setText("Pessoas.Cliente não encontrado.");
                return;
            }

/////////////////////////////////////////////////////////////////////
// MOSTRA OS DADOS
/////////////////////////////////////////////////////////////////////

            resultadoNome.setText("Nome: " + clienteEncontrado.getNome());
            resultadoCpf.setText("CPF: " + clienteEncontrado.getCpf());

            btnExcluir.setEnabled(true);
        });

/////////////////////////////////////////////////////////////////////
// AÇÃO EXCLUIR
/////////////////////////////////////////////////////////////////////

        btnExcluir.addActionListener(e -> {

            if (clienteEncontrado == null) {
                mensagemErro.setText("Busque um cliente primeiro.");
                return;
            }

/////////////////////////////////////////////////////////////////////
// CONFIRMAÇÃO
/////////////////////////////////////////////////////////////////////

            int confirmacao = JOptionPane.showConfirmDialog(
                    this,
                    "Tem certeza que deseja excluir este cliente?",
                    "Confirmar exclusão",
                    JOptionPane.YES_NO_OPTION,
                    JOptionPane.WARNING_MESSAGE
            );

            if (confirmacao != JOptionPane.YES_OPTION) {
                return;
            }

/////////////////////////////////////////////////////////////////////
// EXCLUI O CLIENTE
/////////////////////////////////////////////////////////////////////

            SistemaBanco.excluirCliente(clienteEncontrado);

/////////////////////////////////////////////////////////////////////
// MOSTRA SUCESSO
/////////////////////////////////////////////////////////////////////

            JOptionPane.showMessageDialog(
                    this,
                    "Pessoas.Cliente excluído com sucesso!",
                    "Banco ES",
                    JOptionPane.INFORMATION_MESSAGE
            );

/////////////////////////////////////////////////////////////////////
// LIMPA A TELA
/////////////////////////////////////////////////////////////////////

            campoCpf.setText("");
            resultadoNome.setText(" ");
            resultadoCpf.setText(" ");
            mensagemErro.setText(" ");

            clienteEncontrado = null;
            btnExcluir.setEnabled(false);
        });

/////////////////////////////////////////////////////////////////////
// AÇÃO VOLTAR
/////////////////////////////////////////////////////////////////////

        btnVoltar.addActionListener(e -> dispose());

/////////////////////////////////////////////////////////////////////
// EFEITOS DOS BOTÕES
/////////////////////////////////////////////////////////////////////

        adicionarEfeitoMouse(btnBuscar, VERDE, VERDE_ESCURO);
        adicionarEfeitoMouse(btnExcluir, VERMELHO, VERMELHO_ESCURO);
    }

/////////////////////////////////////////////////////////////////////
// EFEITO AO PASSAR O MOUSE
/////////////////////////////////////////////////////////////////////

    private void adicionarEfeitoMouse(BotaoArredondado botao, Color corNormal, Color corEscura) {

        botao.addMouseListener(new java.awt.event.MouseAdapter() {

            @Override
            public void mouseEntered(java.awt.event.MouseEvent evt) {

                if (!botao.isEnabled()) {
                    return;
                }

                botao.setBackground(corEscura);

                if (corNormal.equals(VERDE)) {
                    botao.setForeground(BRANCO);
                }
            }

            @Override
            public void mouseExited(java.awt.event.MouseEvent evt) {

                if (!botao.isEnabled()) {
                    return;
                }

                botao.setBackground(corNormal);

                if (corNormal.equals(VERDE)) {
                    botao.setForeground(AZUL_ESCURO);
                }
            }
        });
    }
}