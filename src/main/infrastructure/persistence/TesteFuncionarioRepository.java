package infrastructure.persistence;

import domain.pessoas.Funcionario;

import java.util.List;

public class TesteFuncionarioRepository {

    public static void main(String[] args) {

        FuncionarioRepositoryJDBC repository =
                new FuncionarioRepositoryJDBC();

        // Funcionário fictício para os testes
        Funcionario funcionario = new Funcionario(
                "usuario_teste",
                "senha123",
                999,
                "Gerente"
        );

        try {

            // 1. SALVAR FUNCIONÁRIO
            repository.salvar(funcionario);

            System.out.println("Funcionário salvo com sucesso!");

            // 2. BUSCAR POR CÓDIGO
            Funcionario encontrado =
                    repository.buscarPorCodigo(999);

            System.out.println("\nBusca por código:");

            if (encontrado != null) {
                System.out.println("Funcionário encontrado!");
                System.out.println("Código: "
                        + encontrado.getCodigo());
                System.out.println("Usuário: "
                        + encontrado.getUsuario());
                System.out.println("Cargo: "
                        + encontrado.getCargo());
            }

            // 3. BUSCAR POR USUÁRIO
            Funcionario encontradoPorUsuario =
                    repository.buscarPorUsuario("usuario_teste");

            System.out.println("\nBusca por usuário:");

            if (encontradoPorUsuario != null) {
                System.out.println("Funcionário encontrado!");
                System.out.println("Usuário: "
                        + encontradoPorUsuario.getUsuario());
            }

            // 4. LISTAR TODOS
            List<Funcionario> funcionarios =
                    repository.listarTodos();

            System.out.println("\nFuncionários cadastrados:");

            for (Funcionario f : funcionarios) {
                System.out.println(
                        "Código: " + f.getCodigo()
                        + " | Usuário: " + f.getUsuario()
                        + " | Cargo: " + f.getCargo()
                );
            }

            // 5. ATUALIZAR FUNCIONÁRIO
            funcionario.setUsuario("usuario_atualizado");
            funcionario.setSenha("novaSenha123");
            funcionario.setCargo("Administrador");

            repository.atualizar(funcionario);

            System.out.println("\nFuncionário atualizado!");

            Funcionario atualizado =
                    repository.buscarPorCodigo(999);

            System.out.println("Novo usuário: "
                    + atualizado.getUsuario());
            System.out.println("Novo cargo: "
                    + atualizado.getCargo());

            // 6. EXCLUIR FUNCIONÁRIO
            repository.excluir(999);

            System.out.println("\nFuncionário excluído com sucesso!");

        } catch (Exception e) {

            System.out.println("\nErro durante o teste:");
            e.printStackTrace();

        } finally {

            // LIMPEZA DO REGISTRO DE TESTE
            try {
                Funcionario existente =
                        repository.buscarPorCodigo(999);

                if (existente != null) {
                    repository.excluir(999);
                    System.out.println(
                            "\nRegistro de teste removido!"
                    );
                }

            } catch (Exception e) {
                System.out.println(
                        "\nNão foi possível limpar o registro de teste."
                );
                e.printStackTrace();
            }
        }
    }
}