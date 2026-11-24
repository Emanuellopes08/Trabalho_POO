package application.casodeuso;

import domain.pessoas.Cliente;
import presentation.swing.SistemaBanco;

public class CadastrarCliente {

    public void executar(String cpf) {
        Cliente clienteExistente = SistemaBanco.buscarClientePorCpf(cpf);

        if (clienteExistente != null) {
            throw new IllegalArgumentException("Cliente já cadastrado!");
        }
    }
}
