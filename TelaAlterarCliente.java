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
    private JTextField campoCpf;
    private JLabel mensagemErro;

    private JTextField campoNome;
    private JTextField campoRenda;
    private JTextField campoTelefone;
    private JTextField campoEndereco;
    private JTextField campoEmail;

    private Cliente clienteEncontrado;


    public TelaAlterarCliente() {
        configurarJanela();
        criarTitulo();
        criarFormulario();
    }

/////////////////////////////////////////////////////////////////////
// CONFIGURA A JANELA
/////////////////////////////////////////////////////////////////////

    private void configurarJanela() {
        setSize(700, 680);
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
        JLabel subtitulo = new JLabel("Busque o cliente pelo CPF");

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

        JLabel labelCpf = criarLabel("CPF:", fonteLabel);
        campoCpf = new JTextField(22);

        adicionarCampo(painel, gbc, labelCpf, campoCpf, 0);

/////////////////////////////////////////////////////////////////////
// BOTÃO BUSCAR
/////////////////////////////////////////////////////////////////////

        BotaoArredondado btnBuscar = new BotaoArredondado("Buscar Cliente");

        btnBuscar.setPreferredSize(new Dimension(180, 45));
        btnBuscar.setFont(new Font("Arial", Font.BOLD, 15));
        btnBuscar.setBackground(VERDE);
        btnBuscar.setForeground(AZUL_ESCURO);

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 2;
        gbc.insets = new Insets(10, 10, 10, 10);

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
// TÍTULO DOS DADOS
/////////////////////////////////////////////////////////////////////

        JLabel tituloDados = new JLabel("DADOS DO CLIENTE");

        tituloDados.setFont(new Font("Arial", Font.BOLD, 18));
        tituloDados.setForeground(VERDE);
        tituloDados.setHorizontalAlignment(SwingConstants.CENTER);

        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        gbc.insets = new Insets(15, 10, 10, 10);

        painel.add(tituloDados, gbc);

/////////////////////////////////////////////////////////////////////
// NOME
/////////////////////////////////////////////////////////////////////

        JLabel labelNome = criarLabel("Nome:", fonteLabel);
        campoNome = new JTextField(22);

        adicionarCampo(painel, gbc, labelNome, campoNome, 4);

/////////////////////////////////////////////////////////////////////
// RENDA
/////////////////////////////////////////////////////////////////////

        JLabel labelRenda = criarLabel("Renda:", fonteLabel);
        campoRenda = new JTextField(22);

        adicionarCampo(painel, gbc, labelRenda, campoRenda, 5);

/////////////////////////////////////////////////////////////////////
// TELEFONE
/////////////////////////////////////////////////////////////////////

        JLabel labelTelefone = criarLabel("Telefone:", fonteLabel);
        campoTelefone = new JTextField(22);

        adicionarCampo(painel, gbc, labelTelefone, campoTelefone, 6);

/////////////////////////////////////////////////////////////////////
// ENDEREÇO
/////////////////////////////////////////////////////////////////////

        JLabel labelEndereco = criarLabel("Endereço:", fonteLabel);
        campoEndereco = new JTextField(22);

        adicionarCampo(painel, gbc, labelEndereco, campoEndereco, 7);

/////////////////////////////////////////////////////////////////////
// EMAIL
/////////////////////////////////////////////////////////////////////

        JLabel labelEmail = criarLabel("Email:", fonteLabel);
        campoEmail = new JTextField(22);

        adicionarCampo(painel, gbc, labelEmail, campoEmail, 8);

/////////////////////////////////////////////////////////////////////
// BOTÕES
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
// CAMPOS COMEÇAM DESATIVADOS
/////////////////////////////////////////////////////////////////////

        habilitarCampos(false);
        btnSalvar.setEnabled(false);

/////////////////////////////////////////////////////////////////////
// AÇÃO BUSCAR
/////////////////////////////////////////////////////////////////////

        btnBuscar.addActionListener(e -> {
            buscarCliente();

            if (clienteEncontrado != null) {
                btnSalvar.setEnabled(true);
            } else {
                btnSalvar.setEnabled(false);
            }
        });

/////////////////////////////////////////////////////////////////////
// AÇÃO SALVAR
/////////////////////////////////////////////////////////////////////

        btnSalvar.addActionListener(e -> salvarAlteracoes());

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
// CRIA LABEL PADRÃO
/////////////////////////////////////////////////////////////////////

    private JLabel criarLabel(String texto, Font fonte) {
        JLabel label = new JLabel(texto);

        label.setFont(fonte);
        label.setForeground(BRANCO);

        return label;
    }

/////////////////////////////////////////////////////////////////////
// ADICIONA CAMPO NO FORMULÁRIO
/////////////////////////////////////////////////////////////////////

    private void adicionarCampo(JPanel painel, GridBagConstraints gbc, JLabel label, JComponent campo, int linha) {
        gbc.gridwidth = 1;
        gbc.insets = new Insets(6, 10, 6, 10);

        gbc.gridx = 0;
        gbc.gridy = linha;

        painel.add(label, gbc);

        gbc.gridx = 1;

        painel.add(campo, gbc);
    }

/////////////////////////////////////////////////////////////////////
// BUSCA CLIENTE PELO CPF
/////////////////////////////////////////////////////////////////////

    private void buscarCliente() {
        String cpf = campoCpf.getText().trim();

        mensagemErro.setText(" ");
        clienteEncontrado = null;

        limparCampos();
        habilitarCampos(false);

/////////////////////////////////////////////////////////////////////
// VALIDA CPF
/////////////////////////////////////////////////////////////////////

        if (cpf.isEmpty()) {
            mensagemErro.setText("Digite o CPF do cliente.");
            return;
        }

/////////////////////////////////////////////////////////////////////
// BUSCA NO SISTEMA
/////////////////////////////////////////////////////////////////////

        clienteEncontrado = SistemaBanco.buscarClientePorCpf(cpf);

        if (clienteEncontrado == null) {
            mensagemErro.setText("Cliente não encontrado.");
            return;
        }

/////////////////////////////////////////////////////////////////////
// PREENCHE OS DADOS ATUAIS
/////////////////////////////////////////////////////////////////////

        campoNome.setText(clienteEncontrado.getNome());
        campoRenda.setText(String.valueOf(clienteEncontrado.getRenda()));
        campoTelefone.setText(clienteEncontrado.getTelefone());
        campoEndereco.setText(clienteEncontrado.getEndereco());
        campoEmail.setText(clienteEncontrado.getEmail());

        habilitarCampos(true);
    }

/////////////////////////////////////////////////////////////////////
// SALVA AS ALTERAÇÕES
/////////////////////////////////////////////////////////////////////

    private void salvarAlteracoes() {

        if (clienteEncontrado == null) {
            mensagemErro.setText("Busque um cliente primeiro.");
            return;
        }

/////////////////////////////////////////////////////////////////////
// PEGA OS DADOS
/////////////////////////////////////////////////////////////////////

        String nome = campoNome.getText().trim();
        String rendaTexto = campoRenda.getText().trim();
        String telefone = campoTelefone.getText().trim();
        String endereco = campoEndereco.getText().trim();
        String email = campoEmail.getText().trim();

/////////////////////////////////////////////////////////////////////
// VALIDA NOME
/////////////////////////////////////////////////////////////////////

        if (nome.isEmpty()) {
            mensagemErro.setText("Digite o nome.");
            return;
        }

/////////////////////////////////////////////////////////////////////
// VALIDA RENDA
/////////////////////////////////////////////////////////////////////

        if (rendaTexto.isEmpty()) {
            mensagemErro.setText("Digite a renda.");
            return;
        }

        double renda;

        try {
            renda = Double.parseDouble(rendaTexto.replace(",", "."));

            if (renda < 0) {
                mensagemErro.setText("A renda não pode ser negativa.");
                return;
            }

        } catch (NumberFormatException erro) {
            mensagemErro.setText("Digite uma renda válida.");
            return;
        }

/////////////////////////////////////////////////////////////////////
// VALIDA TELEFONE
/////////////////////////////////////////////////////////////////////

        if (telefone.isEmpty()) {
            mensagemErro.setText("Digite o telefone.");
            return;
        }

/////////////////////////////////////////////////////////////////////
// VALIDA ENDEREÇO
/////////////////////////////////////////////////////////////////////

        if (endereco.isEmpty()) {
            mensagemErro.setText("Digite o endereço.");
            return;
        }

/////////////////////////////////////////////////////////////////////
// VALIDA EMAIL
/////////////////////////////////////////////////////////////////////

        if (email.isEmpty()) {
            mensagemErro.setText("Digite o email.");
            return;
        }

/////////////////////////////////////////////////////////////////////
// ALTERA OS DADOS
/////////////////////////////////////////////////////////////////////

        clienteEncontrado.setNome(nome);
        clienteEncontrado.setRenda(renda);
        clienteEncontrado.setTelefone(telefone);
        clienteEncontrado.setEndereco(endereco);
        clienteEncontrado.setEmail(email);

/////////////////////////////////////////////////////////////////////
// SUCESSO
/////////////////////////////////////////////////////////////////////

        JOptionPane.showMessageDialog(
                this,
                "Cliente alterado com sucesso!",
                "Banco ES",
                JOptionPane.INFORMATION_MESSAGE
        );

        mensagemErro.setText(" ");
    }

/////////////////////////////////////////////////////////////////////
// HABILITA OU DESABILITA CAMPOS
/////////////////////////////////////////////////////////////////////

    private void habilitarCampos(boolean habilitar) {
        campoNome.setEnabled(habilitar);
        campoRenda.setEnabled(habilitar);
        campoTelefone.setEnabled(habilitar);
        campoEndereco.setEnabled(habilitar);
        campoEmail.setEnabled(habilitar);
    }

/////////////////////////////////////////////////////////////////////
// LIMPA OS CAMPOS
/////////////////////////////////////////////////////////////////////

    private void limparCampos() {
        campoNome.setText("");
        campoRenda.setText("");
        campoTelefone.setText("");
        campoEndereco.setText("");
        campoEmail.setText("");
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