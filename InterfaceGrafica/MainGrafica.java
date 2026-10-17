package InterfaceGrafica;

import javax.swing.SwingUtilities;
public class MainGrafica {

    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> {

            TelaPrincipal tela = new TelaPrincipal();
            tela.setVisible(true);

        });
    }
}