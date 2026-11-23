import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import Contas.*;

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
