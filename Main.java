import java.util.Scanner;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        //Scanner para poder usar o teclado
        Scanner entrada = new Scanner(System.in);

        // Declaração das Classes na main
        Cliente cadastro = new Cliente();
        Conta conta = new Conta();
        Funcionario funcionario = new Funcionario();
        Administrador adm = new Administrador();

        //Enunciando ---------------------------------- Não está pronto
        System.out.println("Bem vindo ao Banco ES:");
        System.out.println("______________________");
        System.out.println("|                     |");
        System.out.println("|        MENU:        |");
        System.out.println("|_____________________|");
        System.out.println("|                     |");
        System.out.println("|1. Conta             |");
        System.out.println("|2. Cadastro          |");
        System.out.println("|3. Funcionario       |");
        System.out.println("|4. Operação          |");
        System.out.println("|5. Administrador     |");
        System.out.println("|_____________________|");

        System.out.print("Digite o número da opção desejada: ");


        for (int i = 1; i <= 5; i++) {
            System.out.println("i = " + i);
        }
    }
}