package AplicacaoCasoUso;

import InterfaceGrafica.SistemaBanco;
import Pessoas.Cliente;

public class CadastrarCliente {

    public void executar(String cpf) {
        Cliente clienteExistente = SistemaBanco.buscarClientePorCpf(cpf);

        if (clienteExistente != null) {
            throw new IllegalArgumentException("Cliente já cadastrado!");
        }
    }
}
