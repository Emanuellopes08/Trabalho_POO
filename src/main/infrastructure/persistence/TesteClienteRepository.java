
package infrastructure.persistence;

import domain.pessoas.Cliente;
import java.time.LocalDate;
import java.util.List;

public class TesteClienteRepository {

    public static void main(String[] args) {
        ClienteRepositoryJDBC repositorio = new ClienteRepositoryJDBC();

        try {
            // 1. Criar um cliente para teste
            Cliente cliente = new Cliente(
                    "Cliente Teste",
                    "12345678900",
                    LocalDate.of(2000, 5, 15),
                    2500.00,
                    "51999999999",
                    "Rua de Teste",
                    "Nome do Pai",
                    "Nome da Mãe",
                    "teste@email.com"
            );

            // 2. Salvar cliente
            repositorio.salvar(cliente);
            System.out.println("Cliente salvo com sucesso!");

            // 3. Buscar cliente pelo CPF
            Cliente encontrado = repositorio.buscarPorCpf("12345678900");
            System.out.println("\nCliente encontrado:");
            System.out.println(encontrado);

            // 4. Listar todos os clientes
            List<Cliente> clientes = repositorio.listarTodos();
            System.out.println("\nClientes cadastrados:");
            for (Cliente c : clientes) {
                System.out.println(c);
            }

            // 5. Atualizar cliente
            Cliente atualizado = new Cliente(
                    "Cliente Atualizado",
                    "12345678900",
                    LocalDate.of(2000, 5, 15),
                    3000.00,
                    "51988888888",
                    "Novo Endereço",
                    "Nome do Pai",
                    "Nome da Mãe",
                    "atualizado@email.com"
            );

            repositorio.atualizar(atualizado);
            System.out.println("\nCliente atualizado com sucesso!");

            // 6. Excluir cliente
            repositorio.excluir("12345678900");
            System.out.println("Cliente excluído com sucesso!");

        } catch (Exception e) {
            System.out.println("Erro durante o teste:");
            e.printStackTrace();
        }
    }
}