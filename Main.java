import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    static Scanner entrada = new Scanner(System.in);
    static List<Cliente> clientes = new ArrayList<>();
    static List<Conta> contas = new ArrayList<>();
    static List<Funcionario> funcionarios = new ArrayList<>();

    static Gerente gerente = new Gerente ("Dorivaldo Silva", "Dori123456$",1);

    public static void main(String[] args) {

        TelaPrincipal tela = new TelaPrincipal();
        tela.setVisible(true);

            int opcao;

            do {

                System.out.println("Bem vindo ao Banco ES:");
                System.out.println(" ___________________________");
                System.out.println("|                           |");
                System.out.println("|        MENU:              |");
                System.out.println("|___________________________|");
                System.out.println("|                           |");
                System.out.println("|1. Cadastro do Cliente     |");
                System.out.println("|2. Abrir Conta             |");
                System.out.println("|3. Funcionário             |");
                System.out.println("|4. Cliente                 |");
                System.out.println("|5. Gerente                 |");
                System.out.println("|0. Sair                    |");
                System.out.println("|___________________________|");

                System.out.print("Digite o número da opção desejada: ");
                opcao = entrada.nextInt();
                entrada.nextLine();

                switch (opcao) {
                    case 1:
                        //Para fazer o cadastro do cliente
                        clientes.add(solicitarDados(null)); //add joga para lista declarada na linha 12
                        break;

                    case 2:
                        System.out.println(" _________________________");
                        System.out.println("|        MENU:            |");
                        System.out.println("|_________________________|");
                        System.out.println("|                         |");
                        System.out.println("|1. Conta Poupança        |");
                        System.out.println("|2. Conta Corrente        |");
                        System.out.println("|3. Conta Conjunta        |");
                        System.out.println("|4. Conta Pessoa Juridica |");
                        System.out.println("|_________________________|");
                        System.out.print("Escolha o tipo de Conta: ");
                        int opcaoTipoConta = entrada.nextInt();
                        entrada.nextLine();

                        System.out.print("CPF: ");
                        String cpf = entrada.nextLine();
                        Cliente cliente = verificaExistenciaCliente(cpf);

                        if (cliente == null) {
                            System.out.println();
                            System.out.println("Cliente não encontrado.");
                            System.out.println("CPF informado: " + cpf);
                            System.out.print("Deseja cadastrar este cliente? (S/N): ");

                            String resposta = entrada.nextLine();

                            if (resposta.equalsIgnoreCase("S")) {
                                cliente = solicitarDados(cpf);
                                clientes.add(cliente);

                                System.out.println("Cliente cadastrado com sucesso!");
                            } else {
                                System.out.println("Abertura da conta cancelada.");
                                break;
                            }
                        }
                        Conta conta = null;


                        switch (opcaoTipoConta) {
                            case 1:
                                conta = new ContaPoupanca(cliente);
                                break;

                            case 2:
                                conta = new ContaCorrente(cliente);
                                break;

                            case 3:
                                System.out.print("CPF do segundo titular: ");
                                String cpfCotitular = entrada.nextLine();

                                Cliente coTitular = verificaExistenciaCliente(cpfCotitular);

                                if (coTitular == null) {
                                    System.out.println();
                                    System.out.println("Cotitular não encontrado.");
                                    System.out.println("CPF informado: " + cpfCotitular);
                                    System.out.print("Deseja cadastrar este cotitular? (S/N): ");

                                    String respostaCotitular = entrada.nextLine();

                                    if (respostaCotitular.equalsIgnoreCase("S")) {
                                        coTitular = solicitarDados(cpfCotitular);
                                        clientes.add(coTitular);

                                        System.out.println("Cotitular cadastrado com sucesso!");
                                    } else {
                                        System.out.println("Abertura da conta cancelada.");
                                        break;
                                    }
                                }
                                conta = new ContaConjunta(cliente, coTitular);
                                break;

                            case 4:
                                System.out.print("CNPJ: ");
                                String cnpj = entrada.nextLine();

                                System.out.print("Nome da empresa: ");
                                String nomeEmpresa = entrada.nextLine();

                                System.out.print("Fatura da empresa: ");
                                double faturaEmpresa = entrada.nextDouble();
                                entrada.nextLine();

                                conta = new ContaPJ(cnpj, nomeEmpresa, faturaEmpresa, cliente);

                                break;

                            default:
                                System.out.println("Tipo de conta inválido.");
                                break;
                        }
                        if (conta != null) {
                            contas.add(conta);

                            System.out.println();
                            System.out.println("Conta aberta com sucesso!");
                            System.out.println("Titular: " + cliente.getNome());
                            System.out.println("Tipo da conta: " + conta.getTipoConta());
                        }

                        break;

                    case 3:
                        System.out.println(" _________________________");
                        System.out.println("|   ÁREA DO FUNCIONÁRIO   |");
                        System.out.println("|_________________________|");

                        System.out.print("Código: ");
                        int codigoLogin = entrada.nextInt();
                        entrada.nextLine();

                        System.out.print("Senha: ");
                        String senhaLogin = entrada.nextLine();

                        Funcionario funcionarioLogado = verificaExistenciaFuncionario(codigoLogin);

                        if (funcionarioLogado == null) {
                            System.out.println("Funcionário não encontrado.");
                            break;
                        }

                        if (!senhaLogin.equals(funcionarioLogado.getSenha())) {
                            System.out.println("Senha incorreta.");
                            break;
                        }

                        System.out.println();
                        System.out.println("Login realizado com sucesso!");
                        System.out.println("Bem-vindo, " + funcionarioLogado.getUsuario());

                        int opcaoFuncionario;

                        do {

                            System.out.println();
                            System.out.println(" ___________________________");
                            System.out.println("|    MENU DO FUNCIONÁRIO    |");
                            System.out.println("|___________________________|");
                            System.out.println("|1. Cadastrar cliente       |");
                            System.out.println("|2. Consultar cliente       |");
                            System.out.println("|3. Alterar cliente         |");
                            System.out.println("|4. Excluir cliente         |");
                            System.out.println("|0. Voltar                  |");
                            System.out.println("|___________________________|");

                            System.out.print("Escolha uma opção: ");
                            opcaoFuncionario = entrada.nextInt();
                            entrada.nextLine();

                            switch (opcaoFuncionario) {

                                case 1:
                                    clientes.add(solicitarDados(null));

                                    System.out.println("Cliente cadastrado com sucesso!");
                                    break;

                                case 2:
                                    System.out.println(" _________________________");
                                    System.out.println("|    CONSULTAR CLIENTE     |");
                                    System.out.println("|_________________________|");

                                    System.out.print("Informe o CPF do cliente: ");
                                    String cpfConsulta = entrada.nextLine();

                                    Cliente clienteConsultado =
                                            verificaExistenciaCliente(cpfConsulta);

                                    if (clienteConsultado == null) {
                                        System.out.println("Cliente não encontrado.");
                                    } else {
                                        System.out.println();
                                        System.out.println("Cliente encontrado:");
                                        System.out.println("Nome: " + clienteConsultado.getNome());
                                        System.out.println("CPF: " + clienteConsultado.getCpf());
                                    }

                                    break;

                                case 3:
                                    System.out.println(" _________________________");
                                    System.out.println("|     ALTERAR CLIENTE      |");
                                    System.out.println("|_________________________|");

                                    System.out.print("Informe o CPF do cliente: ");
                                    String cpfAlteracao = entrada.nextLine();

                                    Cliente clienteAlteracao =
                                            verificaExistenciaCliente(cpfAlteracao);

                                    if (clienteAlteracao == null) {
                                        System.out.println("Cliente não encontrado.");
                                        break;
                                    }

                                    System.out.println();
                                    System.out.println("Cliente: " + clienteAlteracao.getNome());

                                    System.out.println();
                                    System.out.println("O que deseja alterar?");
                                    System.out.println("1. Nome");
                                    System.out.println("2. Renda");
                                    System.out.println("3. Telefone");
                                    System.out.println("4. Endereço");
                                    System.out.println("5. Email");
                                    System.out.println("0. Cancelar");

                                    System.out.print("Escolha uma opção: ");
                                    int opcaoAlteracaoCliente = entrada.nextInt();
                                    entrada.nextLine();

                                    switch (opcaoAlteracaoCliente) {

                                        case 1:
                                            System.out.print("Novo nome: ");
                                            String novoNome = entrada.nextLine();

                                            clienteAlteracao.setNome(novoNome);

                                            System.out.println("Nome alterado com sucesso!");
                                            break;

                                        case 2:
                                            System.out.print("Nova renda: ");
                                            double novaRenda = entrada.nextDouble();
                                            entrada.nextLine();

                                            clienteAlteracao.setRenda(novaRenda);

                                            System.out.println("Renda alterada com sucesso!");
                                            break;

                                        case 3:
                                            System.out.print("Novo telefone: ");
                                            String novoTelefone = entrada.nextLine();

                                            clienteAlteracao.setTelefone(novoTelefone);

                                            System.out.println("Telefone alterado com sucesso!");
                                            break;

                                        case 4:
                                            System.out.print("Novo endereço: ");
                                            String novoEndereco = entrada.nextLine();

                                            clienteAlteracao.setEndereco(novoEndereco);

                                            System.out.println("Endereço alterado com sucesso!");
                                            break;

                                        case 5:
                                            System.out.print("Novo email: ");
                                            String novoEmail = entrada.nextLine();

                                            clienteAlteracao.setEmail(novoEmail);

                                            System.out.println("Email alterado com sucesso!");
                                            break;

                                        case 0:
                                            System.out.println("Alteração cancelada.");
                                            break;

                                        default:
                                            System.out.println("Opção inválida.");
                                            break;
                                    }


                                    break;

                                case 4:
                                    System.out.println(" _________________________");
                                    System.out.println("|     EXCLUIR CLIENTE      |");
                                    System.out.println("|_________________________|");

                                    System.out.print("Informe o CPF do cliente: ");
                                    String cpfExclusao = entrada.nextLine();

                                    Cliente clienteExcluir =
                                            verificaExistenciaCliente(cpfExclusao);

                                    if (clienteExcluir == null) {
                                        System.out.println("Cliente não encontrado.");
                                        break;
                                    }

                                    System.out.println();
                                    System.out.println("Cliente encontrado:");
                                    System.out.println("Nome: " + clienteExcluir.getNome());
                                    System.out.println("CPF: " + clienteExcluir.getCpf());

                                    System.out.print("Deseja realmente excluir este cliente? (S/N): ");
                                    String confirmaExclusaoCliente = entrada.nextLine();

                                    if (confirmaExclusaoCliente.equalsIgnoreCase("S")) {

                                        clientes.remove(clienteExcluir);

                                        System.out.println("Cliente excluído com sucesso!");

                                    } else {
                                        System.out.println("Exclusão cancelada.");
                                    }

                                    break;

                                case 0:
                                    System.out.println("Voltando ao menu principal.");
                                    break;

                                default:
                                    System.out.println("Opção inválida.");
                                    break;
                            }
                        } while (opcaoFuncionario != 0);

                        break;

                    case 4:
                        System.out.println(" _________________________");
                        System.out.println("|     ÁREA DO CLIENTE     |");
                        System.out.println("|_________________________|");
                        System.out.print("Informe seu CPF: ");
                        String cpfCliente = entrada.nextLine();

                        Cliente clienteLogado = verificaExistenciaCliente(cpfCliente);

                        if (clienteLogado == null) {
                            System.out.println("Cliente não encontrado.");
                            break;
                        }

                        System.out.println();
                        System.out.println("Bem-vindo(a), " + clienteLogado.getNome());

                        List<Conta> contasCliente = verificaContasCliente(clienteLogado);

                        if (contasCliente.size() == 0) {
                            System.out.println("Nenhuma conta encontrada para este cliente.");

                            break;
                        }

                        System.out.println();
                        System.out.println("Contas encontradas:");

                        for (int c = 0; c < contasCliente.size(); c++) {

                            Conta contaEncontrada = contasCliente.get(c);

                            System.out.println((c + 1) + ". " + contaEncontrada.getTipoConta());
                        }

                        System.out.print("Escolha a conta: ");
                        int escolhaConta = entrada.nextInt();
                        entrada.nextLine();

                        if (escolhaConta < 1 || escolhaConta > contasCliente.size()) {
                            System.out.println("Conta inválida.");
                            break;
                        }

                        Conta contaCliente = contasCliente.get(escolhaConta - 1);

                        int opcaoCliente;

                        do {


                        System.out.println(" _________________________");
                        System.out.println("|    OPERAÇÕES BANCÁRIAS  |");
                        System.out.println("|_________________________|");
                        System.out.println("|1. Consultar saldo       |");
                        System.out.println("|2. Depositar             |");
                        System.out.println("|3. Sacar                 |");
                        System.out.println("|0. Voltar                |");
                        System.out.println("|_________________________|");

                        System.out.print("Escolha uma opção: ");
                        opcaoCliente = entrada.nextInt();
                        entrada.nextLine();

                        switch (opcaoCliente) {

                            case 1:
                                System.out.println("Saldo: R$ " + contaCliente.getSaldo());
                                break;

                            case 2:
                                System.out.print("Valor do depósito: R$ ");
                                double deposito = entrada.nextDouble();
                                entrada.nextLine();

                                if (deposito > 0) {
                                    contaCliente.efetuarDeposito(deposito);

                                    System.out.println("Depósito realizado com sucesso!");
                                    System.out.println("Saldo atual: R$ " + contaCliente.getSaldo());
                                } else {
                                    System.out.println("Valor de depósito inválido.");
                                }

                                break;

                            case 3:
                                System.out.print("Valor do saque: R$ ");
                                double saque = entrada.nextDouble();
                                entrada.nextLine();

                                if (saque <= 0) {
                                    System.out.println("Valor de saque inválido.");
                                } else if (saque > contaCliente.getSaldo()) {
                                    System.out.println("Saldo insuficiente.");
                                } else {
                                    contaCliente.efetuarSaque(saque);

                                    System.out.println("Saque realizado com sucesso!");
                                    System.out.println("Saldo atual: R$ " + contaCliente.getSaldo());
                                }

                                break;

                            case 0:
                                System.out.println("Voltando ao menu principal.");
                                break;

                            default:
                                System.out.println("Opção inválida.");
                                break;
                        }
                        } while (opcaoCliente != 0);
                        break;

                    case 5:
                        System.out.println(" _________________________");
                        System.out.println("|    ÁREA DO GERENTE      |");
                        System.out.println("|_________________________|");

                        System.out.print("Usuário: ");
                        String usuarioGerente = entrada.nextLine();

                        System.out.print("Senha: ");
                        String senhaGerente = entrada.nextLine();

                        if (usuarioGerente.equals(gerente.getUsuario()) && senhaGerente.equals(gerente.getSenha())) {


                            System.out.println("\nLogin realizado com sucesso!");
                            System.out.println("Bem-vindo, " + gerente.getUsuario());

                            int opcaoGerente;

                            do {

                            System.out.println("\n ___________________________");
                            System.out.println("|      MENU DO GERENTE      |");
                            System.out.println("|___________________________|");
                            System.out.println("|1. Cadastrar funcionário   |");
                            System.out.println("|2. Consultar funcionário   |");
                            System.out.println("|3. Alterar funcionário     |");
                            System.out.println("|4. Excluir funcionário     |");
                            System.out.println("|0. Voltar                  |");
                            System.out.println("|___________________________|");

                            System.out.print("Escolha uma opção: ");
                            opcaoGerente = entrada.nextInt();
                            entrada.nextLine();




                                switch (opcaoGerente) {

                                    case 1:
                                        System.out.println(" ___________________________");
                                        System.out.println("|  CADASTRO DE FUNCIONÁRIO  |");
                                        System.out.println("|___________________________|");

                                        System.out.print("Usuário: ");
                                        String usuarioFuncionario = entrada.nextLine();

                                        System.out.print("Senha: ");
                                        String senhaFuncionario = entrada.nextLine();

                                        System.out.print("Código: ");
                                        int codigoFuncionario = entrada.nextInt();
                                        entrada.nextLine();

                                        System.out.print("Cargo: ");
                                        String cargoFuncionario = entrada.nextLine();

                                        Funcionario novoFuncionario = new Funcionario(usuarioFuncionario, senhaFuncionario, codigoFuncionario, cargoFuncionario);

                                        funcionarios.add(novoFuncionario);

                                        System.out.println("Funcionário cadastrado com sucesso!");
                                        break;

                                    case 2:
                                        System.out.println(" ___________________________");
                                        System.out.println("|  CONSULTAR FUNCIONÁRIO    |");
                                        System.out.println("|___________________________|");

                                        System.out.print("Informe o código do funcionário: ");
                                        int codigoConsulta = entrada.nextInt();
                                        entrada.nextLine();

                                        Funcionario funcionarioConsultado =
                                                verificaExistenciaFuncionario(codigoConsulta);

                                        if (funcionarioConsultado == null) {
                                            System.out.println("Funcionário não encontrado.");
                                        } else {
                                            System.out.println();
                                            System.out.println("Funcionário encontrado:");
                                            System.out.println("Usuário: " + funcionarioConsultado.getUsuario());
                                            System.out.println("Código: " + funcionarioConsultado.getCodigo());
                                            System.out.println("Cargo: " + funcionarioConsultado.getCargo());
                                        }

                                        break;

                                    case 3:
                                        System.out.println(" ___________________________");
                                        System.out.println("|   ALTERAR FUNCIONÁRIO     |");
                                        System.out.println("|___________________________|");

                                        System.out.print("Informe o código do funcionário: ");
                                        int codigoAlteracao = entrada.nextInt();
                                        entrada.nextLine();

                                        Funcionario funcionarioAlteracao =
                                                verificaExistenciaFuncionario(codigoAlteracao);

                                        if (funcionarioAlteracao == null) {
                                            System.out.println("Funcionário não encontrado.");
                                            break;
                                        }

                                        System.out.println();
                                        System.out.println("Funcionário: " + funcionarioAlteracao.getUsuario());
                                        System.out.println("Cargo: " + funcionarioAlteracao.getCargo());

                                        System.out.println();
                                        System.out.println("O que deseja alterar?");
                                        System.out.println("1. Usuário");
                                        System.out.println("2. Senha");
                                        System.out.println("3. Cargo");
                                        System.out.println("0. Cancelar");

                                        System.out.print("Escolha uma opção: ");
                                        int opcaoAlteracao = entrada.nextInt();
                                        entrada.nextLine();

                                        switch (opcaoAlteracao) {

                                            case 1:
                                                System.out.print("Novo usuário: ");
                                                String novoUsuario = entrada.nextLine();

                                                funcionarioAlteracao.setUsuario(novoUsuario);

                                                System.out.println("Usuário alterado com sucesso!");
                                                break;

                                            case 2:
                                                System.out.print("Nova senha: ");
                                                String novaSenha = entrada.nextLine();

                                                funcionarioAlteracao.setSenha(novaSenha);

                                                System.out.println("Senha alterada com sucesso!");
                                                break;

                                            case 3:
                                                System.out.print("Novo cargo: ");
                                                String novoCargo = entrada.nextLine();

                                                funcionarioAlteracao.setCargo(novoCargo);

                                                System.out.println("Cargo alterado com sucesso!");
                                                break;

                                            case 0:
                                                System.out.println("Alteração cancelada.");
                                                break;

                                            default:
                                                System.out.println("Opção inválida.");
                                                break;
                                        }
                                        break;

                                    case 4:
                                        System.out.println(" ___________________________");
                                        System.out.println("|   EXCLUIR FUNCIONÁRIO     |");
                                        System.out.println("|___________________________|");

                                        System.out.print("Informe o código do funcionário: ");
                                        int codigoExclusao = entrada.nextInt();
                                        entrada.nextLine();

                                        Funcionario funcionarioExcluir =
                                                verificaExistenciaFuncionario(codigoExclusao);

                                        if (funcionarioExcluir == null) {
                                            System.out.println("Funcionário não encontrado.");
                                            break;
                                        }

                                        System.out.println();
                                        System.out.println("Funcionário encontrado:");
                                        System.out.println("Usuário: " + funcionarioExcluir.getUsuario());
                                        System.out.println("Código: " + funcionarioExcluir.getCodigo());
                                        System.out.println("Cargo: " + funcionarioExcluir.getCargo());

                                        System.out.print("Deseja realmente excluir este funcionário? (S/N): ");
                                        String confirmaExclusao = entrada.nextLine();

                                        if (confirmaExclusao.equalsIgnoreCase("S")) {

                                            funcionarios.remove(funcionarioExcluir);

                                            System.out.println("Funcionário excluído com sucesso!");

                                        } else {
                                            System.out.println("Exclusão cancelada.");
                                        }

                                        break;

                                    case 0:
                                        System.out.println("Voltando ao menu principal.");
                                        break;

                                    default:
                                        System.out.println("Opção inválida.");
                                        break;
                                }
                            }while (opcaoGerente != 0);


                            } else{
                                System.out.println("Usuário ou senha incorretos.");
                            }



                            break;

                    case 0:
                        System.out.println("Banco ES encerrado.");
                        break;

                    default:
                        System.out.println("Opção inválida.");
                }
            } while (opcao != 0);

        }

    private static List<Conta> verificaContasCliente(Cliente cliente) {

        List<Conta> contasCliente = new ArrayList<>();

        for (int c = 0; c < contas.size(); c++) {

            Conta contaComparacao = contas.get(c);

            if (contaComparacao.getTitular().equals(cliente)) {
                contasCliente.add(contaComparacao);
            }
        }

        return contasCliente;
    }


    private static Cliente verificaExistenciaCliente (String cpf){
        for (int c = 0; c < clientes.size(); c++) {
            Cliente comparacaoCliente = clientes.get(c);
            if (comparacaoCliente.getCpf().equals(cpf)) {
                return comparacaoCliente;
            }
        }
        return null;
    }
    private static Funcionario verificaExistenciaFuncionario(int codigo) {

        for (int f = 0; f < funcionarios.size(); f++) {

            Funcionario funcionarioComparacao = funcionarios.get(f);

            if (funcionarioComparacao.getCodigo() == codigo) {
                return funcionarioComparacao;
            }
        }

        return null;
    }

    // Metodo que pede os dados ao usuário
    public static Cliente solicitarDados (String cpf){
        Scanner entrada = new Scanner(System.in);
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        System.out.print("Nome: ");
        String nome = entrada.nextLine();
        if (cpf == null) {
            System.out.print("CPF: ");
            cpf = entrada.nextLine();
        } else {
            System.out.println("CPF: " + cpf);
        }
            // DateTimeFormatter é a classe que define o "formato" (padrão) em que uma data
            // vai ser lida ou escrita como texto.
            // O metodo estático "ofPattern(...)" recebe uma String descrevendo o padrão desejado:
            //   dd -> dia com 2 dígitos (ex: 05)
            //   MM -> mês com 2 dígitos (ex: 03)
            //   yyyy -> ano com 4 dígitos (ex: 1998)
            // Ou seja, esse formatador vai entender/exigir datas no formato "dia/mes/ano".
            // Criamos o objeto "formato" para poder usar depois, ao converter texto em LocalDate.
        System.out.print("Data de nascimento (dd/MM/yyyy): ");
        LocalDate dataNascimento = LocalDate.parse(entrada.nextLine(), formato);
        System.out.print("Renda: ");
        double renda = Double.parseDouble(entrada.nextLine());
        System.out.print("Telefone: ");
        String telefone = entrada.nextLine();
        System.out.print("Endereço: ");
        String endereco = entrada.nextLine();
        System.out.print("Nome do pai: ");
        String nomePai = entrada.nextLine();
        System.out.print("Nome da mãe: ");
        String nomeMae = entrada.nextLine();
        System.out.print("Email: ");
        String email = entrada.nextLine();

        return new Cliente(nome, cpf, dataNascimento, renda, telefone, endereco, nomePai, nomeMae, email);
    }
}
