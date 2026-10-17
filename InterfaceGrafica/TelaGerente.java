package InterfaceGrafica;

import Pessoas.Funcionario;

import javax.swing.*;
import java.awt.*;

public class TelaGerente extends JFrame {

    // =========================================================
    // CORES DO PROJETO
    // =========================================================

    private final Color AZUL_ESCURO = new Color(14, 14, 37);
    private final Color AZUL_SECUNDARIO = new Color(28, 30, 56);

    private final Color VERDE = new Color(72, 255, 129);
    private final Color VERDE_ESCURO = new Color(45, 190, 95);

    private final Color VERMELHO = new Color(255, 90, 90);
    private final Color VERMELHO_ESCURO = new Color(190, 55, 55);

    private final Color BRANCO = Color.WHITE;

    // =========================================================
    // CONSTRUTOR
    // =========================================================

    public TelaGerente() {
        configurarJanela();
        criarTitulo();
        criarMenu();

        setVisible(true);
    }

    // =========================================================
    // CONFIGURAÇÃO DA JANELA
    // =========================================================

    private void configurarJanela() {

        setSize(700, 600);

        setTitle("Banco ES - Contas.Conta do Pessoas.Gerente");

        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        setLocationRelativeTo(null);

        setLayout(new BorderLayout());

        getContentPane().setBackground(AZUL_ESCURO);
    }

    // =========================================================
    // TÍTULO
    // =========================================================

