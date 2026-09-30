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
                        //Funcionário
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

                        System.out.println(" _________________________");
                        System.out.println("|    OPERAÇÕES BANCÁRIAS  |");
                        System.out.println("|_________________________|");
                        System.out.println("|1. Consultar saldo       |");
                        System.out.println("|2. Depositar             |");
                        System.out.println("|3. Sacar                 |");
                        System.out.println("|0. Voltar                |");
                        System.out.println("|_________________________|");

                        System.out.print("Escolha uma opção: ");
                        int opcaoCliente = entrada.nextInt();
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
                            int opcaoGerente = entrada.nextInt();
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
                                    break;

                                case 3:
                                    break;

                                case 4:
                                    break;

                                case 0:
                                    System.out.println("Voltando ao menu principal.");
                                    break;

                                default:
                                    System.out.println("Opção inválida.");
                                    break;
                            }

                        } else {
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
