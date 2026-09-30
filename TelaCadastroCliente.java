import javax.swing.*;

public class TelaCadastroCliente extends JFrame {

    private JComboBox<String> tipoConta;

    private JPanel painelCorrente;
    private JPanel painelPJ;

    TelaCadastroCliente() {

        setTitle("Cadastro de Cliente");
        setSize(600, 500);
        setLayout(null);

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);


        // ============================
        // TIPO DE CONTA
        // ============================

        JLabel labelConta = new JLabel("Tipo de conta:");
        labelConta.setBounds(50, 30, 120, 30);

        tipoConta = new JComboBox<>(
                new String[]{
                        "Conta Corrente",
                        "Conta PJ"
                }
        );

        tipoConta.setBounds(170, 30, 200, 30);

        add(labelConta);
        add(tipoConta);


        // ============================
        // PAINEL CONTA CORRENTE
        // ============================

        painelCorrente = new JPanel();
        painelCorrente.setLayout(null);
        painelCorrente.setBounds(0, 80, 600, 250);

        JLabel labelNome = new JLabel("Nome:");
        labelNome.setBounds(50, 20, 100, 30);

        JTextField campoNome = new JTextField();
        campoNome.setBounds(170, 20, 300, 30);

        JLabel labelCpf = new JLabel("CPF:");
        labelCpf.setBounds(50, 60, 100, 30);

        JTextField campoCpf = new JTextField();
        campoCpf.setBounds(170, 60, 300, 30);

        JButton botaoCadastrar = new JButton("Cadastrar");
        botaoCadastrar.setBounds(170, 110, 120, 30);


        botaoCadastrar.addActionListener(e -> {
            JOptionPane.showMessageDialog(
                    this,
                    "Conta criada com sucesso!"
            );
        });

        painelCorrente.add(botaoCadastrar);
        painelCorrente.add(labelNome);
        painelCorrente.add(campoNome);
        painelCorrente.add(labelCpf);
        painelCorrente.add(campoCpf);


        // ============================
        // PAINEL CONTA PJ
        // ============================

        painelPJ = new JPanel();
        painelPJ.setLayout(null);
        painelPJ.setBounds(0, 80, 600, 250);

        JLabel labelCnpj = new JLabel("CNPJ:");
        labelCnpj.setBounds(50, 20, 100, 30);

        JTextField campoCnpj = new JTextField();
        campoCnpj.setBounds(170, 20, 300, 30);

        JLabel labelEmpresa = new JLabel("Empresa:");
        labelEmpresa.setBounds(50, 60, 100, 30);

        JTextField campoEmpresa = new JTextField();
        campoEmpresa.setBounds(170, 60, 300, 30);

        JLabel labelProprietario = new JLabel("Proprietário:");
        labelProprietario.setBounds(50, 100, 100, 30);

        JTextField campoProprietario = new JTextField();
        campoProprietario.setBounds(170, 100, 300, 30);


        JButton botaoCadastrarPJ = new JButton("Cadastrar");
        botaoCadastrarPJ.setBounds(170, 150, 120, 30);

        botaoCadastrarPJ.addActionListener(e -> {
            JOptionPane.showMessageDialog(
                    this,
                    "Conta criada com sucesso!"
            );
        });

        painelPJ.add(botaoCadastrarPJ);

        painelPJ.add(labelCnpj);
        painelPJ.add(campoCnpj);

        painelPJ.add(labelEmpresa);
        painelPJ.add(campoEmpresa);

        painelPJ.add(labelProprietario);
        painelPJ.add(campoProprietario);


        // ============================
        // ADICIONA OS PAINÉIS
        // ============================

        add(painelCorrente);
        add(painelPJ);


        // ============================
        // ESTADO INICIAL
        // ============================

        painelCorrente.setVisible(true);
        painelPJ.setVisible(false);


        // ============================
        // EVENTO DO COMBOBOX
        // ============================

        tipoConta.addActionListener(e -> {

            String tipo =
                    (String) tipoConta.getSelectedItem();

            if (tipo.equals("Conta Corrente")) {

                painelCorrente.setVisible(true);
                painelPJ.setVisible(false);

            } else if (tipo.equals("Conta PJ")) {

                painelCorrente.setVisible(false);
                painelPJ.setVisible(true);
            }

            revalidate();
            repaint();
        });


        // ============================
        // FINALIZA
        // ============================

        setLocationRelativeTo(null);
        setVisible(true);

        }

    }