    private void criarTitulo() {

        JPanel topo = new JPanel();

        topo.setBackground(AZUL_ESCURO);

        topo.setLayout(new BoxLayout(topo, BoxLayout.Y_AXIS));

        topo.add(Box.createVerticalStrut(30));

        JLabel titulo = new JLabel("CONTA DO GERENTE");

        JLabel subtitulo = new JLabel("Gerenciamento de funcionários");

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

    // =========================================================
    // MENU PRINCIPAL
    // =========================================================

    private void criarMenu() {

        JPanel painel = new JPanel();

        painel.setBackground(AZUL_ESCURO);

        painel.setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();

        gbc.insets = new Insets(10, 10, 10, 10);

        gbc.fill = GridBagConstraints.HORIZONTAL;

        // -----------------------------------------------------
        // BOTÕES
        // -----------------------------------------------------

        BotaoArredondado btnCadastrar =
                new BotaoArredondado("Cadastrar funcionário");

        BotaoArredondado btnConsultar =
                new BotaoArredondado("Consultar funcionário");

        BotaoArredondado btnAlterar =
                new BotaoArredondado("Alterar funcionário");

        BotaoArredondado btnExcluir =
                new BotaoArredondado("Excluir funcionário");

        BotaoArredondado btnVoltar =
                new BotaoArredondado("Voltar");

        // -----------------------------------------------------
        // TAMANHO
        // -----------------------------------------------------

        Dimension tamanho = new Dimension(250, 50);

        btnCadastrar.setPreferredSize(tamanho);
        btnConsultar.setPreferredSize(tamanho);
        btnAlterar.setPreferredSize(tamanho);
        btnExcluir.setPreferredSize(tamanho);
        btnVoltar.setPreferredSize(tamanho);

        // -----------------------------------------------------
        // CORES
        // -----------------------------------------------------

        btnCadastrar.setBackground(VERDE);
        btnCadastrar.setForeground(AZUL_ESCURO);

        btnConsultar.setBackground(VERDE);
        btnConsultar.setForeground(AZUL_ESCURO);

        btnAlterar.setBackground(VERDE);
        btnAlterar.setForeground(AZUL_ESCURO);

        btnExcluir.setBackground(VERMELHO);
        btnExcluir.setForeground(BRANCO);

        btnVoltar.setBackground(AZUL_SECUNDARIO);
        btnVoltar.setForeground(BRANCO);

        // -----------------------------------------------------
        // FONTE
        // -----------------------------------------------------

        Font fonteBotao = new Font("Arial", Font.BOLD, 16);

        btnCadastrar.setFont(fonteBotao);
        btnConsultar.setFont(fonteBotao);
        btnAlterar.setFont(fonteBotao);
        btnExcluir.setFont(fonteBotao);
        btnVoltar.setFont(fonteBotao);

        // -----------------------------------------------------
        // LINHA 1
        // -----------------------------------------------------

        gbc.gridx = 0;
        gbc.gridy = 0;

        painel.add(btnCadastrar, gbc);

        gbc.gridx = 1;

        painel.add(btnConsultar, gbc);

        // -----------------------------------------------------
        // LINHA 2
        // -----------------------------------------------------

        gbc.gridx = 0;
        gbc.gridy = 1;

        painel.add(btnAlterar, gbc);

        gbc.gridx = 1;

        painel.add(btnExcluir, gbc);

        // -----------------------------------------------------
        // LINHA 3
        // -----------------------------------------------------

        gbc.gridx = 0;
        gbc.gridy = 2;

        painel.add(btnVoltar, gbc);

        add(painel, BorderLayout.CENTER);

        // =====================================================
        // EFEITOS DOS BOTÕES
        // =====================================================

        adicionarEfeitoMouse(btnCadastrar, VERDE, VERDE_ESCURO);
        adicionarEfeitoMouse(btnConsultar, VERDE, VERDE_ESCURO);
        adicionarEfeitoMouse(btnAlterar, VERDE, VERDE_ESCURO);

        adicionarEfeitoMouse(btnExcluir, VERMELHO, VERMELHO_ESCURO);

        // =====================================================
        // AÇÕES
        // =====================================================

        btnCadastrar.addActionListener(e -> abrirCadastro());

        btnConsultar.addActionListener(e -> abrirConsulta());

        btnAlterar.addActionListener(e -> abrirAlteracao());

        btnExcluir.addActionListener(e -> abrirExclusao());

        btnVoltar.addActionListener(e -> dispose());
    }

    // =========================================================
    // CADASTRAR FUNCIONÁRIO
    // =========================================================

    private void abrirCadastro() {

        JTextField campoUsuario = new JTextField();

        JPasswordField campoSenha = new JPasswordField();

        JTextField campoCodigo = new JTextField();

        JTextField campoCargo = new JTextField();

        JPanel painel = new JPanel(new GridLayout(4, 2, 10, 10));

        painel.setBackground(AZUL_ESCURO);

        JLabel lblUsuario = criarLabel("Usuário:");

        JLabel lblSenha = criarLabel("Senha:");

        JLabel lblCodigo = criarLabel("Código:");

        JLabel lblCargo = criarLabel("Cargo:");

        painel.add(lblUsuario);
        painel.add(campoUsuario);

        painel.add(lblSenha);
        painel.add(campoSenha);

        painel.add(lblCodigo);
        painel.add(campoCodigo);

        painel.add(lblCargo);
        painel.add(campoCargo);

        int resultado = JOptionPane.showConfirmDialog(
                this,
                painel,
                "Cadastrar funcionário",
                JOptionPane.OK_CANCEL_OPTION,
                JOptionPane.PLAIN_MESSAGE
        );

        if (resultado != JOptionPane.OK_OPTION) {
            return;
        }

        String usuario = campoUsuario.getText().trim();

        String senha = new String(campoSenha.getPassword());

        String codigoTexto = campoCodigo.getText().trim();

        String cargo = campoCargo.getText().trim();

        // -----------------------------------------------------
        // VALIDAÇÕES
        // -----------------------------------------------------

        if (usuario.isEmpty()) {
            mostrarErro("Digite o usuário.");
            return;
        }

        if (senha.isEmpty()) {
            mostrarErro("Digite a senha.");
            return;
        }

        if (senha.length() < 8) {
            mostrarErro("A senha deve ter no mínimo 8 caracteres.");
            return;
        }

        if (codigoTexto.isEmpty()) {
            mostrarErro("Digite o código.");
            return;
        }

        if (!codigoTexto.matches("\\d+")) {
            mostrarErro("O código deve conter apenas números.");
            return;
        }

        if (cargo.isEmpty()) {
            mostrarErro("Digite o cargo.");
            return;
        }

        int codigo = Integer.parseInt(codigoTexto);

        // -----------------------------------------------------
        // VERIFICA SE O CÓDIGO JÁ EXISTE
        // -----------------------------------------------------

        if (SistemaBanco.buscarFuncionarioPorCodigo(codigo) != null) {
            mostrarErro("Já existe um funcionário com esse código.");
            return;
        }

        // -----------------------------------------------------
        // CADASTRO
        // -----------------------------------------------------

        Funcionario funcionario = new Funcionario(
                usuario,
                senha,
                codigo,
                cargo
        );

        SistemaBanco.adicionarFuncionario(funcionario);

        JOptionPane.showMessageDialog(
                this,
                "Contas.Conta cadastrada!",
                "Banco ES",
                JOptionPane.INFORMATION_MESSAGE
        );
    }

    // =========================================================
    // CONSULTAR FUNCIONÁRIO
    // =========================================================

    private void abrirConsulta() {

        String codigoTexto = JOptionPane.showInputDialog(
                this,
                "Digite o código do funcionário:",
                "Consultar funcionário",
                JOptionPane.QUESTION_MESSAGE
        );

        if (codigoTexto == null) {
            return;
        }

        codigoTexto = codigoTexto.trim();

        if (codigoTexto.isEmpty()) {
            mostrarErro("Digite o código.");
            return;
        }

        if (!codigoTexto.matches("\\d+")) {
            mostrarErro("O código deve conter apenas números.");
            return;
        }

        int codigo = Integer.parseInt(codigoTexto);

        Funcionario funcionario =
                SistemaBanco.buscarFuncionarioPorCodigo(codigo);

        if (funcionario == null) {
            mostrarErro("Funcionário não encontrado.");
            return;
        }

        JOptionPane.showMessageDialog(
                this,
                "Usuário: " + funcionario.getUsuario()
                        + "\nCódigo: " + funcionario.getCodigo()
                        + "\nCargo: " + funcionario.getCargo(),
                "Funcionário encontrado",
                JOptionPane.INFORMATION_MESSAGE
        );
    }

    // =========================================================
    // ALTERAR FUNCIONÁRIO
    // =========================================================

    private void abrirAlteracao() {

        String codigoTexto = JOptionPane.showInputDialog(
                this,
                "Digite o código do funcionário:",
                "Alterar funcionário",
                JOptionPane.QUESTION_MESSAGE
        );

        if (codigoTexto == null) {
            return;
        }

        codigoTexto = codigoTexto.trim();

        if (!codigoTexto.matches("\\d+")) {
            mostrarErro("O código deve conter apenas números.");
            return;
        }

        int codigo = Integer.parseInt(codigoTexto);

        Funcionario funcionario =
                SistemaBanco.buscarFuncionarioPorCodigo(codigo);

        if (funcionario == null) {
            mostrarErro("Funcionário não encontrado.");
            return;
        }

        JTextField campoUsuario =
                new JTextField(funcionario.getUsuario());

        JPasswordField campoSenha =
                new JPasswordField();

        JTextField campoCargo =
                new JTextField(funcionario.getCargo());

        JPanel painel = new JPanel(new GridLayout(3, 2, 10, 10));

        painel.setBackground(AZUL_ESCURO);

        painel.add(criarLabel("Usuário:"));
        painel.add(campoUsuario);

        painel.add(criarLabel("Nova senha:"));
        painel.add(campoSenha);

        painel.add(criarLabel("Cargo:"));
        painel.add(campoCargo);

        int resultado = JOptionPane.showConfirmDialog(
                this,
                painel,
                "Alterar funcionário",
                JOptionPane.OK_CANCEL_OPTION,
                JOptionPane.PLAIN_MESSAGE
        );

        if (resultado != JOptionPane.OK_OPTION) {
            return;
        }

        String novoUsuario = campoUsuario.getText().trim();

        String novaSenha =
                new String(campoSenha.getPassword());

        String novoCargo =
                campoCargo.getText().trim();

        // -----------------------------------------------------
        // VALIDAÇÕES
        // -----------------------------------------------------

        if (novoUsuario.isEmpty()) {
            mostrarErro("Digite o usuário.");
            return;
        }

        if (novaSenha.isEmpty()) {
            mostrarErro("Digite a nova senha.");
            return;
        }

        if (novaSenha.length() < 8) {
            mostrarErro("A senha deve ter no mínimo 8 caracteres.");
            return;
        }

        if (novoCargo.isEmpty()) {
            mostrarErro("Digite o cargo.");
            return;
        }

        // -----------------------------------------------------
        // ALTERAÇÃO
        // -----------------------------------------------------

        funcionario.setUsuario(novoUsuario);
        funcionario.setSenha(novaSenha);
        funcionario.setCargo(novoCargo);

        JOptionPane.showMessageDialog(
                this,
                "Contas.Conta alterada!",
                "Banco ES",
                JOptionPane.INFORMATION_MESSAGE
        );
    }

    // =========================================================
    // EXCLUIR FUNCIONÁRIO
    // =========================================================

    private void abrirExclusao() {

        String codigoTexto = JOptionPane.showInputDialog(
                this,
                "Digite o código do funcionário:",
                "Excluir funcionário",
                JOptionPane.QUESTION_MESSAGE
        );

        if (codigoTexto == null) {
            return;
        }

        codigoTexto = codigoTexto.trim();

        if (!codigoTexto.matches("\\d+")) {
            mostrarErro("O código deve conter apenas números.");
            return;
        }

        int codigo = Integer.parseInt(codigoTexto);

        Funcionario funcionario =
                SistemaBanco.buscarFuncionarioPorCodigo(codigo);

        if (funcionario == null) {
            mostrarErro("Funcionário não encontrado.");
            return;
        }

        // -----------------------------------------------------
        // MOSTRA OS DADOS ANTES DA EXCLUSÃO
        // -----------------------------------------------------

        String dados =
                "Usuário: " + funcionario.getUsuario()
                        + "\nCódigo: " + funcionario.getCodigo()
                        + "\nCargo: " + funcionario.getCargo()
                        + "\n\nDeseja realmente excluir esta conta?";

        int confirmacao = JOptionPane.showConfirmDialog(
                this,
                dados,
                "Confirmar exclusão",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.WARNING_MESSAGE
        );

        if (confirmacao == JOptionPane.YES_OPTION) {

            SistemaBanco.excluirFuncionario(funcionario);

            JOptionPane.showMessageDialog(
                    this,
                    "Contas.Conta excluída!",
                    "Banco ES",
                    JOptionPane.INFORMATION_MESSAGE
            );
        }
    }

    // =========================================================
    // CRIAR LABEL
    // =========================================================

    private JLabel criarLabel(String texto) {

        JLabel label = new JLabel(texto);

        label.setFont(
                new Font("Arial", Font.BOLD, 16)
        );

        label.setForeground(BRANCO);

        return label;
    }

    // =========================================================
    // MENSAGEM DE ERRO
    // =========================================================

    private void mostrarErro(String mensagem) {

        JOptionPane.showMessageDialog(
                this,
                mensagem,
                "Erro",
                JOptionPane.ERROR_MESSAGE
        );
    }

    // =========================================================
    // EFEITO DOS BOTÕES
    // =========================================================

    private void adicionarEfeitoMouse(
            BotaoArredondado botao,
            Color corNormal,
            Color corEscura
    ) {

        botao.addMouseListener(
                new java.awt.event.MouseAdapter() {

                    @Override
                    public void mouseEntered(
                            java.awt.event.MouseEvent evt
                    ) {
                        botao.setBackground(corEscura);
                    }

                    @Override
                    public void mouseExited(
                            java.awt.event.MouseEvent evt
                    ) {
                        botao.setBackground(corNormal);
                    }
                }
        );
    }

}