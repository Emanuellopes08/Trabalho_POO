import java.util.ArrayList;
import java.util.List;

public class SistemaBanco {

    // Dados do banco
    private static final List<Cliente> clientes = new ArrayList<>();
    private static final List<Conta> contas = new ArrayList<>();
    private static final List<Funcionario> funcionarios = new ArrayList<>();


// GERENTE PRINCIPAL
    private static final Gerente gerente =
            new Gerente("Dorivaldo Silva", "Dori123456$", 1);


// FUNCIONÁRIO TEMPORÁRIO PARA TESTE
    private static final Funcionario funcionarioTeste =
            new Funcionario("Emanuel", "1234", 10, "Funcionário");


// ADICIONA O FUNCIONÁRIO DE TESTE AO INICIAR O SISTEMA
    static {
        funcionarios.add(funcionarioTeste);
    }


// ADICIONA CLIENTE
    public static void adicionarCliente(Cliente cliente) {
        clientes.add(cliente);
    }


// ADICIONA CONTA
    public static void adicionarConta(Conta conta) {
        contas.add(conta);
    }


// ADICIONA FUNCIONÁRIO
    public static void adicionarFuncionario(Funcionario funcionario) {
        funcionarios.add(funcionario);
    }


// PROCURA CLIENTE PELO CPF
    public static Cliente buscarClientePorCpf(String cpf) {

        for (Cliente cliente : clientes) {

            if (cliente.getCpf().equals(cpf)) {
                return cliente;
            }
        }

        return null;
    }


// PROCURA FUNCIONÁRIO PELO CÓDIGO
    public static Funcionario buscarFuncionarioPorCodigo(int codigo) {

        for (Funcionario funcionario : funcionarios) {

            if (funcionario.getCodigo() == codigo) {
                return funcionario;
            }
        }

        return null;
    }


// PROCURA AS CONTAS DE UM CLIENTE

    public static List<Conta> buscarContasDoCliente(Cliente cliente) {

        List<Conta> contasCliente = new ArrayList<>();

        for (Conta conta : contas) {

            if (conta.getTitular().equals(cliente)) {
                contasCliente.add(conta);
            }
        }

        return contasCliente;
    }

    /////////////////////////////////////////////////////////////////////
    // PROCURA CONTA PJ PELO CNPJ
    /////////////////////////////////////////////////////////////////////

    public static ContaPJ buscarContaPjPorCnpj(String cnpj) {

        for (Conta conta : contas) {

            if (conta instanceof ContaPJ) {

                ContaPJ contaPJ = (ContaPJ) conta;

                if (contaPJ.getCnpj().equals(cnpj)) {
                    return contaPJ;
                }
            }
        }

        return null;
    }

// RETORNA O GERENTE

    public static Gerente getGerente() {
        return gerente;
    }
}