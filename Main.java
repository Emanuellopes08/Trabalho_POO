import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    static List<Cliente> clientes = new ArrayList<>();
    static Scanner entrada = new Scanner(System.in);

    public static void main(String[] args) {


        do {


            System.out.println("Bem vindo ao Banco ES:");
            System.out.println(" ___________________________");
            System.out.println("|                           |");
            System.out.println("|        MENU:              |");
            System.out.println("|___________________________|");
            System.out.println("|                           |");
            System.out.println("|1. Cadastro do Cliente     |");
            System.out.println("|2. Abrir Conta             |");
            System.out.println("|___________________________|");

            System.out.print("Digite o número da opção desejada: ");
            int opcao = entrada.nextInt();
            entrada.nextLine();

            switch (opcao) {
                case 1:
                    //Para fazer o cadastro do cliente
                    clientes.add(solicitarDados()); //add joga para lista declarada na linha 12
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
                        System.out.println("Cliente não cadastrado.");
                        System.out.println("Realize o cadastro:");

                        cliente = solicitarDados();
                        clientes.add(cliente);
                    }
                    Conta conta;


                    switch (opcaoTipoConta) {
                        case 1:
                            conta = new ContaPoupanca(cliente);
                            break;

                        case 2:
                            conta = new ContaCorrente(cliente);
                            break;

                        case 3:
                            conta = new ContaConjunta(cliente);
                            break;

                        case 4:
                            conta = new ContaPJ(cliente);
                            break;

                        default:
                            System.out.println("Tipo de conta inválido.");
                            break;
                    }

                    break;

                case 3:
                    //Funcionário
                    break;

                case 4:
                    //Operação
                    System.out.println(" _______________________");
                    System.out.println("|        MENU:          |");
                    System.out.println("|_______________________|");
                    System.out.println("|                       |");
                    System.out.println("|1. Conta Poupança      |");
                    System.out.println("|2. Conta Corrente      |");
                    System.out.println("|3. Conta Conjunta      |");
                    System.out.println("|4. Conta Pessoa Jurica |");
                    System.out.println("|_______________________|");
                    System.out.print("Escolha o tipo de Conta: ");
                    int tipoConta = entrada.nextInt();

                    break;

                case 5:
                    Gerente adm = new Gerente(nome, senha, codigo);
                    //chamar o metodo
                    break;
            }
        }
    }


    private static Cliente verificaExistenciaCliente(String cpf) {
        for (int c = 0; c< clientes.size(); c++){
            Cliente comparacaoCliente = clientes.get(c);
            if (comparacaoCliente.getCpf().equals(cpf)){
                return comparacaoCliente;
            }
        }
        return null;
    }

    // Metodo que pede os dados ao usuário
    public static Cliente solicitarDados() {
        Scanner entrada= new Scanner(System.in);
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        System.out.print("Nome: ");
        String nome = entrada.nextLine();
        System.out.print("CPF: ");
        String cpf = entrada.nextLine();
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