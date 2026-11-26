
package presentation.swing;

import application.casodeuso.autenticacao.*;
import application.casodeuso.cliente.*;
import application.casodeuso.conta.*;
import application.casodeuso.funcionario.*;

/**
 * Disponibiliza os casos de uso do sistema Banco ES para a camada
 * de apresentação.
 *
 * Recebe os casos de uso já instanciados por meio do construtor,
 * mantendo a responsabilidade de criação das dependências fora
 * desta classe.
 *
 * Dessa forma, a classe não depende diretamente das implementações
 * de infraestrutura, contribuindo para a separação de responsabilidades.
 */
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

    /**
     * Inicializa o contexto da aplicação recebendo todos os casos
     * de uso necessários para a camada de apresentação.
     *
     * @param cadastrarCliente caso de uso para cadastro de clientes
     * @param consultarCliente caso de uso para consulta de clientes
     * @param alterarCliente caso de uso para alteração de clientes
     * @param excluirCliente caso de uso para exclusão de clientes
     * @param cadastrarConta caso de uso para cadastro de contas
     * @param consultarContasCliente caso de uso para consulta das contas de um cliente
     * @param depositar caso de uso para depósitos
     * @param sacar caso de uso para saques
     * @param autenticarCliente caso de uso para autenticação de clientes
     * @param cadastrarFuncionario caso de uso para cadastro de funcionários
     * @param consultarFuncionario caso de uso para consulta de funcionários
     * @param alterarFuncionario caso de uso para alteração de funcionários
     * @param excluirFuncionario caso de uso para exclusão de funcionários
     * @param autenticarFuncionario caso de uso para autenticação de funcionários
     * @param autenticarGerente caso de uso para autenticação de gerentes
     */
    public AplicacaoBanco(
            CadastrarCliente cadastrarCliente,
            ConsultarCliente consultarCliente,
            AlterarCliente alterarCliente,
            ExcluirCliente excluirCliente,
            CadastrarConta cadastrarConta,
            ConsultarContasCliente consultarContasCliente,
            Depositar depositar,
            Sacar sacar,
            AutenticarCliente autenticarCliente,
            CadastrarFuncionario cadastrarFuncionario,
            ConsultarFuncionario consultarFuncionario,
            AlterarFuncionario alterarFuncionario,
            ExcluirFuncionario excluirFuncionario,
            AutenticarFuncionario autenticarFuncionario,
            AutenticarGerente autenticarGerente) {

        this.cadastrarCliente = cadastrarCliente;
        this.consultarCliente = consultarCliente;
        this.alterarCliente = alterarCliente;
        this.excluirCliente = excluirCliente;
        this.cadastrarConta = cadastrarConta;
        this.consultarContasCliente = consultarContasCliente;
        this.depositar = depositar;
        this.sacar = sacar;
        this.autenticarCliente = autenticarCliente;
        this.cadastrarFuncionario = cadastrarFuncionario;
        this.consultarFuncionario = consultarFuncionario;
        this.alterarFuncionario = alterarFuncionario;
        this.excluirFuncionario = excluirFuncionario;
        this.autenticarFuncionario = autenticarFuncionario;
        this.autenticarGerente = autenticarGerente;
    }
}
