import javax.swing.*;
import java.awt.*;

//Código de exemplo apenas para teste inicial
public class TelaPrincipal extends JFrame {

    public TelaPrincipal() {
        setSize(900, 1500);
        setTitle("Banco ES");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }
    public static void main(String[] args) {
        TelaPrincipal tela = new TelaPrincipal();
        tela.setVisible(true);

    }
}
