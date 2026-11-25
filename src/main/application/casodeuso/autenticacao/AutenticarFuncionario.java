package application.casodeuso.autenticacao;

import application.repositories.FuncionarioRepository;
import application.validacao.ValidadorFuncionario;
import domain.pessoas.Funcionario;

public class AutenticarFuncionario {

    private final FuncionarioRepository funcionarioRepo;

    public AutenticarFuncionario(FuncionarioRepository funcionarioRepo) {
        this.funcionarioRepo = funcionarioRepo;
    }

    public Funcionario executar(int codigo, String usuario, String senha) {

        ValidadorFuncionario.validarCodigo(codigo);

        if (usuario == null || usuario.isBlank()) {
            throw new IllegalArgumentException("Usuário não informado.");
        }

        if (senha == null || senha.isBlank()) {
            throw new IllegalArgumentException("Senha não informada.");
        }

        Funcionario funcionario = funcionarioRepo.buscarPorCodigo(codigo);

        if (funcionario == null || !usuario.equals(funcionario.getUsuario()) || !senha.equals(funcionario.getSenha())) {
            throw new IllegalArgumentException("Credenciais inválidas.");
        }
        return funcionario;
    }
}