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

                        break;

                    case 5:
                        Gerente adm = new Gerente(usuario, senha, codigo);
                        //chamar o metodo
                        break;

                    case 0:
                        System.out.println("Banco ES encerrado.");
                        break;

                    default:
                        System.out.println("Opção inválida.");
                }
            } while (opcao != 0);

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
