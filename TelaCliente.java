import javax.swing.*;
import java.awt.*;
import java.util.List;

public class TelaCliente extends JFrame {

    // Paleta de cores
    private final Color AZUL_ESCURO = new Color(14, 14, 37);
    private final Color AZUL_SECUNDARIO = new Color(28, 30, 56);
    private final Color VERDE = new Color(72, 255, 129);
    private final Color VERDE_ESCURO = new Color(45, 190, 95);
    private final Color VERMELHO = new Color(180, 70, 70);
    private final Color VERMELHO_ESCURO = new Color(140, 45, 45);
    private final Color VERMELHO_ERRO = new Color(255, 90, 90);
    private final Color BRANCO = Color.WHITE;

    // Dados recebidos do login
    private Cliente cliente;
    private List<Conta> contas;

    // Conta atualmente selecionada
    private Conta contaSelecionada;

    // Componentes
    private JLabel labelNomeCliente;
    private JComboBox<String> comboContas;
    private JLabel labelSaldo;
    private JTextField campoValor;
    private JLabel mensagemErro;


    public TelaCliente(Cliente cliente, List<Conta> contas) {

        this.cliente = cliente;
        this.contas = contas;

        configurarJanela();
        criarTitulo();
        criarConteudo();
        carregarDadosCliente();
    }

/////////////////////////////////////////////////////////////////////
// CONFIGURA A JANELA
/////////////////////////////////////////////////////////////////////

