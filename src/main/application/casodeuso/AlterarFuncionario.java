package application.casodeuso;

import application.repositories.FuncionarioRepository;
import domain.pessoas.Funcionario;

public class AlterarFuncionario {

    private final FuncionarioRepository funcionarioRepo;

    public AlterarFuncionario(FuncionarioRepository funcionarioRepo) {
        this.funcionarioRepo = funcionarioRepo;
    }

    public void executar(Funcionario funcionario) {

        if (funcionario == null) {
            throw new IllegalArgumentException("Funcionário não informado.");
        }

        int codigo = funcionario.getCodigo();

        ValidadorCodigo.validarCodigo(codigo);

        Funcionario funcionarioExistente = funcionarioRepo.buscarPorCodigo(codigo);

        if (funcionarioExistente == null) {
            throw new IllegalArgumentException("Funcionário não encontrado.");
        }

        if (funcionario.getUsuario() == null || funcionario.getUsuario().isBlank()) {
            throw new IllegalArgumentException("Usuário não informado.");
        }

        if (funcionario.getSenha() == null || funcionario.getSenha().length() < 8) {
            throw new IllegalArgumentException("A senha deve ter no mínimo 8 caracteres.");
        }

        if (funcionario.getCargo() == null || funcionario.getCargo().isBlank()) {
            throw new IllegalArgumentException("Cargo não informado.");
        }

        funcionarioRepo.atualizar(funcionario);
    }
}