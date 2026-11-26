package presentation.swing;

import application.casodeuso.autenticacao.*;
import application.casodeuso.cliente.*;
import application.casodeuso.conta.*;
import application.casodeuso.funcionario.*;
import application.repositories.*;
import infrastructure.persistence.*;

/** Contexto com os casos de uso já existentes, montado na inicialização. */
public class AplicacaoBanco {
    public final CadastrarCliente cadastrarCliente;
    public final ConsultarCliente consultarCliente;
    public final AlterarCliente alterarCliente;
    public final ExcluirCliente excluirCliente;
    public final CadastrarConta cadastrarConta;
    public final ConsultarContasCliente consultarContasCliente;
    public final Depositar depositar;
    public final Sacar sacar;
    public final AutenticarCliente autenticarCliente;
    public final CadastrarFuncionario cadastrarFuncionario;
    public final ConsultarFuncionario consultarFuncionario;
    public final AlterarFuncionario alterarFuncionario;
    public final ExcluirFuncionario excluirFuncionario;
    public final AutenticarFuncionario autenticarFuncionario;
    public final AutenticarGerente autenticarGerente;

    public AplicacaoBanco() {
        ClienteRepository clientes = new ClienteRepositoryJDBC();
        ContaRepository contas = new ContaRepositoryJDBC();
        FuncionarioRepository funcionarios = new FuncionarioRepositoryJDBC();
        cadastrarCliente = new CadastrarCliente(clientes);
        consultarCliente = new ConsultarCliente(clientes);
        alterarCliente = new AlterarCliente(clientes);
        excluirCliente = new ExcluirCliente(clientes);
        cadastrarConta = new CadastrarConta(contas);
        consultarContasCliente = new ConsultarContasCliente(clientes, contas);
        depositar = new Depositar(contas);
        sacar = new Sacar(contas);
        autenticarCliente = new AutenticarCliente(clientes, contas);
        cadastrarFuncionario = new CadastrarFuncionario(funcionarios);
        consultarFuncionario = new ConsultarFuncionario(funcionarios);
        alterarFuncionario = new AlterarFuncionario(funcionarios);
        excluirFuncionario = new ExcluirFuncionario(funcionarios);
        autenticarFuncionario = new AutenticarFuncionario(funcionarios);
        autenticarGerente = new AutenticarGerente(funcionarios);
    }
}