    private void configurarJanela() {
        setSize(700, 620);
        setTitle("Banco ES - Área do Cliente");
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

        JLabel titulo = new JLabel("ÁREA DO CLIENTE");
        JLabel subtitulo = new JLabel("Gerencie sua conta");

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
// CRIA O CONTEÚDO
/////////////////////////////////////////////////////////////////////

    private void criarConteudo() {
        JPanel painel = new JPanel();

        painel.setBackground(AZUL_ESCURO);
        painel.setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();

        gbc.insets = new Insets(8, 10, 8, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

/////////////////////////////////////////////////////////////////////
// NOME DO CLIENTE
/////////////////////////////////////////////////////////////////////

        labelNomeCliente = new JLabel("Cliente:");

        labelNomeCliente.setFont(new Font("Arial", Font.BOLD, 18));
        labelNomeCliente.setForeground(BRANCO);
        labelNomeCliente.setHorizontalAlignment(SwingConstants.CENTER);

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;

        painel.add(labelNomeCliente, gbc);

/////////////////////////////////////////////////////////////////////
// SELEÇÃO DA CONTA
/////////////////////////////////////////////////////////////////////

        JLabel labelConta = new JLabel("Selecione a conta:");

        labelConta.setFont(new Font("Arial", Font.BOLD, 16));
        labelConta.setForeground(BRANCO);

        comboContas = new JComboBox<>();

        comboContas.setFont(new Font("Arial", Font.PLAIN, 15));

        gbc.gridwidth = 1;

        gbc.gridx = 0;
        gbc.gridy = 1;

        painel.add(labelConta, gbc);

        gbc.gridx = 1;

        painel.add(comboContas, gbc);

/////////////////////////////////////////////////////////////////////
// SALDO DA CONTA
/////////////////////////////////////////////////////////////////////

        JPanel painelSaldo = new JPanel();

        painelSaldo.setBackground(AZUL_SECUNDARIO);
        painelSaldo.setLayout(new BoxLayout(painelSaldo, BoxLayout.Y_AXIS));
        painelSaldo.setBorder(BorderFactory.createEmptyBorder(20, 40, 20, 40));

        JLabel tituloSaldo = new JLabel("SALDO DA CONTA");

        labelSaldo = new JLabel("R$ 0,00");

        tituloSaldo.setFont(new Font("Arial", Font.BOLD, 16));
        tituloSaldo.setForeground(VERDE);

        labelSaldo.setFont(new Font("Arial", Font.BOLD, 28));
        labelSaldo.setForeground(BRANCO);

        tituloSaldo.setAlignmentX(Component.CENTER_ALIGNMENT);
        labelSaldo.setAlignmentX(Component.CENTER_ALIGNMENT);

        painelSaldo.add(tituloSaldo);
        painelSaldo.add(Box.createVerticalStrut(10));
        painelSaldo.add(labelSaldo);

        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        gbc.insets = new Insets(20, 10, 15, 10);

        painel.add(painelSaldo, gbc);

/////////////////////////////////////////////////////////////////////
// VALOR DA OPERAÇÃO
/////////////////////////////////////////////////////////////////////

        JLabel labelValor = new JLabel("Valor:");

        labelValor.setFont(new Font("Arial", Font.BOLD, 16));
        labelValor.setForeground(BRANCO);

        campoValor = new JTextField(20);

        campoValor.setToolTipText("Exemplo: 100.00");

        gbc.gridwidth = 1;
        gbc.insets = new Insets(8, 10, 8, 10);

        gbc.gridx = 0;
        gbc.gridy = 3;

        painel.add(labelValor, gbc);

        gbc.gridx = 1;

        painel.add(campoValor, gbc);

/////////////////////////////////////////////////////////////////////
// MENSAGEM DE ERRO
/////////////////////////////////////////////////////////////////////

        mensagemErro = new JLabel(" ");

        mensagemErro.setFont(new Font("Arial", Font.PLAIN, 14));
        mensagemErro.setForeground(VERMELHO_ERRO);
        mensagemErro.setHorizontalAlignment(SwingConstants.CENTER);

        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 2;

        painel.add(mensagemErro, gbc);

/////////////////////////////////////////////////////////////////////
// BOTÕES DE OPERAÇÃO
/////////////////////////////////////////////////////////////////////

        BotaoArredondado btnDepositar = new BotaoArredondado("Depositar");
        BotaoArredondado btnSacar = new BotaoArredondado("Sacar");

        Dimension tamanhoBotao = new Dimension(180, 50);
        Font fonteBotao = new Font("Arial", Font.BOLD, 16);

        btnDepositar.setPreferredSize(tamanhoBotao);
        btnSacar.setPreferredSize(tamanhoBotao);

        btnDepositar.setFont(fonteBotao);
        btnSacar.setFont(fonteBotao);

        btnDepositar.setBackground(VERDE);
        btnDepositar.setForeground(AZUL_ESCURO);

        btnSacar.setBackground(VERDE);
        btnSacar.setForeground(AZUL_ESCURO);

        JPanel painelOperacoes = new JPanel();

        painelOperacoes.setBackground(AZUL_ESCURO);

        painelOperacoes.add(btnDepositar);
        painelOperacoes.add(btnSacar);

        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.gridwidth = 2;
        gbc.insets = new Insets(15, 10, 15, 10);

        painel.add(painelOperacoes, gbc);

/////////////////////////////////////////////////////////////////////
// BOTÃO VOLTAR
/////////////////////////////////////////////////////////////////////

        BotaoArredondado btnVoltar = new BotaoArredondado("Voltar");

        btnVoltar.setPreferredSize(new Dimension(180, 50));
        btnVoltar.setFont(fonteBotao);
        btnVoltar.setBackground(VERMELHO);
        btnVoltar.setForeground(BRANCO);

        gbc.gridx = 0;
        gbc.gridy = 6;
        gbc.gridwidth = 2;

        painel.add(btnVoltar, gbc);

        add(painel, BorderLayout.CENTER);

/////////////////////////////////////////////////////////////////////
// ALTERA A CONTA SELECIONADA
/////////////////////////////////////////////////////////////////////

        comboContas.addActionListener(e -> selecionarConta());

/////////////////////////////////////////////////////////////////////
// AÇÃO DEPOSITAR
/////////////////////////////////////////////////////////////////////

        btnDepositar.addActionListener(e -> depositar());

/////////////////////////////////////////////////////////////////////
// AÇÃO SACAR
/////////////////////////////////////////////////////////////////////

        btnSacar.addActionListener(e -> sacar());

/////////////////////////////////////////////////////////////////////
// AÇÃO VOLTAR
/////////////////////////////////////////////////////////////////////

        btnVoltar.addActionListener(e -> dispose());

/////////////////////////////////////////////////////////////////////
// EFEITOS DOS BOTÕES
/////////////////////////////////////////////////////////////////////

        adicionarEfeitoMouse(btnDepositar, VERDE, VERDE_ESCURO);
        adicionarEfeitoMouse(btnSacar, VERDE, VERDE_ESCURO);
        adicionarEfeitoMouse(btnVoltar, VERMELHO, VERMELHO_ESCURO);
    }

/////////////////////////////////////////////////////////////////////
// CARREGA OS DADOS DO CLIENTE
/////////////////////////////////////////////////////////////////////

    private void carregarDadosCliente() {

        labelNomeCliente.setText(
                "Cliente: " + cliente.getNome()
        );

/////////////////////////////////////////////////////////////////////
// ADICIONA AS CONTAS NO COMBOBOX
/////////////////////////////////////////////////////////////////////

        comboContas.removeAllItems();

        for (Conta conta : contas) {
            comboContas.addItem(
                    conta.getTipoConta().toString()
            );
        }

/////////////////////////////////////////////////////////////////////
// SELECIONA A PRIMEIRA CONTA
/////////////////////////////////////////////////////////////////////

        if (!contas.isEmpty()) {
            comboContas.setSelectedIndex(0);

            contaSelecionada = contas.get(0);

            atualizarSaldo();
        }
    }

/////////////////////////////////////////////////////////////////////
// SELECIONA A CONTA
/////////////////////////////////////////////////////////////////////

    private void selecionarConta() {

        int indice = comboContas.getSelectedIndex();

        if (indice < 0 || indice >= contas.size()) {
            return;
        }

        contaSelecionada = contas.get(indice);

        mensagemErro.setText(" ");
        campoValor.setText("");

        atualizarSaldo();
    }

/////////////////////////////////////////////////////////////////////
// ATUALIZA O SALDO
/////////////////////////////////////////////////////////////////////

    private void atualizarSaldo() {

        if (contaSelecionada == null) {
            labelSaldo.setText("R$ 0,00");
            return;
        }

        labelSaldo.setText(
                String.format(
                        "R$ %.2f",
                        contaSelecionada.getSaldo()
                )
        );
    }

/////////////////////////////////////////////////////////////////////
// DEPOSITAR
/////////////////////////////////////////////////////////////////////

    private void depositar() {

        mensagemErro.setText(" ");

/////////////////////////////////////////////////////////////////////
// VERIFICA SE EXISTE CONTA SELECIONADA
/////////////////////////////////////////////////////////////////////

        if (contaSelecionada == null) {
            mensagemErro.setText("Selecione uma conta.");
            return;
        }

/////////////////////////////////////////////////////////////////////
// PEGA O VALOR
/////////////////////////////////////////////////////////////////////

        String valorTexto = campoValor.getText().trim();

        if (valorTexto.isEmpty()) {
            mensagemErro.setText("Digite o valor do depósito.");
            return;
        }

        double valor;

/////////////////////////////////////////////////////////////////////
// CONVERTE STRING PARA DOUBLE
/////////////////////////////////////////////////////////////////////

        try {
            valor = Double.parseDouble(
                    valorTexto.replace(",", ".")
            );

        } catch (NumberFormatException erro) {
            mensagemErro.setText("Digite um valor válido.");
            return;
        }

/////////////////////////////////////////////////////////////////////
// VALIDA O VALOR
/////////////////////////////////////////////////////////////////////

        if (valor <= 0) {
            mensagemErro.setText("O depósito deve ser maior que zero.");
            return;
        }

/////////////////////////////////////////////////////////////////////
// REALIZA O DEPÓSITO
/////////////////////////////////////////////////////////////////////

        contaSelecionada.efetuarDeposito(valor);

/////////////////////////////////////////////////////////////////////
// ATUALIZA O SALDO
/////////////////////////////////////////////////////////////////////

        atualizarSaldo();

/////////////////////////////////////////////////////////////////////
// LIMPA O CAMPO
/////////////////////////////////////////////////////////////////////

        campoValor.setText("");

/////////////////////////////////////////////////////////////////////
// MOSTRA SUCESSO
/////////////////////////////////////////////////////////////////////

        JOptionPane.showMessageDialog(
                this,
                "Depósito realizado com sucesso!",
                "Banco ES",
                JOptionPane.INFORMATION_MESSAGE
        );
    }

/////////////////////////////////////////////////////////////////////
// SACAR
/////////////////////////////////////////////////////////////////////

    private void sacar() {

        mensagemErro.setText(" ");

/////////////////////////////////////////////////////////////////////
// VERIFICA SE EXISTE CONTA SELECIONADA
/////////////////////////////////////////////////////////////////////

        if (contaSelecionada == null) {
            mensagemErro.setText("Selecione uma conta.");
            return;
        }

/////////////////////////////////////////////////////////////////////
// PEGA O VALOR
/////////////////////////////////////////////////////////////////////

        String valorTexto = campoValor.getText().trim();

        if (valorTexto.isEmpty()) {
            mensagemErro.setText("Digite o valor do saque.");
            return;
        }

        double valor;

/////////////////////////////////////////////////////////////////////
// CONVERTE STRING PARA DOUBLE
/////////////////////////////////////////////////////////////////////

        try {
            valor = Double.parseDouble(
                    valorTexto.replace(",", ".")
            );

        } catch (NumberFormatException erro) {
            mensagemErro.setText("Digite um valor válido.");
            return;
        }

/////////////////////////////////////////////////////////////////////
// VALIDA O VALOR
/////////////////////////////////////////////////////////////////////

        if (valor <= 0) {
            mensagemErro.setText("O saque deve ser maior que zero.");
            return;
        }

/////////////////////////////////////////////////////////////////////
// VERIFICA SALDO
/////////////////////////////////////////////////////////////////////

        if (valor > contaSelecionada.getSaldo()) {
            mensagemErro.setText("Saldo insuficiente.");
            return;
        }

/////////////////////////////////////////////////////////////////////
// REALIZA O SAQUE
/////////////////////////////////////////////////////////////////////

        contaSelecionada.efetuarSaque(valor);

/////////////////////////////////////////////////////////////////////
// ATUALIZA O SALDO
/////////////////////////////////////////////////////////////////////

        atualizarSaldo();

/////////////////////////////////////////////////////////////////////
// LIMPA O CAMPO
/////////////////////////////////////////////////////////////////////

        campoValor.setText("");

/////////////////////////////////////////////////////////////////////
// MOSTRA SUCESSO
/////////////////////////////////////////////////////////////////////

        JOptionPane.showMessageDialog(
                this,
                "Saque realizado com sucesso!",
                "Banco ES",
                JOptionPane.INFORMATION_MESSAGE
        );
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