package presentation.swing;

import javax.swing.*;
import java.awt.*;

/**
 * Botão arredondado reutilizável da interface do Banco ES.
 */
/**
 * Botão personalizado com aparência arredondada para uso na interface gráfica.
 */
public class BotaoArredondado extends JButton {
    /**
     * Cria um botão estilizado com o texto informado.
     
 * @param texto texto exibido no botão.
 */

    public BotaoArredondado(String texto) {
        super(texto);
        setContentAreaFilled(false);
        setFocusPainted(false);
        setBorderPainted(false);
        setOpaque(false);
        setMargin(new Insets(10, 20, 10, 20));
        setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);

        int margem = 1;
        int largura = getWidth() - 2 * margem;
        int altura = getHeight() - 2 * margem;

        Color base = getBackground();
        if (getModel().isPressed()) {
            base = base.darker();
        } else if (getModel().isRollover()) {
            base = base.brighter();
        }

        // Sombra discreta e superfície em degradê.
        g2.setColor(new Color(0, 0, 0, 45));
        g2.fillRoundRect(margem, margem + 2, largura, altura, 20, 20);
        g2.setPaint(new GradientPaint(0, margem, base.brighter(),
                0, altura, base.darker()));
        g2.fillRoundRect(margem, margem, largura, altura, 20, 20);

        if (hasFocus()) {
            g2.setColor(new Color(255, 255, 255, 150));
            g2.setStroke(new BasicStroke(1.3f));
            g2.drawRoundRect(margem + 1, margem + 1,
                    largura - 3, altura - 3, 18, 18);
        }

        g2.dispose();
        super.paintComponent(g);
    }
}
