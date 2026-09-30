import javax.swing.*;
import java.awt.*;

public class TelaPrincipal extends JFrame {

    public TelaPrincipal() {

        setSize(900, 700);
        setTitle("Banco ES");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
        JLabel titulo = new JLabel("Bem-vindo ao Banco ES");
        add(titulo);
        JButton btnCliente = new JButton("Cadastrar Cliente");
        add(btnCliente);
    }
}