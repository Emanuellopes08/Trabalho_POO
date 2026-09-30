import javax.swing.*;
import java.awt.*;

public class TelaPrincipal extends JFrame {

    public TelaPrincipal() {

        setSize(900, 600);
        setTitle("Banco ES");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new FlowLayout());
        JLabel titulo = new JLabel("Bem-vindo ao Banco ES");
        add(titulo);
    }
}