package AplicacaoCasoUso;

import Pessoas.Cliente;
import InterfaceGrafica.SistemaBanco;


public class ExcluirCliente {

    public void executar(String cpf) {
        validarCpf(cpf);
        Cliente cliente = SistemaBanco.buscarClientePorCpf(cpf);

        if (cliente == null){
            throw new IllegalArgumentException("Cliente não encontrado.");
        }
        SistemaBanco.excluirCliente(cliente);
    }

    private void validarCpf(String cpf) {
        if (cpf == null || cpf.isBlank()) {
            throw new IllegalArgumentException("CPF não informado.");
        }
    }
}
