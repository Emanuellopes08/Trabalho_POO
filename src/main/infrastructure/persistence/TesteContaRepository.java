package infrastructure.persistence;

import domain.contas.Conta;
import domain.contas.ContaConjunta;
import domain.pessoas.Cliente;

import java.time.LocalDate;
import java.util.List;

public class TesteContaRepository {

    public static void main(String[] args) {

        ClienteRepositoryJDBC clienteRepository =
                new ClienteRepositoryJDBC();

        ContaRepositoryJDBC contaRepository =
                new ContaRepositoryJDBC();

        // CPFs fictícios utilizados somente no teste
        Cliente titular = new Cliente(
                "Cliente Teste 1",
                "11111111111",
                LocalDate.of(2000, 1, 10),
                2500.00,
                "11999999999",
                "Rua Teste, 100",
                "Pai Teste",
                "Mae Teste",
                "teste1@email.com"
        );

        Cliente cotitular = new Cliente(
                "Cliente Teste 2",
                "22222222222",
                LocalDate.of(2001, 5, 20),
                3000.00,
                "11888888888",
                "Rua Teste, 200",
                "Pai Teste 2",
                "Mae Teste 2",
                "teste2@email.com"
        );

        try {
            // 1. CADASTRAR OS CLIENTES
            clienteRepository.salvar(titular);
            clienteRepository.salvar(cotitular);

            System.out.println("Clientes cadastrados!");

            // 2. SALVAR UMA CONTA CONJUNTA
            ContaConjunta conta = new ContaConjunta(
                    titular,
                    cotitular
            );

            conta.efetuarDeposito(1500.00);

            contaRepository.salvar(conta);

            System.out.println("\nConta salva com sucesso!");
            System.out.println("ID gerado: " + conta.getId());

            // 3. BUSCAR CONTA POR ID
            Conta contaEncontrada =
                    contaRepository.buscarPorId(conta.getId());

            System.out.println("\nBusca por ID:");

            if (contaEncontrada != null) {
                System.out.println("Conta encontrada!");
                System.out.println("ID: " + contaEncontrada.getId());
                System.out.println("Tipo: "
                        + contaEncontrada.getTipoConta());
                System.out.println("Titular: "
                        + contaEncontrada.getTitular().getNome());
                System.out.println("Saldo: "
                        + contaEncontrada.getSaldo());
            }

            // 4. BUSCAR CONTAS PELO CPF DO TITULAR
            List<Conta> contasDoTitular =
                    contaRepository.buscarPorCpfCliente(
                            titular.getCpf()
                    );

            System.out.println("\nContas encontradas pelo CPF do titular: "
                    + contasDoTitular.size());

            // 5. BUSCAR CONTAS PELO CPF DO COTITULAR
            List<Conta> contasDoCotitular =
                    contaRepository.buscarPorCpfCliente(
                            cotitular.getCpf()
                    );

            System.out.println("Contas encontradas pelo CPF do cotitular: "
                    + contasDoCotitular.size());

            // 6. LISTAR TODAS AS CONTAS
            List<Conta> todasAsContas =
                    contaRepository.listarTodas();

            System.out.println("\nTodas as contas cadastradas:");

            for (Conta c : todasAsContas) {
                System.out.println(
                        "ID: " + c.getId()
                        + " | Tipo: " + c.getTipoConta()
                        + " | Titular: " + c.getTitular().getNome()
                        + " | Saldo: " + c.getSaldo()
                );
            }

            // 7. ATUALIZAR A CONTA
            conta.efetuarDeposito(500.00);

            contaRepository.atualizar(conta);

            System.out.println("\nConta atualizada!");
            System.out.println("Novo saldo: "
                    + contaRepository.buscarPorId(
                            conta.getId()
                    ).getSaldo());

            // 8. EXCLUIR A CONTA
            contaRepository.excluir(conta.getId());

            System.out.println("\nConta excluída com sucesso!");

        } catch (Exception e) {
            System.out.println("\nErro durante o teste:");
            e.printStackTrace();

        } finally {

            // LIMPEZA DOS CLIENTES DE TESTE
            try {
                clienteRepository.excluir(titular.getCpf());
                clienteRepository.excluir(cotitular.getCpf());

                System.out.println(
                        "\nClientes de teste excluídos!"
                );

            } catch (Exception e) {
                System.out.println(
                        "\nNão foi possível excluir os clientes de teste."
                );
                e.printStackTrace();
            }
        }
    }
}
