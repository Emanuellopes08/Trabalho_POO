package InterfaceGrafica;

import Contas.*;
import Pessoas.Cliente;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class TelaCadastroCliente extends JFrame {

    // Paleta de cores
    private final Color AZUL_ESCURO = new Color(14, 14, 37);
    private final Color VERDE = new Color(72, 255, 129);
    private final Color VERDE_ESCURO = new Color(45, 190, 95);
    private final Color VERMELHO = new Color(180, 70, 70);
    private final Color VERMELHO_ESCURO = new Color(140, 45, 45);
    private final Color VERMELHO_ERRO = new Color(255, 90, 90);
    private final Color BRANCO = Color.WHITE;

    // Tipo de conta
    private JComboBox<String> tipoConta;

    // Dados do cliente
    private JTextField campoNome;
    private JTextField campoCpf;
    private JTextField campoDataNascimento;
    private JTextField campoRenda;
    private JTextField campoTelefone;
    private JTextField campoEndereco;
    private JTextField campoNomePai;
    private JTextField campoNomeMae;
    private JTextField campoEmail;

    // Contas.Conta Conjunta
    private JLabel labelCpfCotitular;
    private JTextField campoCpfCotitular;

    // Contas.Conta PJ
    private JLabel tituloDadosEmpresa;

    private JLabel labelCnpj;
    private JTextField campoCnpj;

    private JLabel labelNomeEmpresa;
    private JTextField campoNomeEmpresa;

    private JLabel labelFaturamento;
    private JTextField campoFaturamento;

    // Mensagem de erro
    private JLabel mensagemErro;


    public TelaCadastroCliente() {
        configurarJanela();
        criarTitulo();
        criarFormulario();
    }

/////////////////////////////////////////////////////////////////////
// CONFIGURA A JANELA
/////////////////////////////////////////////////////////////////////

    private void configurarJanela() {
        setSize(800, 850);
        setTitle("Banco ES - Cadastro de Pessoas.Cliente");
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

        topo.add(Box.createVerticalStrut(20));

        JLabel titulo = new JLabel("CADASTRO DE CLIENTE");
        JLabel subtitulo = new JLabel("Informe os dados do titular e escolha o tipo de conta");

        titulo.setFont(new Font("Arial", Font.BOLD, 32));
        titulo.setForeground(BRANCO);

        subtitulo.setFont(new Font("Arial", Font.PLAIN, 16));
        subtitulo.setForeground(VERDE);

        titulo.setAlignmentX(Component.CENTER_ALIGNMENT);
        subtitulo.setAlignmentX(Component.CENTER_ALIGNMENT);

        topo.add(titulo);
        topo.add(Box.createVerticalStrut(5));
        topo.add(subtitulo);
        topo.add(Box.createVerticalStrut(10));

        add(topo, BorderLayout.NORTH);
    }

/////////////////////////////////////////////////////////////////////
// CRIA O FORMULÁRIO
/////////////////////////////////////////////////////////////////////

    private void criarFormulario() {
        JPanel formulario = new JPanel();

        formulario.setBackground(AZUL_ESCURO);
        formulario.setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();

        gbc.insets = new Insets(5, 10, 5, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        Font fonteLabel = new Font("Arial", Font.BOLD, 15);

/////////////////////////////////////////////////////////////////////
// TÍTULO - DADOS DO TITULAR
/////////////////////////////////////////////////////////////////////

        JLabel tituloDadosTitular = new JLabel("DADOS DO TITULAR");

        tituloDadosTitular.setFont(new Font("Arial", Font.BOLD, 18));
        tituloDadosTitular.setForeground(VERDE);
        tituloDadosTitular.setHorizontalAlignment(SwingConstants.CENTER);

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.insets = new Insets(10, 10, 10, 10);

        formulario.add(tituloDadosTitular, gbc);

/////////////////////////////////////////////////////////////////////
// NOME
/////////////////////////////////////////////////////////////////////

        JLabel labelNome = criarLabel("Nome:", fonteLabel);
        campoNome = new JTextField(24);

        adicionarCampo(formulario, gbc, labelNome, campoNome, 1);

/////////////////////////////////////////////////////////////////////
// CPF DO TITULAR
/////////////////////////////////////////////////////////////////////

        JLabel labelCpf = criarLabel("CPF do titular:", fonteLabel);
        campoCpf = new JTextField(24);

        adicionarCampo(formulario, gbc, labelCpf, campoCpf, 2);

/////////////////////////////////////////////////////////////////////
// DATA DE NASCIMENTO
/////////////////////////////////////////////////////////////////////

        JLabel labelDataNascimento = criarLabel("Data de nascimento:", fonteLabel);
        campoDataNascimento = new JTextField(24);

        campoDataNascimento.setToolTipText("Formato: dd/MM/yyyy");

        adicionarCampo(formulario, gbc, labelDataNascimento, campoDataNascimento, 3);

/////////////////////////////////////////////////////////////////////
// RENDA
/////////////////////////////////////////////////////////////////////

        JLabel labelRenda = criarLabel("Renda:", fonteLabel);
        campoRenda = new JTextField(24);

        campoRenda.setToolTipText("Exemplo: 2500.00");

        adicionarCampo(formulario, gbc, labelRenda, campoRenda, 4);

/////////////////////////////////////////////////////////////////////
// TELEFONE
/////////////////////////////////////////////////////////////////////

        JLabel labelTelefone = criarLabel("Telefone:", fonteLabel);
        campoTelefone = new JTextField(24);

        adicionarCampo(formulario, gbc, labelTelefone, campoTelefone, 5);

/////////////////////////////////////////////////////////////////////
// ENDEREÇO
/////////////////////////////////////////////////////////////////////

        JLabel labelEndereco = criarLabel("Endereço:", fonteLabel);
        campoEndereco = new JTextField(24);

        adicionarCampo(formulario, gbc, labelEndereco, campoEndereco, 6);

/////////////////////////////////////////////////////////////////////
// NOME DO PAI
/////////////////////////////////////////////////////////////////////

        JLabel labelNomePai = criarLabel("Nome do pai:", fonteLabel);
        campoNomePai = new JTextField(24);

        adicionarCampo(formulario, gbc, labelNomePai, campoNomePai, 7);

/////////////////////////////////////////////////////////////////////
// NOME DA MÃE
/////////////////////////////////////////////////////////////////////

        JLabel labelNomeMae = criarLabel("Nome da mãe:", fonteLabel);
        campoNomeMae = new JTextField(24);

        adicionarCampo(formulario, gbc, labelNomeMae, campoNomeMae, 8);

/////////////////////////////////////////////////////////////////////
// EMAIL
/////////////////////////////////////////////////////////////////////

        JLabel labelEmail = criarLabel("Email:", fonteLabel);
        campoEmail = new JTextField(24);

        adicionarCampo(formulario, gbc, labelEmail, campoEmail, 9);

/////////////////////////////////////////////////////////////////////
// TIPO DE CONTA
/////////////////////////////////////////////////////////////////////

        JLabel labelTipoConta = criarLabel("Tipo de conta:", fonteLabel);

        tipoConta = new JComboBox<>(new String[]{
                "Contas.Conta Poupança",
                "Contas.Conta Corrente",
                "Contas.Conta Conjunta",
                "Contas.Conta PJ"
        });

        tipoConta.setFont(new Font("Arial", Font.PLAIN, 15));

        adicionarCampo(formulario, gbc, labelTipoConta, tipoConta, 10);

/////////////////////////////////////////////////////////////////////
// CPF DO COTITULAR
/////////////////////////////////////////////////////////////////////

        labelCpfCotitular = criarLabel("CPF do cotitular:", fonteLabel);
        campoCpfCotitular = new JTextField(24);

        adicionarCampo(formulario, gbc, labelCpfCotitular, campoCpfCotitular, 11);

/////////////////////////////////////////////////////////////////////
// TÍTULO - DADOS DA EMPRESA
/////////////////////////////////////////////////////////////////////

        tituloDadosEmpresa = new JLabel("DADOS DA EMPRESA");

        tituloDadosEmpresa.setFont(new Font("Arial", Font.BOLD, 18));
        tituloDadosEmpresa.setForeground(VERDE);
        tituloDadosEmpresa.setHorizontalAlignment(SwingConstants.CENTER);

        gbc.gridx = 0;
        gbc.gridy = 12;
        gbc.gridwidth = 2;
        gbc.insets = new Insets(15, 10, 10, 10);

        formulario.add(tituloDadosEmpresa, gbc);

/////////////////////////////////////////////////////////////////////
// CNPJ
/////////////////////////////////////////////////////////////////////

        labelCnpj = criarLabel("CNPJ da empresa:", fonteLabel);
        campoCnpj = new JTextField(24);

        adicionarCampo(formulario, gbc, labelCnpj, campoCnpj, 13);

/////////////////////////////////////////////////////////////////////
// NOME DA EMPRESA
/////////////////////////////////////////////////////////////////////

        labelNomeEmpresa = criarLabel("Nome da empresa:", fonteLabel);
        campoNomeEmpresa = new JTextField(24);

        adicionarCampo(formulario, gbc, labelNomeEmpresa, campoNomeEmpresa, 14);

/////////////////////////////////////////////////////////////////////
// FATURAMENTO
/////////////////////////////////////////////////////////////////////

        labelFaturamento = criarLabel("Faturamento da empresa:", fonteLabel);
        campoFaturamento = new JTextField(24);

        campoFaturamento.setToolTipText("Exemplo: 50000.00");

        adicionarCampo(formulario, gbc, labelFaturamento, campoFaturamento, 15);

/////////////////////////////////////////////////////////////////////
// MENSAGEM DE ERRO
/////////////////////////////////////////////////////////////////////

        mensagemErro = new JLabel(" ");

        mensagemErro.setFont(new Font("Arial", Font.PLAIN, 14));
        mensagemErro.setForeground(VERMELHO_ERRO);
        mensagemErro.setHorizontalAlignment(SwingConstants.CENTER);

        gbc.gridx = 0;
        gbc.gridy = 16;
        gbc.gridwidth = 2;
        gbc.insets = new Insets(10, 10, 5, 10);

        formulario.add(mensagemErro, gbc);

/////////////////////////////////////////////////////////////////////
// BOTÕES
/////////////////////////////////////////////////////////////////////

        BotaoArredondado btnCadastrar = new BotaoArredondado("Cadastrar");
        BotaoArredondado btnVoltar = new BotaoArredondado("Voltar");

        Dimension tamanhoBotao = new Dimension(180, 50);
        Font fonteBotao = new Font("Arial", Font.BOLD, 16);

        btnCadastrar.setPreferredSize(tamanhoBotao);
        btnVoltar.setPreferredSize(tamanhoBotao);

        btnCadastrar.setFont(fonteBotao);
        btnVoltar.setFont(fonteBotao);

        btnCadastrar.setBackground(VERDE);
        btnCadastrar.setForeground(AZUL_ESCURO);

        btnVoltar.setBackground(VERMELHO);
        btnVoltar.setForeground(BRANCO);

        JPanel painelBotoes = new JPanel();

        painelBotoes.setBackground(AZUL_ESCURO);

        painelBotoes.add(btnVoltar);
        painelBotoes.add(btnCadastrar);

        gbc.gridx = 0;
        gbc.gridy = 17;
        gbc.gridwidth = 2;
        gbc.insets = new Insets(15, 10, 15, 10);

        formulario.add(painelBotoes, gbc);

/////////////////////////////////////////////////////////////////////
// ALTERA OS CAMPOS DE ACORDO COM O TIPO DA CONTA
/////////////////////////////////////////////////////////////////////

        tipoConta.addActionListener(e -> atualizarCamposConta());

/////////////////////////////////////////////////////////////////////
// AÇÃO CADASTRAR
/////////////////////////////////////////////////////////////////////

        btnCadastrar.addActionListener(e -> validarCadastro());

/////////////////////////////////////////////////////////////////////
// AÇÃO VOLTAR
/////////////////////////////////////////////////////////////////////

        btnVoltar.addActionListener(e -> dispose());

/////////////////////////////////////////////////////////////////////
// EFEITOS DOS BOTÕES
/////////////////////////////////////////////////////////////////////

        adicionarEfeitoMouse(btnCadastrar, VERDE, VERDE_ESCURO);
        adicionarEfeitoMouse(btnVoltar, VERMELHO, VERMELHO_ESCURO);

        add(formulario, BorderLayout.CENTER);

/////////////////////////////////////////////////////////////////////
// DEFINE OS CAMPOS VISÍVEIS INICIAIS
/////////////////////////////////////////////////////////////////////

        atualizarCamposConta();
    }

/////////////////////////////////////////////////////////////////////
// CRIA UMA LABEL PADRÃO
/////////////////////////////////////////////////////////////////////

    private JLabel criarLabel(String texto, Font fonte) {
        JLabel label = new JLabel(texto);

        label.setFont(fonte);
        label.setForeground(BRANCO);

        return label;
    }

/////////////////////////////////////////////////////////////////////
// ADICIONA LABEL E CAMPO NO FORMULÁRIO
/////////////////////////////////////////////////////////////////////

    private void adicionarCampo(JPanel painel, GridBagConstraints gbc, JLabel label, JComponent campo, int linha) {
        gbc.gridwidth = 1;
        gbc.insets = new Insets(5, 10, 5, 10);

        gbc.gridx = 0;
        gbc.gridy = linha;

        painel.add(label, gbc);

        gbc.gridx = 1;

        painel.add(campo, gbc);
    }

/////////////////////////////////////////////////////////////////////
// MOSTRA OS CAMPOS NECESSÁRIOS PARA CADA TIPO DE CONTA
/////////////////////////////////////////////////////////////////////

    private void atualizarCamposConta() {
        String contaSelecionada = (String) tipoConta.getSelectedItem();

/////////////////////////////////////////////////////////////////////
// CONTA CONJUNTA
/////////////////////////////////////////////////////////////////////

        boolean contaConjunta = "Contas.Conta Conjunta".equals(contaSelecionada);

        labelCpfCotitular.setVisible(contaConjunta);
        campoCpfCotitular.setVisible(contaConjunta);

/////////////////////////////////////////////////////////////////////
// CONTA PJ
/////////////////////////////////////////////////////////////////////

        boolean contaPJ = "Contas.Conta PJ".equals(contaSelecionada);

        tituloDadosEmpresa.setVisible(contaPJ);

        labelCnpj.setVisible(contaPJ);
        campoCnpj.setVisible(contaPJ);

        labelNomeEmpresa.setVisible(contaPJ);
        campoNomeEmpresa.setVisible(contaPJ);

        labelFaturamento.setVisible(contaPJ);
        campoFaturamento.setVisible(contaPJ);

/////////////////////////////////////////////////////////////////////
// LIMPA CAMPOS QUE NÃO ESTÃO SENDO UTILIZADOS
/////////////////////////////////////////////////////////////////////

        if (!contaConjunta) {
            campoCpfCotitular.setText("");
        }

        if (!contaPJ) {
            campoCnpj.setText("");
            campoNomeEmpresa.setText("");
            campoFaturamento.setText("");
        }

        mensagemErro.setText(" ");

        revalidate();
        repaint();
    }

/////////////////////////////////////////////////////////////////////
// VALIDA E CADASTRA
/////////////////////////////////////////////////////////////////////

    private void validarCadastro() {

/////////////////////////////////////////////////////////////////////
// PEGA OS DADOS DIGITADOS
/////////////////////////////////////////////////////////////////////

        String nome = campoNome.getText().trim();
        String cpf = campoCpf.getText().trim();
        String dataTexto = campoDataNascimento.getText().trim();
        String rendaTexto = campoRenda.getText().trim();
        String telefone = campoTelefone.getText().trim();
        String endereco = campoEndereco.getText().trim();
        String nomePai = campoNomePai.getText().trim();
        String nomeMae = campoNomeMae.getText().trim();
        String email = campoEmail.getText().trim();

        String contaSelecionada = (String) tipoConta.getSelectedItem();

        mensagemErro.setText(" ");

/////////////////////////////////////////////////////////////////////
// VALIDA NOME
/////////////////////////////////////////////////////////////////////

        if (nome.isEmpty()) {
            mensagemErro.setText("Digite o nome do titular.");
            return;
        }

/////////////////////////////////////////////////////////////////////
// VALIDA CPF
/////////////////////////////////////////////////////////////////////

        if (cpf.isEmpty()) {
            mensagemErro.setText("Digite o CPF do titular.");
            return;
        }

/////////////////////////////////////////////////////////////////////
// VERIFICA CPF DUPLICADO
/////////////////////////////////////////////////////////////////////

        Cliente clienteExistente = SistemaBanco.buscarClientePorCpf(cpf);

        if (clienteExistente != null) {
            mensagemErro.setText("Já existe um cliente cadastrado com este CPF.");
            return;
        }

/////////////////////////////////////////////////////////////////////
// VALIDA DATA
/////////////////////////////////////////////////////////////////////

        if (dataTexto.isEmpty()) {
            mensagemErro.setText("Digite a data de nascimento.");
            return;
        }

        LocalDate dataNascimento;

        try {
            DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            dataNascimento = LocalDate.parse(dataTexto, formato);

        } catch (DateTimeParseException erro) {
            mensagemErro.setText("Data inválida. Use dd/MM/yyyy.");
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
// VALIDA NOME DO PAI
/////////////////////////////////////////////////////////////////////

        if (nomePai.isEmpty()) {
            mensagemErro.setText("Digite o nome do pai.");
            return;
        }

/////////////////////////////////////////////////////////////////////
// VALIDA NOME DA MÃE
/////////////////////////////////////////////////////////////////////

        if (nomeMae.isEmpty()) {
            mensagemErro.setText("Digite o nome da mãe.");
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
// DADOS ESPECÍFICOS DAS CONTAS
/////////////////////////////////////////////////////////////////////

        String cpfCotitular = "";
        String cnpj = "";
        String nomeEmpresa = "";
        double faturamento = 0;

/////////////////////////////////////////////////////////////////////
// CONTA CONJUNTA
/////////////////////////////////////////////////////////////////////

        Cliente cotitular = null;

        if ("Contas.Conta Conjunta".equals(contaSelecionada)) {

            cpfCotitular = campoCpfCotitular.getText().trim();

            if (cpfCotitular.isEmpty()) {
                mensagemErro.setText("Digite o CPF do cotitular.");
                return;
            }

            if (cpfCotitular.equals(cpf)) {
                mensagemErro.setText("O cotitular deve ter um CPF diferente.");
                return;
            }

            cotitular = SistemaBanco.buscarClientePorCpf(cpfCotitular);

            if (cotitular == null) {
                mensagemErro.setText("Cotitular não encontrado. Cadastre-o primeiro.");
                return;
            }
        }

/////////////////////////////////////////////////////////////////////
// CONTA PJ
/////////////////////////////////////////////////////////////////////

        if ("Contas.Conta PJ".equals(contaSelecionada)) {

            cnpj = campoCnpj.getText().trim();
            nomeEmpresa = campoNomeEmpresa.getText().trim();

            String faturamentoTexto = campoFaturamento.getText().trim();

            if (cnpj.isEmpty()) {
                mensagemErro.setText("Digite o CNPJ da empresa.");
                return;
            }

            if (SistemaBanco.buscarContaPjPorCnpj(cnpj) != null) {
                mensagemErro.setText("Já existe uma Contas.Conta PJ com este CNPJ.");
                return;
            }

            if (nomeEmpresa.isEmpty()) {
                mensagemErro.setText("Digite o nome da empresa.");
                return;
            }

            if (faturamentoTexto.isEmpty()) {
                mensagemErro.setText("Digite o faturamento da empresa.");
                return;
            }

            try {
                faturamento = Double.parseDouble(faturamentoTexto.replace(",", "."));

                if (faturamento < 0) {
                    mensagemErro.setText("O faturamento não pode ser negativo.");
                    return;
                }

            } catch (NumberFormatException erro) {
                mensagemErro.setText("Digite um faturamento válido.");
                return;
            }
        }

/////////////////////////////////////////////////////////////////////
// CRIA O CLIENTE
/////////////////////////////////////////////////////////////////////

        Cliente cliente = new Cliente(
                nome,
                cpf,
                dataNascimento,
                renda,
                telefone,
                endereco,
                nomePai,
                nomeMae,
                email
        );

/////////////////////////////////////////////////////////////////////
// CRIA A CONTA
/////////////////////////////////////////////////////////////////////

        Conta conta = null;

/////////////////////////////////////////////////////////////////////
// CONTA POUPANÇA
/////////////////////////////////////////////////////////////////////

        if ("Contas.Conta Poupança".equals(contaSelecionada)) {

            conta = new ContaPoupanca(cliente);
        }

/////////////////////////////////////////////////////////////////////
// CONTA CORRENTE
/////////////////////////////////////////////////////////////////////

        else if ("Contas.Conta Corrente".equals(contaSelecionada)) {

            conta = new ContaCorrente(cliente);
        }

/////////////////////////////////////////////////////////////////////
// CONTA CONJUNTA
/////////////////////////////////////////////////////////////////////

        else if ("Contas.Conta Conjunta".equals(contaSelecionada)) {

            conta = new ContaConjunta(cliente, cotitular);
        }

/////////////////////////////////////////////////////////////////////
// CONTA PJ
/////////////////////////////////////////////////////////////////////

        else if ("Contas.Conta PJ".equals(contaSelecionada)) {

            conta = new ContaPJ(
                    cnpj,
                    nomeEmpresa,
                    faturamento,
                    cliente
            );
        }

/////////////////////////////////////////////////////////////////////
// GARANTE QUE A CONTA FOI CRIADA
/////////////////////////////////////////////////////////////////////

        if (conta == null) {
            mensagemErro.setText("Não foi possível criar a conta.");
            return;
        }

/////////////////////////////////////////////////////////////////////
// SALVA CLIENTE
/////////////////////////////////////////////////////////////////////

        SistemaBanco.adicionarCliente(cliente);

/////////////////////////////////////////////////////////////////////
// SALVA CONTA
/////////////////////////////////////////////////////////////////////

        SistemaBanco.adicionarConta(conta);

/////////////////////////////////////////////////////////////////////
// MOSTRA SUCESSO
/////////////////////////////////////////////////////////////////////

        JOptionPane.showMessageDialog(
                this,
                "Pessoas.Cliente e conta cadastrados com sucesso!\nTipo: " + conta.getTipoConta(),
                "Banco ES",
                JOptionPane.INFORMATION_MESSAGE
        );

/////////////////////////////////////////////////////////////////////
// LIMPA OS CAMPOS
/////////////////////////////////////////////////////////////////////

        limparCamposCadastro();
    }

/////////////////////////////////////////////////////////////////////
// LIMPA OS CAMPOS DEPOIS DO CADASTRO
/////////////////////////////////////////////////////////////////////

    private void limparCamposCadastro() {

        campoNome.setText("");
        campoCpf.setText("");
        campoDataNascimento.setText("");
        campoRenda.setText("");
        campoTelefone.setText("");
        campoEndereco.setText("");
        campoNomePai.setText("");
        campoNomeMae.setText("");
        campoEmail.setText("");

        campoCpfCotitular.setText("");

        campoCnpj.setText("");
        campoNomeEmpresa.setText("");
        campoFaturamento.setText("");

        mensagemErro.setText(" ");

        tipoConta.setSelectedIndex(0);
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