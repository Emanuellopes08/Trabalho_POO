import org.junit.jupiter.api.Test;

import domain.contas.*;

import static org.junit.jupiter.api.Assertions.*;

public class ContaLspTest {

    @Test
    void deveSomarDoisNumeros() {
        int resultado = 2 + 3;

        assertEquals(5, resultado);
    }
    @Test
    void testarRendimentoContaPoupanca() {
        ContaPoupanca conta = new ContaPoupanca(null);

        assertEquals(0.5, conta.consultarRendimento(), 0.001);
    }

}
