package InterfaceGrafica;

import javax.swing.*;
import java.awt.*;

public class BotaoArredondado extends JButton {

    public BotaoArredondado(String texto) {

        super(texto);

        setContentAreaFilled(false);
        setFocusPainted(false);
        setBorderPainted(false);
        setOpaque(false);
    }

    @Override
    protected void paintComponent(Graphics g) {

        Graphics2D g2 = (Graphics2D) g.create();

        // Deixa o desenho mais suave
        g2.setRenderingHint(
                RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON
        );

        // Cor do botão
        g2.setColor(getBackground());

        // Desenha o botão arredondado
        g2.fillRoundRect(
                0,
                0,
                getWidth(),
                getHeight(),
                25,
                25
        );

        g2.dispose();

        // Desenha o texto do botão
        super.paintComponent(g);
    }
}