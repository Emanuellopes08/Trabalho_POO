package application.casodeuso.autenticacao;

import application.repositories.FuncionarioRepository;
import domain.pessoas.Funcionario;

public class AutenticarGerente {

    private final AutenticarFuncionario autenticarFuncionario;

    public AutenticarGerente(FuncionarioRepository funcionarioRepo) {
        this.autenticarFuncionario = new AutenticarFuncionario(funcionarioRepo);
    }

    public Funcionario executar(int codigo, String usuario, String senha) {
        Funcionario funcionario = autenticarFuncionario.executar(codigo, usuario, senha);

        if (!"Pessoas.Gerente".equals(funcionario.getCargo())) {
            throw new IllegalArgumentException("Acesso não autorizado.");
        }
        return funcionario;
    }
}